/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;
import de.htwg.se.battleship.model.old.OLDShip;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class SetShipSuccess extends AbstractEvent {

   private final OLDShip ship;

   /**
    * Create instance of SetShipSuccess
    *
    * @param round
    */
   public SetShipSuccess(OLDRound round, OLDShip ship) {
      super(round);
      this.ship = ship;
   }

   /**
    * Return instance of OLDShip.
    *
    * @return OLDShip
    */
   public OLDShip getShip() {
      return ship;
   }

}
