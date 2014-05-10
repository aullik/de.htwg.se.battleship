/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Ship;
import de.htwg.se.battleship.model.impl.PlayerImpl;
import de.htwg.se.battleship.model.impl.ShipImpl;

/**
 * @author Philipp
 *
 */
public class SetShipSuccessTest {

    @Test
    public void test() {
        Ship s = new ShipImpl(new PlayerImpl(""), new HashMap<String, Cell>());
        SetShipSuccess e = new SetShipSuccess(null, s);
        assertEquals(s, e.getShip());
    }

}
