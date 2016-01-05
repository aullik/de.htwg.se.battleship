package de.htwg.se.battleship.controller.ingame.impl;

import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWGrid;

import java.util.function.Consumer;

/**
 * @author aullik on 05.01.2016.
 */
public class OpponentCellShooter implements Consumer<REnemyCell> {

   private final RWGrid targetGrid;

   public OpponentCellShooter(RWGrid targetGrid) {
      this.targetGrid = targetGrid;
   }

   @Override
   public void accept(final REnemyCell rEnemyCell) {
      final RWCell cell = targetGrid.getCell(rEnemyCell);
      if (cell == null)
         throw new IllegalArgumentException("no such cell in targeted grid");

      cell.shootCell();
   }
}
