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

   private final Scanner scanner;
   private final InputStream stream;
   private Thread thread;

   /**
    *
    */
   protected ConsoleInput() {
      stream = System.in;
      scanner = new Scanner(stream);
      thread = Thread.currentThread();
   }

   /**
    * Retrieve next input word from console
    *
    * @return String
    */
   public String getInputWord() throws IOException {
      thread = Thread.currentThread();
      try {
         return scanner.next();
      } catch (IllegalStateException e) {
         return null;
      }
   }

   /**
    * Retrieve next input line from console
    *
    * @return String
    */
   public String getInputLine() throws IOException {
      thread = Thread.currentThread();
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
      thread.interrupt();
   }
}
