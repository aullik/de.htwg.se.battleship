package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.impl.OLDCellImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IsShotTest {

   @Test
   public void test() {
      OLDCell cell = new OLDCellImpl(0, 0, null);
      IsShot isShot = new IsShot(null, cell);
      assertEquals(cell, isShot.getCell());
   }

}
