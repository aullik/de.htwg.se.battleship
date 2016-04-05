/**
 *
 */
package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.util.platform.ThreadPlatform;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Read input from console and can be interrupt.
 *
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */

public class ConsoleInput {

   private static final SingletonSupplier<ConsoleInput> INST_SUPP = new SingletonSupplier<>(ConsoleInput::new);

   public static ConsoleInput getInstance() {
      return INST_SUPP.get();
   }


   private final Scanner scanner;
   private final InputStream stream;
   private final ScannerPlatform consolePlatform;
   private final Thread scannerThread;
   private final LinkedList<String> buffer;
   private final List<BiConsumer<String, Runnable>> jobList;



   /**
    *
    */
   private ConsoleInput() {
      this.stream = System.in;
      this.scanner = new Scanner(stream);
      this.buffer = new LinkedList<>();
      this.jobList = new ArrayList<>();
      this.scannerThread = new Thread(this::runScannerThread);
      this.scannerThread.setDaemon(true);
      this.scannerThread.start();
      this.consolePlatform = new ScannerPlatform();
      this.consolePlatform.run();

   }

   private void runScannerThread() {
      while (true) {
         try {
            addInputLine(scanner.nextLine());
         } catch (NoSuchElementException | IllegalStateException e) {
            return;
         }
      }
   }

   protected void addLeadingInputLine(final String inp) {
      consolePlatform.runOnPlatform(() -> _addLeadingInputLine(inp));
   }

   private void _addLeadingInputLine(final String inp) {
      if (jobList.isEmpty())
         buffer.addFirst(inp);
      else
         executeConsumer(jobList.remove(0), inp);
   }


   protected void addInputLine(final String inp) {
      consolePlatform.runOnPlatform(() -> _addInputLine(inp));
   }

   private void _addInputLine(final String inp) {
      if (jobList.isEmpty())
         buffer.addLast(inp);
      else
         executeConsumer(jobList.remove(0), inp);
   }

   /**
    * Retrieve next input line from console
    *
    * @return String
    */
   public void getInput(BiConsumer<String, Runnable> inputCons_returnInpIfFailed) {
      consolePlatform.runOnPlatform(() -> _getInput(inputCons_returnInpIfFailed));
   }


   private void _getInput(final BiConsumer<String, Runnable> cons) {
      if (buffer.isEmpty())
         jobList.add(cons);
      else
         executeConsumer(cons, buffer.removeFirst());
   }

   private void executeConsumer(final BiConsumer<String, Runnable> cons, final String inp) {
      cons.accept(inp, () -> addLeadingInputLine(inp));
   }


   /**
    * Close all resources of interface
    */
   public void close() {
      consolePlatform.closeInputQueue();
   }

   private static class ScannerPlatform extends ThreadPlatform {

      @Override
      protected synchronized void closeInputQueue() {
         super.closeInputQueue();
      }
   }
}
