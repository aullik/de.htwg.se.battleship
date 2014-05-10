/**
 *
 */
package de.htwg.se.battleship.model.impl;

import java.util.ArrayList;
import java.util.List;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Ship;

/**
 * This class contains all data for an Player
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class PlayerImpl implements Player {

    private final String name;
    private final List<Ship> ships;
    private Grid grid;

    /**
     * Create a new Player instance with name and Map for Cell instances
     * 
     * @param name Player name
     */
    public PlayerImpl(final String name) {
        this.name = name;
        this.ships = new ArrayList<Ship>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addShip(final Ship ship) {
        if (!containsShip(ship)) {
            ships.add(ship);
        }
    }

    @Override
    public boolean containsShip(final Ship ship) {
        return ships.contains(ship);
    }

    @Override
    public void setGrid(final Grid grid) {
        this.grid = grid;
    }

    @Override
    public Grid getGrid() {
        return grid;
    }

    @Override
    public int getNumberOfShipCells() {
        int sum = 0;

        for (Ship ship : ships) {
            sum += ship.getNumberOfCells();
        }
        return sum;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public List<Ship> getShips() {
        return ships;
    }

}
