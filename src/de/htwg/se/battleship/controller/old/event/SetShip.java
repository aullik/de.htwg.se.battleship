/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class SetShip extends AbstractEvent {

   /**
    * Create an instance of SetShip
    *
    * @param round Round
    */
   public SetShip(Round round) {
      super(round);
   }

}
