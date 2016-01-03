package de.htwg.se.battleship.util.controller.impl;

/**
 * Is thrown when something is executed in the GameThead that is not supposed to be executed in the Gamethread.
 *
 * @author aullik on 29.12.2015.
 * @see de.htwg.se.battleship.util.controller.impl.SingleUseConsumer
 * @see de.htwg.se.battleship.util.controller.impl.GamePlatform
 */
public class NotUIThreadException extends Exception {

   public NotUIThreadException() {
   }

   public NotUIThreadException(final String message) {
      super(message);
   }
}
