/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp
 *
 */
public class RoundTest {

    @Test
    public void test() {
        Player p = new PlayerImpl("");
        Grid g1 = new GridImpl(GridImpl.DEFAULT_SIZE, p);
        Grid g2 = new GridImpl(GridImpl.DEFAULT_SIZE, p);

        Round r = new RoundImpl(g1, g2);

        assertEquals(g1, r.getGrid());
        assertEquals(g2, r.getOpponentGrid());
        r.next();
        assertEquals(g2, r.getGrid());
        r.next();
        assertEquals(g1, r.getGrid());
    }

}
