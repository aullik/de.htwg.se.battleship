/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.model.impl.GridImpl;
import de.htwg.se.battleship.model.impl.PlayerImpl;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class GridImplTest {

    private GridImpl g1;
    private GridImpl g2;
    private PlayerImpl p1;
    private PlayerImpl p2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        p1 = new PlayerImpl("test1");
        p2 = new PlayerImpl("test2");
        g1 = new GridImpl(20, p1);
        g2 = new GridImpl(18, p2);

    }

    @Test
    public void testGetSize() {
        assertEquals(20, g1.getWidth());
        assertEquals(18, g2.getWidth());
    }

    @Test
    public void testGetCell() {
        assertNull(g1.getCell(20, 20));
        assertNotNull(g1.getCell(0, 0));

        assertNull(g2.getCell(19, 19));
        assertNotNull(g2.getCell(0, 0));
    }

    @Test
    public void testGetPlayer() {
        assertEquals(g1.getPlayer(), p1);
        assertEquals(g2.getPlayer(), p2);
    }
}
