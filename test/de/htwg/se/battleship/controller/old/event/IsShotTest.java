package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.impl.CellImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IsShotTest {

   @Test
   public void test() {
      Cell cell = new CellImpl(0, 0, null);
      IsShot isShot = new IsShot(null, cell);
      assertEquals(cell, isShot.getCell());
   }

}
