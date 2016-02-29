package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import de.htwg.se.battleship.util.stringmap.StringMatchMap;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * @author aullik on 18.01.2016.
 */
public class TUIView {

   private static final String MSG_INVALID_INPUT = "'%s' is no valid input!\n";


   private final ThreadPlatform platform;
   private final String header;
   private final ConsoleInput console;
   private final Logger logger;

   private boolean running;
   private final Queue<TuiJob> jobs;


   public TUIView(final ThreadPlatform platform) {
      this.jobs = new LinkedList<>();
      this.platform = platform;
      this.header = createHeader();
      this.console = TuiFactory.getConsoleInput();
      this.running = false;
      logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
   }

   /**
    * Game name as ASCII-Art:
    * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
    */
   private String createHeader() {
      StringBuilder sb;
      sb = new StringBuilder("\n");
      appendHeaderFrame(sb);
      appendNewLine(sb, "*      ____        _   _   _           _     _            *");
      appendNewLine(sb, "*     |  _ \\      | | | | | |         | |   (_)           *");
      appendNewLine(sb, "*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *");
      appendNewLine(sb, "*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *");
      appendNewLine(sb, "*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *");
      appendNewLine(sb, "*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *");
      appendNewLine(sb, "*                                             | |         *");
      appendNewLine(sb, "*                                             |_|         *");
      appendHeaderFrame(sb);
      return sb.toString();
   }

   private void appendHeaderFrame(final StringBuilder sb) {
      appendNewLine(sb, "***********************************************************");
   }

   private void appendNewLine(final StringBuilder sb, final String s) {
      sb.append(s).append('\n');
   }


   private void nextGameLoop() {
      platform.runLater(this::runGameLoopIteration);
   }

   private void runGameLoopIteration() {
      printInformation();
      console.getInputLine(this::awaitInputPlatformSave);
   }


   private void printInformation() {
      final TuiJob job = getFirstJob();
      final String info;
      if (job != null)
         info = job.getInformation();
      else
         info = null;

      if (info == null)
         return;

      output(header);
      output(info);
   }


   private TuiJob getFirstJob() {
      final Iterator<TuiJob> iterator = jobs.iterator();
      while (iterator.hasNext()) {
         final TuiJob job = iterator.next();
         if (job.isExecuted())
            iterator.remove();
         else
            return job;
      }
      return null;
   }

   private void awaitInputPlatformSave(final String inp) {
      platform.runLater(() -> awaitInput(inp));
   }

   private void awaitInput(final String inp) {
      final TuiJob job = getFirstJob();

      if (job == null) {
         running = false;
         return;
      }

      final String input;
      if (inp == null || (input = inp.trim()).isEmpty())
         nextGameLoop();
      else if (input.startsWith(Command.COMMAND_PREFIX))
         checkCommand(input, job);
      else
         doJob(input, job);

      jobs.remove(job);
      nextGameLoop();
   }

   private void doJob(String input, TuiJob job) {
      if (!job.doJob(input))
         printInvalidInput(input);
   }


   private void checkCommand(String input, final TuiJob job) {
      job.checkCommand(input, this::output);
   }


   private void printInvalidInput(String input) {
      outputFormat(MSG_INVALID_INPUT, input);
   }


   private void outputFormat(String format, String... args) {
      output(String.format(format, args));
   }


   public void printFormat(String format, String... args) {
      abstractPrint(() -> outputFormat(format, args));
   }


   public void print(String out) {
      abstractPrint(() -> outputFormat(out));
   }

   private void abstractPrint(Runnable printer) {
      if (!platform.isPlatformThread()) {
         platform.runLater(() -> abstractPrint(printer));
         return;
      }

      output(header);
      printer.run();

      if (running)
         printInformation();
   }


   private void output(String s) {
      logger.info(s);
   }


   public void setActionOnlyJob(Function<String, Boolean> action, String description, BooleanSupplier alreadyExecuted) {
      // protect critical section
      if (!platform.isPlatformThread())
         platform.runLater(() -> setActionOnlyJob(action, description, alreadyExecuted));
      else
         _setJob(new ActionOnlyJob(action, description, alreadyExecuted));
   }

   public void setCommandOnlyJob(Command strippedCommand) {
      setCommandOnlyJob(Collections.singletonList(strippedCommand));
   }

   public void setCommandOnlyJob(List<Command> strippedCommands) {
      if (!platform.isPlatformThread())
         platform.runLater(() -> setCommandOnlyJob(strippedCommands));
      else
         _setJob(new CommandOnlyJob(strippedCommands));
   }

   public void setJob(TuiJob job) {
      if (!platform.isPlatformThread())
         platform.runLater(() -> setJob(job));
      else
         _setJob(job);
   }

   private void _setJob(TuiJob job) {
      this.jobs.offer(job);

      if (!running) {
         running = true;
         nextGameLoop();
      } else if (job == getFirstJob()) //check job is first in queue
         printInformation();
   }

   private class CommandOnlyJob extends TuiJob {

      private final List<Command> strippedCommands;

      private CommandOnlyJob(List<Command> strippedCommands) {
         this.strippedCommands = strippedCommands;
      }


      @Override
      protected void populateCommandMap(final StringMatchMap<Command> commandMap) {
         strippedCommands.forEach(c -> commandMap.put(c.getCommandStripped(), c));

      }

      //All of this can stay false or null.
      @Override
      public boolean doJob(final String input) {
         return false;
      }


      @Override
      protected String getDescription() {
         return null;
      }

      @Override
      protected boolean isExecuted() {
         return false;
      }
   }

   private class ActionOnlyJob extends TuiJob {

      private final Function<String, Boolean> action;
      private final String description;
      private final BooleanSupplier alreadyExecuted;

      private ActionOnlyJob(Function<String, Boolean> action, String description, final BooleanSupplier
            alreadyExecuted) {

         this.action = action;
         this.description = description;
         this.alreadyExecuted = alreadyExecuted;
      }

      @Override
      protected void populateCommandMap(final StringMatchMap<Command> commandMap) { // do nothing
      }

      @Override
      public boolean doJob(final String input) {
         return action.apply(input);
      }

      @Override
      protected String getDescription() {
         return description;
      }

      protected boolean isExecuted() {
         return alreadyExecuted.getAsBoolean();
      }
   }

}
