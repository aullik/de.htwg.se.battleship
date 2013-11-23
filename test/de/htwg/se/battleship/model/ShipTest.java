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
public class ShipTest {

    private Ship s;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        s = new Ship();
    }

    @Test
    public void testPlayer() {
        Player p1 = new Player("test1");
        assertNull(s.getPlayer());
        s.setPlayer(p1);
        assertEquals(p1, s.getPlayer());

        Player p2 = new Player("test2");
        s.setPlayer(p2);
        assertEquals(p2, s.getPlayer());
    }

    @Test
    public void testCell() {
        Cell c1 = new Cell(1, 2, new Grid(1, new Player("")));
        assertNull(s.getCell(c1.getX(), c1.getY()));
        s.addCell(c1);
        assertEquals(c1, s.getCell(c1.getX(), c1.getY()));

        Cell c2 = new Cell(2, 3, new Grid(1, new Player("")));
        assertNull(s.getCell(c2.getX(), c2.getY()));
        s.addCell(c2);
        assertEquals(c2, s.getCell(c2.getX(), c2.getY()));


        assertEquals(c1, s.getCell(c1.getX(), c1.getY()));
    }
}
