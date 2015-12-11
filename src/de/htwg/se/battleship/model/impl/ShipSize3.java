package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;

import java.util.List;

/**
 * Ship with a size of 3
 * Created by aullik on 04.12.2015.
 */
public class ShipSize3 extends ShipWithSetSize {

   public ShipSize3(final List<RCell> cells) {
      super(3, cells);
   }
}
