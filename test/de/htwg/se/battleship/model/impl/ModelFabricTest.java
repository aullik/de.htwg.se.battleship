package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;
import de.htwg.se.battleship.model.IShip;

public class ModelFabricTest {

    @Test
    public void test() {
        IModelFabric fab = new ModelFabric();

        String string = "Ich bin ein Spieler!";
        IPlayer p = fab.createPlayer(string);
        assertEquals(string, p.getName());

        IGrid g = fab.createGrid(p);
        assertEquals(p, g.getPlayer());

        ICell c = new Cell(1, 1, g);
        Map<String, ICell> map = new HashMap<String, ICell>();
        map.put(Cell.createKey(c.getX(), c.getY()), c);
        IShip s = fab.createShip(p, map);
        assertEquals(p, s.getPlayer());

        IRound r = fab.createRound(g, null);
        assertEquals(g, r.getGrid());
    }

}
