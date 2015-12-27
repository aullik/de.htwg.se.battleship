/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class SetShot extends AbstractEvent {

   /**
    * Create an instance of SetShip
    *
    * @param round OLDRound
    */
   public SetShot(OLDRound round) {
      super(round);
   }
}