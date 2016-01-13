package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static de.htwg.se.battleship.model.impl.PossibleShipTest.cellList;

/**
 * @author aullik on 08.12.2015.
 */
public class ShipSize4Test {


   @Test
   public void testSize() {
      _testSize(cellList(1));
      _testSize(cellList(2));
      _testSize(cellList(3));
      _testSize(cellList(5));
      _testSize(cellList(6));

      Assert.assertNotNull(new ShipSize4(cellList(4)));

   }


   private void _testSize(List<RCell> cells) {
      try {
         new ShipSize4(cells);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      // if not exception if thrown, this will be called
      Assert.fail();
   }

}