package de.htwg.se.battleship.model.impl;

import de.htwg.se.battleship.model.read.RCell;

import java.util.List;

/**
 * Ship with a size of 5
 * Created by aullik on 04.12.2015.
 */
public class ShipSize5 extends PossibleShip {

   public ShipSize5(final List<RCell> cells) {
      super(5, cells);
   }
}
