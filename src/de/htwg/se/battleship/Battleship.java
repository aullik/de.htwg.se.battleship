package de.htwg.se.battleship;

import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.controller.ControllerFactory;
import org.apache.log4j.Logger;
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
      final Logger logger = Logger.getLogger(getClass());
      logger.info("Game Started");

      TuiFactory.createTUIMain();
      ControllerFactory.getController();
   }

   /**
    * Starting point for a java application.
    */
   public static void main(String[] args) {
      new Battleship();
   }
}