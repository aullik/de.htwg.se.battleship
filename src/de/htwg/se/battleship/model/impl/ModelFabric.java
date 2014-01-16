/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import java.util.Map;

import com.google.inject.Singleton;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IShip;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
@Singleton
public class ModelFabric implements IModelFabric {

    @Override
    public IPlayer createPlayer(String name) {
        return new Player(name);
    }

    @Override
    public IGrid createGrid(IPlayer player) {
        return new Grid(Grid.DEFAULT_SIZE, player);
    }

    @Override
    public IShip createShip(IPlayer player, Map<String, ICell> cells) {
        return new Ship(player, cells);
    }

}
