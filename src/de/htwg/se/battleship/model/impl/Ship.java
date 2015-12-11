package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.readwrite.RWCell;
import de.htwg.se.battleship.model.readwrite.RWShip;

import java.util.List;

/**
 * @author aullik on 07.12.2015.
 */
public class Ship extends BasicRShip implements RWShip {

   private final List<RWCell> cells;

   public Ship(final List<RWCell> cells) {
      super(cells);
      this.cells = cells;
      cells.forEach(c -> c.setShip(this));
   }


   @Override
   public int getNumberOfCells() {
      return cells.size();
   }

   @Override
   public List<? extends RWCell> getCells() {
      return cells;
   }

}
