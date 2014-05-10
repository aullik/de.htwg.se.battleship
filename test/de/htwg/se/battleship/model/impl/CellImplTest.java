/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.impl.CellImpl;
import de.htwg.se.battleship.model.impl.GridImpl;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.ShipImpl;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class CellImplTest {

    private CellImpl c1;
    private CellImpl c2;
    private GridImpl g1;
    private GridImpl g2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        g1 = new GridImpl(1, new PlayerImpl(""));
        g2 = new GridImpl(1, new PlayerImpl(""));
        c1 = new CellImpl(1, 2, g1);
        c2 = new CellImpl(12, 8, g2);
    }

    @Test
    public void testX() {
        assertEquals(1, c1.getX());
        assertEquals(12, c2.getX());
    }

    @Test
    public void testY() {
        assertEquals(2, c1.getY());
        assertEquals(8, c2.getY());
    }

    @Test
    public void testKey() {
        assertEquals("1.2", c1.getKey());
        assertEquals("12.8", c2.getKey());
    }

    @Test
    public void testGrid() {
        assertEquals(g1, c1.getGrid());
        assertEquals(g2, c2.getGrid());
    }

    @Test
    public void testShip() {
        Player p = new PlayerImpl("test");

        HashMap<String, Cell> map1 = new HashMap<String, Cell>();
        map1.put(CellImpl.createKey(c1.getX(), c1.getY()), c1);
        assertNull(c1.getShip());
        ShipImpl s1 = new ShipImpl(p, map1);
        c1.setShip(s1);
        assertEquals(c1.getShip(), s1);

        HashMap<String, Cell> map2 = new HashMap<String, Cell>();
        map1.put(CellImpl.createKey(c2.getX(), c2.getY()), c2);
        ShipImpl s2 = new ShipImpl(p, map2);
        assertNull(c2.getShip());
        c2.setShip(s2);
        assertEquals(c2.getShip(), s2);

        assertEquals(c1.getShip(), s1);
    }

    @Test
    public void testStatus() {
        assertTrue(c1.isNormal());
        assertFalse(c1.isHit());
        assertFalse(c1.isShot());

        c1.setToHit();
        assertFalse(c1.isNormal());
        assertTrue(c1.isHit());
        assertTrue(c1.isShot());

        c1.setToShot();
        assertFalse(c1.isNormal());
        assertFalse(c1.isHit());
        assertTrue(c1.isShot());
    }
}
