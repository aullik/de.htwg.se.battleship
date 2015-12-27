/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class Winner extends AbstractEvent {

   /**
    * Create an instance of Winner.
    *
    * @param round OLDRound
    */
   public Winner(OLDRound round) {
      super(round);
   }
}