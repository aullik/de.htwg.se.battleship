/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface Round {

    /**
     * Switch to other player.
     */
    void next();

    /**
     * Get current Grid.
     * @return Grid
     */
    Grid getGrid();

    /**
     * Get instance of Opponent Grid.
     * @return Grid
     */
    Grid getOpponentGrid();
}
