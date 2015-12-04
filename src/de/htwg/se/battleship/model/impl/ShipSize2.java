package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.PossibleShip;

import java.util.List;

/**
 * Ship with a size of 2
 * Created by aullik on 04.12.2015.
 */
public class ShipSize2 extends PossibleShip {

   public ShipSize2(final List<Cell> cells) {
      super(2, cells);
   }
}
