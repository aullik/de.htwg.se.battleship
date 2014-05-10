package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.ModelFabric;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.Ship;

public class ModelFabricTest {

    @Test
    public void test() {
        ModelFabric fab = new ModelFabricImpl();

        String string = "Ich bin ein Spieler!";
        Player p = fab.createPlayer(string);
        assertEquals(string, p.getName());

        Grid g = fab.createGrid(p);
        assertEquals(p, g.getPlayer());

        Cell c = new CellImpl(1, 1, g);
        Map<String, Cell> map = new HashMap<String, Cell>();
        map.put(CellImpl.createKey(c.getX(), c.getY()), c);
        Ship s = fab.createShip(p, map);
        assertEquals(p, s.getPlayer());

        Round r = fab.createRound(g, null);
        assertEquals(g, r.getGrid());
    }

}
