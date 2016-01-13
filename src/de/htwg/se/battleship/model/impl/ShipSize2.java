package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;

import java.util.List;

/**
 * Ship with a size of 2
 * Created by aullik on 04.12.2015.
 */
public class ShipSize2 extends ShipWithSetSize {

   public ShipSize2(final List<RCell> cells) {
      super(2, cells);
   }
}
