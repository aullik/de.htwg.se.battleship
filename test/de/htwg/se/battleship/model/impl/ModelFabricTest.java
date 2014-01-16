package de.htwg.se.battleship.model.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;

public class ModelFabricTest {

    @Test
    public void test() {
        IModelFabric fab = new ModelFabric();
        String s = "Ich bin ein Spieler!";
        IPlayer p = fab.createPlayer(s);
        assertEquals(s, p.getName());

        IGrid g = fab.createGrid(p);
        assertEquals(p, g.getPlayer());
    }

}
