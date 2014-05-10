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
     * Get current IGrid.
     * @return IGrid
     */
    Grid getGrid();

    /**
     * Get instance of Opponent IGrid.
     * @return IGrid
     */
    Grid getOpponentGrid();
}
