/**
 * 
 */
package de.htwg.se.battleship.model;

import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface ModelFabric {

    /**
     * Create an instance of an IPlayer implementation.
     * @param name String
     * @return IPlayer
     */
    Player createPlayer(String name);

    /**
     * Create an instance of an IGrid implementation.
     * @param player IPlayer
     * @return IGrid
     */
    Grid createGrid(Player player);


    /**
     * Create an instance of an IShip implementation.
     * @param player IPlayer
     * @param cells  List of cells of the ship.
     * @return IShip
     */
    Ship createShip(Player player, Map<String, Cell> cells);

    /**
     * Create an instance of an IRound implementation.
     * @param g1 IGrid
     * @param g2 IGrid
     * @return IRound
     */
    Round createRound(Grid g1, Grid g2);

}
