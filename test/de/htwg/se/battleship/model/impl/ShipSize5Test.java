package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static de.htwg.se.battleship.model.impl.PossibleShipTest.cellList;

/**
 * @author aullik on 08.12.2015.
 */
public class ShipSize5Test {


   @Test
   public void testSize() {
      _testSize(cellList(1));
      _testSize(cellList(2));
      _testSize(cellList(3));
      _testSize(cellList(4));
      _testSize(cellList(6));

      Assert.assertNotNull(new ShipSize5(cellList(5)));

   }


   private void _testSize(List<RCell> cells) {
      try {
         new ShipSize5(cells);
      } catch (IllegalArgumentException ignore) {
         return;
      }
      // if not exception if thrown, this will be called
      Assert.fail();
   }

}