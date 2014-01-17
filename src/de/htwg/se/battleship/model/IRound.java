/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IRound {

    /**
     * Switch to other player.
     */
    void next();

    /**
     * Get current IGrid.
     * @return IGrid
     */
    IGrid getGrid();

    /**
     * Get instance of Opponent IGrid.
     * @return IGrid
     */
    IGrid getOpponentGrid();
}
