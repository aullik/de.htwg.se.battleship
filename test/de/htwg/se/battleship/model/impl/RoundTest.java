/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;

/**
 * @author Philipp
 *
 */
public class RoundTest {

    @Test
    public void test() {
        IPlayer p = new Player("");
        IGrid g1 = new Grid(Grid.DEFAULT_SIZE, p);
        IGrid g2 = new Grid(Grid.DEFAULT_SIZE, p);

        IRound r = new Round(g1, g2);

        assertEquals(g1, r.getGrid());
        assertEquals(g2, r.getOpponentGrid());
        r.next();
        assertEquals(g2, r.getGrid());
        r.next();
        assertEquals(g1, r.getGrid());
    }

}
