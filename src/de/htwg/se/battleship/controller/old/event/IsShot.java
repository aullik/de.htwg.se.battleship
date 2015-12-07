/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDCell;
import de.htwg.se.battleship.model.old.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class IsShot extends AbstractEvent {

   private final OLDCell cell;

   /**
    * Create an instance of IsShot.
    *
    * @param round Round
    */
   public IsShot(Round round, OLDCell cell) {
      super(round);

      this.cell = cell;
   }

   /**
    * @return OLDCell
    */
   public OLDCell getCell() {
      return cell;
   }
}