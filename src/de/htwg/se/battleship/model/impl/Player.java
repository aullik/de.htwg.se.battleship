/**
 *
 */
package de.htwg.se.battleship.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IShip;

/**
 * This class contains all data for an Player
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class Player implements IPlayer {

    private final String name;
    private final List<IShip> ships;
    private IGrid grid;

    /**
     * Create a new Player instance with name and Map for Cell instances
     * 
     * @param name Player name
     */
    public Player(final String name) {
        this.name = name;
        this.ships = new ArrayList<IShip>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addShip(final IShip ship) {
        if (!containsShip(ship)) {
            ships.add(ship);
        }
    }

    @Override
    public boolean containsShip(final IShip ship) {
        return ships.contains(ship);
    }

    @Override
    public void setGrid(final IGrid grid) {
        this.grid = grid;
    }

    @Override
    public IGrid getGrid() {
        return grid;
    }
}
