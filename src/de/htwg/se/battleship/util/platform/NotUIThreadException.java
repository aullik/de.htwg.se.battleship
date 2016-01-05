package de.htwg.se.battleship.util.platform;

/**
 * Is thrown when something is executed in the GameThead that is not supposed to be executed in the Gamethread.
 *
 * @author aullik on 29.12.2015.
 * @see de.htwg.se.battleship.util.platform.SingleUseConsumer
 * @see de.htwg.se.battleship.util.platform.ThreadPlatform
 */
public class NotUIThreadException extends Exception {

   public NotUIThreadException() {
   }

   public NotUIThreadException(final String message) {
      super(message);
   }
}
