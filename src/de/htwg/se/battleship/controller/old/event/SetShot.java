/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class SetShot extends AbstractEvent {

   /**
    * Create an instance of SetShip
    *
    * @param round Round
    */
   public SetShot(Round round) {
      super(round);
   }
}