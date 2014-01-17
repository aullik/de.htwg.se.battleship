/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.model.IShip;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class SetShipSuccess extends AbstractEvent {

    private final IShip ship;

    /**
     * Create instance of SetShipSuccess
     * @param round
     */
    public SetShipSuccess(IRound round, IShip ship) {
        super(round);
        this.ship = ship;
    }

    /**
     * Return instance of IShip.
     * @return IShip
     */
    public IShip getShip() {
        return ship;
    }

}
