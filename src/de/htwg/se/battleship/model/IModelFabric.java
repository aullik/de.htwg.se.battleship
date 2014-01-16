/**
 * 
 */
package de.htwg.se.battleship.model;

import java.util.Map;

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


    /**
     * Create an instance of an IShip implementation.
     * @param player IPlayer
     * @param cells  List of cells of the ship.
     * @return IShip
     */
    IShip createShip(IPlayer player, Map<String, ICell> cells);
}
