/**
 * 
 */
package de.htwg.se.battleship.model;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IModelFabric {

    /**
     * Create an instance of an IPlayer implementation.
     * @param name String
     * @return IPlayer
     */
    IPlayer createPlayer(String name);

    /**
     * Create an instance of an IGrid implementation.
     * @param player IPlayer
     * @return IGrid
     */
    IGrid createGrid(IPlayer player);
}
