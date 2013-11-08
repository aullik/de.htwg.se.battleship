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
public class GridTest {

    private Grid g1;
    private Grid g2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        g1 = new Grid(20);
        g2 = new Grid(18);

    }

    @Test
    public void testGetSize() {
        assertEquals(20, g1.getWidth());
        assertEquals(18, g2.getWidth());
    }

    @Test
    public void testGetCell() {
        Cell c1 = new Cell(1, 2);
        assertNull(g1.getCell(c1.getX(), c1.getY()));
        g1.addCell(c1);
        assertEquals(c1, g1.getCell(c1.getX(), c1.getY()));

        Cell c2 = new Cell(3, 4);
        assertNull(g1.getCell(c2.getX(), c2.getY()));
        g1.addCell(c2);
        assertEquals(c2, g1.getCell(c2.getX(), c2.getY()));
    }
}
