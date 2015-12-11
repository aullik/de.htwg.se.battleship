package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;

import java.util.List;

/**
 * Ship with a size of 4
 * Created by aullik on 04.12.2015.
 */
public class ShipSize4 extends PossibleShip {

   public ShipSize4(final List<RCell> cells) {
      super(4, cells);
   }
}
