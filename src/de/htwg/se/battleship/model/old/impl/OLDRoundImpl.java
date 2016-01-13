/**
 *
 */
package de.htwg.se.battleship.model.old.impl;

import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class OLDRoundImpl implements OLDRound {

   private final OLDGrid[] grids;
   private int index;

   /**
    * Creates a OLDRound instance.
    *
    * @param g1 OLDGrid
    * @param g2 OLDGrid
    */
   public OLDRoundImpl(OLDGrid g1, OLDGrid g2) {
      grids = new OLDGrid[2];
      index = 0;
      grids[0] = g1;
      grids[1] = g2;
   }

   @Override
   public void next() {
      index = calc();
   }

   private int calc() {
      return (index + 1) % 2;
   }

   @Override
   public OLDGrid getGrid() {
      return grids[index];
   }

   @Override
   public OLDGrid getOpponentGrid() {
      return grids[calc()];
   }

}
