/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.Ship;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class SetShipSuccess extends AbstractEvent {

   private final Ship ship;

   /**
    * Create instance of SetShipSuccess
    *
    * @param round
    */
   public SetShipSuccess(Round round, Ship ship) {
      super(round);
      this.ship = ship;
   }

   /**
    * Return instance of Ship.
    *
    * @return Ship
    */
   public Ship getShip() {
      return ship;
   }

}
