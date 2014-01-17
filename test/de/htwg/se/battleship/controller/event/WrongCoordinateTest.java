/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class WrongCoordinateTest {

    @Test
    public void test() {
        String s = "test";
        WrongCoordinate e = new WrongCoordinate(null, s);
        assertEquals(s, e.getMessage());
    }

}
