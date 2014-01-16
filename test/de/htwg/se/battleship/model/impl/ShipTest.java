/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.impl.Cell;
import de.htwg.se.battleship.model.impl.Grid;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.model.impl.Ship;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 * 
 */
public class ShipTest {

    @Test
    public void testPlayer() {

        IPlayer p = new Player("test1");
        IGrid g = new Grid(Grid.DEFAULT_SIZE, p);
        ICell c = new Cell(1, 1, g);

        HashMap<String, ICell> map = new HashMap<String, ICell>();
        map.put(Cell.createKey(c.getX(), c.getY()), c);
        map.put("test", c);

        Ship s = new Ship(p, map);

        assertEquals(p, s.getPlayer());
        assertEquals(c, s.getCell(c.getX(), c.getY()));
        assertNull(s.getCell(-1, -1));
    }
}
