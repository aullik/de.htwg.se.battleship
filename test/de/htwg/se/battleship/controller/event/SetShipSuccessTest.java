/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IShip;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.model.impl.Ship;

/**
 * @author Philipp
 *
 */
public class SetShipSuccessTest {

    @Test
    public void test() {
        IShip s = new Ship(new Player(""), new HashMap<String, ICell>());
        SetShipSuccess e = new SetShipSuccess(null, s);
        assertEquals(s, e.getShip());
    }

}
