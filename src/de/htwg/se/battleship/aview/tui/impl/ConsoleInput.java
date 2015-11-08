/**
 *
 */
package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

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

   private static final long SLEEP_TIME = 10L;

   private final Scanner scanner;
   private final InputStream stream;
   private Thread thread;

   /**
    *
    */
   protected ConsoleInput() {
      stream = System.in;
      scanner = new Scanner(stream);
   }


   /**
    * Retrieve input from interface
    *
    * @return String
    */
   public String getInput() throws IOException {
      String string = "";
      if (hasNextLine()) {
         string = scanner.nextLine();
      }
      return string;
   }

   private boolean hasNextLine() throws IOException {
      thread = Thread.currentThread();
      while (stream.available() == 0) {
         try {
            Thread.currentThread();
            Thread.sleep(ConsoleInput.SLEEP_TIME);
         } catch (InterruptedException e) {
            return false;
         }
      }
      return scanner.hasNextLine();
   }

   /**
    * Close all resources of interface
    */
   public void close() {
      thread.interrupt();
   }
}
