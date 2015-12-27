/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.OLDGrid;
import de.htwg.se.battleship.model.old.OLDPlayer;
import de.htwg.se.battleship.model.old.OLDRound;
import de.htwg.se.battleship.util._observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public abstract class AbstractEvent implements Event {

   private final OLDRound round;

   /**
    * Create instance of an AbstractEvent.
    *
    * @param round OLDRound
    */
   public AbstractEvent(OLDRound round) {
      this.round = round;
   }

   /**
    * Returns a instance of OLDRound.
    *
    * @return OLDRound
    */
   public OLDRound getRound() {
      return round;
   }

   /**
    * Returns a instance of OLDGrid.
    *
    * @return OLDGrid
    */
   public OLDGrid getGrid() {
      return getRound().getGrid();
   }

   /**
    * Returns a instance of OLDPlayer.
    *
    * @return OLDPlayer
    */
   public OLDPlayer getPlayer() {
      return getGrid().getPlayer();
   }

   /**
    * Returns a instance of OLDCell.
    *
    * @param x X-coordinate
    * @param y Y-coordinate
    * @return OLDCell
    */
   public OLDCell getCell(int x, int y) {
      return getGrid().getCell(x, y);
   }

}
