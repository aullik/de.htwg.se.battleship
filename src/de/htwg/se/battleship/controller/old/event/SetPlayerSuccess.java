/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.Round;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class SetPlayerSuccess extends AbstractEvent {

   /**
    * Create instance of SetPlayerSuccess.
    *
    * @param round Round
    */
   public SetPlayerSuccess(Round round) {
      super(round);
   }

}
