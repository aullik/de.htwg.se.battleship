/**
 *
 */
package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.controller.old.impl.OLDInitGameController;
import de.htwg.se.battleship.util._observer.Event;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
@Deprecated
public class InitGame implements Event {

   private final OLDInitGameController controller;

   /**
    * OLDController for game initialization.
    *
    * @param controller IControllerFactory
    */
   public InitGame(OLDInitGameController controller) {
      this.controller = controller;
   }

   public OLDInitGameController getController() {
      return controller;
   }
}
