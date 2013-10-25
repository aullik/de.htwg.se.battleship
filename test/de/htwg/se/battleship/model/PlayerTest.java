/**
 * 
 */
package de.htwg.se.battleship.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author phdaniel
 * 
 */
public class PlayerTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testGetName() {

        assertEquals("philipp", new Player("philipp", null).getName());
        assertEquals("nicolas", new Player("nicolas", null).getName());
    }

    @Test
    public void testGetShip() {

        List<Ship> l = new ArrayList<>();
        Player p = new Player("philipp", l);

        assertEquals(l, p.getShips());

    }

}
