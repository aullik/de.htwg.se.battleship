/**
 * 
 */
package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import de.htwg.se.battleship.model.ICell;
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

        Player p = new Player("test1");
        Cell c = new Cell(1, 1, new Grid(Grid.DEFAULT_SIZE, p));

        HashMap<String, ICell> map = new HashMap<String, ICell>();
        map.put(Cell.createKey(c.getX(), c.getY()), c);

        Ship s = new Ship(p, map);

        assertEquals(p, s.getPlayer());
        assertEquals(c, s.getCell(c.getX(), c.getY()));
    }
}
