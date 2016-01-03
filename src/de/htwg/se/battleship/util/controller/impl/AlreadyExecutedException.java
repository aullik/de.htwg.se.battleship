package de.htwg.se.battleship.util.controller.impl;

/**
 * @author aullik on 28.12.2015.
 * @see de.htwg.se.battleship.util.controller.impl.SingleUseConsumer
 */
public class AlreadyExecutedException extends Exception {

   public AlreadyExecutedException() {
   }

   public AlreadyExecutedException(final String message) {
      super(message);
   }
}
