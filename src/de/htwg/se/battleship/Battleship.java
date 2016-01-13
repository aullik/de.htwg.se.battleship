package de.htwg.se.battleship;

import de.htwg.se.battleship.aview.tui.impl.TextUI;
import org.apache.log4j.PropertyConfigurator;

/**
 * Initiate logging and dependency Injector and start battleship application.
 *
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public final class Battleship {

   /**
    * Start Battleship application.
    */
   protected Battleship() {
      PropertyConfigurator.configure("log4j.properties");

      TextUI.getInstance();
   }

   /**
    * Starting point for a java application.
    */
   public static void main(String[] args) {
      new Battleship();
   }
}