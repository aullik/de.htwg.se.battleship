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
     * Create an instance of an Player implementation.
     * @param name String
     * @return Player
     */
    Player createPlayer(String name);

    /**
     * Create an instance of an Grid implementation.
     * @param player Player
     * @return Grid
     */
    Grid createGrid(Player player);


    /**
     * Create an instance of an Ship implementation.
     * @param player Player
     * @param cells  List of cells of the ship.
     * @return Ship
     */
    Ship createShip(Player player, Map<String, Cell> cells);

    /**
     * Create an instance of an Round implementation.
     * @param g1 Grid
     * @param g2 Grid
     * @return Round
     */
    Round createRound(Grid g1, Grid g2);

}
