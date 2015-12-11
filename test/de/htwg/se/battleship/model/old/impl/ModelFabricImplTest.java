package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.ModelFabric;
import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.Round;
import de.htwg.se.battleship.model.old.Ship;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ModelFabricImplTest {

   @Test
   public void test() {
      ModelFabric fab = ModelFabricImpl.getInstance();

      String string = "Ich bin ein Spieler!";
      OLDPlayer p = fab.createPlayer(string);
      assertEquals(string, p.getName());

      OLDGrid g = fab.createGrid(p);
      assertEquals(p, g.getPlayer());

      OLDCell c = new OLDCellImpl(1, 1, g);
      Map<String, OLDCell> map = new HashMap<String, OLDCell>();
      map.put(OLDCellImpl.createKey(c.getX(), c.getY()), c);
      Ship s = fab.createShip(p, map);
      assertEquals(p, s.getPlayer());

      Round r = fab.createRound(g, null);
      assertEquals(g, r.getGrid());
   }

}
