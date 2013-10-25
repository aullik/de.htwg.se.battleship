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
public class ShipTest {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        List<Cell> l1 = new ArrayList<>();
        assertEquals(l1, new Ship(l1).getCells());

        List<Cell> l2 = new ArrayList<>();
        l2.add(new Cell(0, 0));
        assertEquals(l2, new Ship(l2).getCells());

    }

}
