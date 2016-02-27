package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.aview.tui.BufferedCommandMatchMap;
import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

/**
 * @author aullik on 18.01.2016.
 */
public class TUIView {

   private static final String MENU_FORMAT = "%-20s - %s";
   private static final String MSG_INPUT_NOTE = "Please choose a menu: ";
   private static final String MSG_POSSIBLE_ENTRIES = "\nDid you mean: \n";
   private static final String NEXT_CLOSEST = "\n Interpreted '%s' as '%s'\n";
   private static final String MSG_INVALID_INPUT = "'%s' is no valid input!\n";


   private final ThreadPlatform platform;
   private final BufferedCommandMatchMap commandMap;
   private final String header;
   private final ConsoleInput console;
   private final Logger logger;
   private String lastInfo = null;

   private final Queue<Job> jobs;


   public TUIView(final ThreadPlatform platform) {
      this.jobs = new LinkedList<>();
      this.platform = platform;
      commandMap = new BufferedCommandMatchMap(3);
      this.header = createHeader();
      this.console = TuiFactory.getConsoleInput();
      logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");

      nextGameLoop();
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
      String info = createInformation();
      if (info == null || info.equals(lastInfo))
         return;

      output(header);
      output(info);
   }

   private String createInformation() {
      final Job firstJob = getFirstJob();
      if (firstJob != null)
         return firstJob.description;
      else if (commandMap.size() > 0)
         return printAllCommands();
      else
         return null;
   }

   private Job getFirstJob() {
      final Iterator<Job> iterator = jobs.iterator();
      while (iterator.hasNext()) {
         final Job job = iterator.next();
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
      lastInfo = null;
      final String input;
      Job job;
      if (inp == null || (input = inp.trim()).isEmpty())
         nextGameLoop();
      else if (input.startsWith(Command.COMMAND_PREFIX))
         checkCommand(input);
      else if ((job = getFirstJob()) != null) {
         job.action(input);
      } else
         printInvalidInput(input);
   }


   private void printInvalidInput(String input) {
      outputFormat(MSG_INVALID_INPUT, input);
      nextGameLoop();
   }

   private void checkCommand(final String inp) {
      String input = inp;
      while (input.startsWith(Command.COMMAND_PREFIX))
         input = input.substring(1);

      final List<Command> closest = commandMap.getApprox(input);
      if (closest.isEmpty())
         printInvalidInput(input);
      else if (closest.size() == 1)
         executeClosest(input, closest.get(0));
      else
         platform.runLater(() -> printClosestCommands(closest));
   }

   private void executeClosest(String input, Command c) {
      if (!input.equals(c.getCommand()))
         outputFormat(NEXT_CLOSEST, input, c.getCommandStripped());

      commandMap.remove(c);

      c.doAction();
      nextGameLoop();
   }

   private void printClosestCommands(final List<Command> closest) {
      String possibilities = printCommands(closest);
      if (possibilities.isEmpty())
         output(printAllCommands());
      else {
         output(MSG_POSSIBLE_ENTRIES);
         output(possibilities);
      }
   }

   private String printAllCommands() {
      final List<Command> all = commandMap.getApprox("");
      if (!all.isEmpty())
         return printCommands(all);
      else
         return "";
   }

   private String printCommands(final List<Command> commandList) {
      StringJoiner sj = new StringJoiner("\n");
      commandList.forEach(c -> sj.add(commandToMenuEntry(c)));
      sj.add(MSG_INPUT_NOTE);
      return sj.toString();
   }

   private String commandToMenuEntry(Command c) {
      return String.format(MENU_FORMAT, c.getCommand(), c.getDescription());
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
      printInformation();
   }


   private void output(String s) {
      logger.info(s);
   }


   public void addCommand(final Command command) {
      if (!platform.isPlatformThread()) {
         platform.runLater(() -> addCommand(command));
         return;
      }

      commandMap.put(command);

      if (getFirstJob() == null) {
         printInformation();
      }
   }

   public void removeCommand(final Command command) {
      if (!platform.isPlatformThread()) {
         platform.runLater(() -> removeCommand(command));
         return;
      }
      commandMap.remove(command);
   }

   public void setAllCommands(final List<Command> commandList) {
      if (!platform.isPlatformThread()) {
         platform.runLater(() -> setAllCommands(commandList));
         return;
      }
      commandMap.clear();
      commandList.forEach(commandMap::put);
   }

   public void setJob(Function<String, Boolean> action, String description, BooleanSupplier alreadyExecuted) {
      // protect critical section
      if (!platform.isPlatformThread()) {
         platform.runLater(() -> setJob(action, description, alreadyExecuted));
         return;
      }
      this.jobs.offer(new Job(action, description, alreadyExecuted));
      printInformation();
   }

   private class Job {

      private final Function<String, Boolean> action;
      private final String description;
      private final BooleanSupplier alreadyExecuted;

      private Job(Function<String, Boolean> action, String description, final BooleanSupplier alreadyExecuted) {
         this.action = action;
         this.description = description;
         this.alreadyExecuted = alreadyExecuted;
      }

      void action(String input) {
         if (action.apply(input)) {
            jobs.remove(this);
            nextGameLoop();
         } else
            printInvalidInput(input);
      }

      boolean isExecuted() {
         return alreadyExecuted.getAsBoolean();
      }

   }

}
