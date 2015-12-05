/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class IsShot extends AbstractEvent {

   private final Cell cell;

   /**
    * Create an instance of IsShot.
    *
    * @param round Round
    */
   public IsShot(Round round, Cell cell) {
      super(round);

      this.cell = cell;
   }

   /**
    * @return Cell
    */
   public Cell getCell() {
      return cell;
   }
}