/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class RoundImpl implements Round {

    private final Grid[] grids;
    private int index;

    /**
     * Creates a Round instance.
     * @param g1 Grid
     * @param g2 Grid
     */
    public RoundImpl(Grid g1, Grid g2) {
        grids = new Grid[2];
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
    public Grid getGrid() {
        return grids[index];
    }

    @Override
    public Grid getOpponentGrid() {
        return grids[calc()];
    }

}
