/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class Round implements IRound {

    private final IGrid[] grids;
    private int index;

    /**
     * Creates a Round instance.
     * @param g1 IGrid
     * @param g2 IGrid
     */
    public Round(IGrid g1, IGrid g2) {
        grids = new IGrid[2];
        index = 0;
        grids[0] = g1;
        grids[1] = g2;
    }

    @Override
    public void next() {
        index = calc();
    }

    private int calc() {
        return (index + 1) % 2;
    }

    @Override
    public IGrid getGrid() {
        return grids[index];
    }

    @Override
    public IGrid getOpponentGrid() {
        return grids[calc()];
    }

}
