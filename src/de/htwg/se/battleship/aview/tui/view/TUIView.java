package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
      if (!platform.inputClosed())
         platform.runLater(this::runGameLoopIteration);
   }

   private void runGameLoopIteration() {
      if (getFirstJob() == null) {
         running = false;
         return;
      }

      printInformation();
      console.getInput(this::awaitInputPlatformSave);
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

   private void awaitInputPlatformSave(final String inp, final Runnable returnInput) {
      platform.runLater(() -> awaitInput(inp, returnInput));
   }

   private void awaitInput(final String inp, final Runnable returnInput) {
      //TODO REMOVE
      output("###INPUT:" + inp);

      final TuiJob job = getFirstJob();

      if (job == null) {
         returnInput.run();
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
      platform.runOnPlatform(() -> _abstractPrint(printer));
   }

   private void _abstractPrint(Runnable printer) {
      output(header);
      printer.run();

      if (running)
         printInformation();
   }

   private void output(String s) {
      logger.info(s);
   }


   public void setJob(TuiJob job) {
      platform.runOnPlatform(() -> _setJob(job));
   }

   private void _setJob(TuiJob job) {
      this.jobs.offer(job);

      if (!running) {
         running = true;
         nextGameLoop();
      } else if (job == getFirstJob()) //check job is first in queue
         printInformation();
   }

   public void close() {
      clearJobs();
      console.close();
   }

   public void clearJobs() {
      jobs.clear();
   }

}
