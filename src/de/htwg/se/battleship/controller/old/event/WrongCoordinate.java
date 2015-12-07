/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class WrongCoordinate extends AbstractEvent {

   private final String message;

   /**
    * Create instance of WrongCoordinate.
    *
    * @param round Round
    */
   public WrongCoordinate(Round round, String message) {
      super(round);
      this.message = message;
   }

   public String getMessage() {
      return message;
   }

}
