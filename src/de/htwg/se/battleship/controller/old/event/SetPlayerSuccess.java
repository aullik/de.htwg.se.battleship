/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.model.old.OLDRound;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
@Deprecated
public class SetPlayerSuccess extends AbstractEvent {

   /**
    * Create instance of SetPlayerSuccess.
    *
    * @param round OLDRound
    */
   public SetPlayerSuccess(OLDRound round) {
      super(round);
   }

}
