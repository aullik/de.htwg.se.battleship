/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import com.google.inject.Singleton;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;

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

}
