/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.impl.CellImpl;
import de.htwg.se.battleship.model.impl.GridImpl;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.ShipImpl;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class ShipTest {

    @Test
    public void testPlayer() {

        Player p = new PlayerImpl("test1");
        Grid g = new GridImpl(GridImpl.DEFAULT_SIZE, p);
        Cell c = new CellImpl(1, 1, g);

        HashMap<String, Cell> map = new HashMap<String, Cell>();
        map.put(CellImpl.createKey(c.getX(), c.getY()), c);
        map.put("test", c);

        ShipImpl s = new ShipImpl(p, map);

        assertEquals(p, s.getPlayer());
        assertEquals(c, s.getCell(c.getX(), c.getY()));
        assertNull(s.getCell(-1, -1));
        assertEquals(1, s.getNumberOfCells());
        assertNotEquals(null, s.getCells());
    }
}
