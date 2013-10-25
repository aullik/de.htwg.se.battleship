/**
 * 
 */
package de.htwg.se.battleship.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author phdaniel
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

}
