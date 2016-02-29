/**
 *
 */
package de.htwg.se.battleship.aview.tui.view;

import de.htwg.se.battleship.util.platform.ThreadPlatform;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

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
   private final ThreadPlatform scannerPlatform;


   /**
    *
    */
   private ConsoleInput() {
      stream = System.in;
      scanner = new Scanner(stream);
      this.scannerPlatform = new ThreadPlatform();
      this.scannerPlatform.run();
   }

   /**
    * Retrieve next input word from console
    */
   public void getInputWord(Consumer<String> cons) {
      scannerPlatform.runLater(() -> cons.accept(_getInputWord()));
   }

   private String _getInputWord() {
      try {
         return scanner.next();
      } catch (IllegalStateException e) {
         return "";
      }
   }

   /**
    * Retrieve next input line from console
    *
    * @return String
    */
   public void getInputLine(Consumer<String> cons) {
      scannerPlatform.runLater(() -> cons.accept(_getInputLine()));
   }

   private String _getInputLine() {
      try {
         return scanner.nextLine();
      } catch (IllegalStateException e) {
         return null;
      }
   }


   /**
    * Close all resources of interface
    */
   public void close() {
      scannerPlatform.inputClosed();
   }
}
