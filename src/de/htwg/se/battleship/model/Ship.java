/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * @author phdaniel
 *
 */
public class Ship {

    private final List<Cell> ships;

    public Ship(List<Cell> ships) {
        this.ships = ships;
    }

    public List<Cell> getCells() {
        return ships;
    }

}
