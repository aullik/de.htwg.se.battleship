/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.OLDGrid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.util._observer.Event;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public abstract class AbstractEvent implements Event {

   private final Round round;

   /**
    * Create instance of an AbstractEvent.
    *
    * @param round Round
    */
   public AbstractEvent(Round round) {
      this.round = round;
   }

   /**
    * Returns a instance of Round.
    *
    * @return Round
    */
   public Round getRound() {
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
    * Returns a instance of Player.
    *
    * @return Player
    */
   public Player getPlayer() {
      return getGrid().getPlayer();
   }

   /**
    * Returns a instance of Cell.
    *
    * @param x X-coordinate
    * @param y Y-coordinate
    * @return Cell
    */
   public Cell getCell(int x, int y) {
      return getGrid().getCell(x, y);
   }

}
