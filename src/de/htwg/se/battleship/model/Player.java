/**
 *
 */
package de.htwg.se.battleship.model;

import java.util.List;

/**
 * @author phdaniel
 *
 */
public class Player {

    private final String name;
    private final List<Ship> ships;

    public Player(final String name, List<Ship> ships) {
        this.name = name;
        this.ships = ships;
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return this.ships;
    }
}
