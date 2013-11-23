/**
 * 
 */
package de.htwg.se.battleship.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class CellTest {

    private Cell c1;
    private Cell c2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        c1 = new Cell(1, 2);
        c2 = new Cell(12, 8);
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
        assertEquals(null, c1.getGrid());
        assertEquals(null, c2.getGrid());

        Grid g1 = new Grid(10, new Player(""));
        Grid g2 = new Grid(13, new Player(""));

        c1.setGrid(g1);
        assertEquals(g1, c1.getGrid());

        c1.setGrid(g2);
        assertEquals(g2, c1.getGrid());
    }

    @Test
    public void testShip() {
        Ship s1 = new Ship();
        assertNull(c1.getShip());
        c1.setShip(s1);
        assertEquals(c1.getShip(), s1);

        Ship s2 = new Ship();
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
        assertFalse(c1.isShot());

        c1.setToShot();
        assertFalse(c1.isNormal());
        assertFalse(c1.isHit());
        assertTrue(c1.isShot());
    }
}
