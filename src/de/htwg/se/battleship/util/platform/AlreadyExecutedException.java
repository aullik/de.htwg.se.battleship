package de.htwg.se.battleship.util.platform;

/**
 * @author aullik on 28.12.2015.
 * @see de.htwg.se.battleship.util.platform.SingleUseConsumer
 */
public class AlreadyExecutedException extends Exception {

   public AlreadyExecutedException() {
   }

   public AlreadyExecutedException(final String message) {
      super(message);
   }
}
