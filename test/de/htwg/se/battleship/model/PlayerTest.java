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
public class PlayerTest {

    private Player p1;
    private Player p2;

    private final static String NAME1 = "philipp";
    private final static String NAME2 = "nicolas";

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        p1 = new Player(NAME1);
        p2 = new Player(NAME2);
    }

    @Test
    public void testGetName() {

        assertEquals(NAME1, p1.getName());
        assertEquals(NAME2, p2.getName());
    }

    @Test
    public void testShip() {

        Ship s1 = new Ship();
        assertFalse(p1.containsShip(s1));
        p1.addShip(s1);
        assertTrue(p1.containsShip(s1));

        Ship s2 = new Ship();
        assertFalse(p1.containsShip(s2));
        p1.addShip(s2);
        assertTrue(p1.containsShip(s2));

        assertTrue(p1.containsShip(s1));
    }

    @Test
    public void testCell() {

        Cell c1 = new Cell(1, 2);
        assertNull(p1.getCell(c1.getX(), c1.getY()));
        p1.addCell(c1);
        assertEquals(c1, p1.getCell(c1.getX(), c1.getY()));

        Cell c2 = new Cell(3, 4);
        assertNull(p1.getCell(c2.getX(), c2.getY()));
        p1.addCell(c2);
        assertEquals(c2, p1.getCell(c2.getX(), c2.getY()));

        assertEquals(c1, p1.getCell(c1.getX(), c1.getY()));
    }
}
