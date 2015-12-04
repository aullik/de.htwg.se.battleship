/**
 *
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.gamemode.GamemodeController;
import de.htwg.se.battleship.controller.gamemode.SharedScreenController;
import de.htwg.se.battleship.util.controller.impl.ControllerBase;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

/**
 * @author aullik on 27.11.2015.
 */
public class GameStateControllerImpl extends ControllerBase<GameStateControllable> implements GameStateController {

   private static final SingletonSupplier<GameStateControllerImpl> INST_SUPP = new SingletonSupplier<>
         (GameStateControllerImpl::new);

   public static GameStateControllerImpl getInstance() {
      return INST_SUPP.get();
   }


   /**
    * Initialize OLDInitGameController
    */
   private GameStateControllerImpl() {


   }

   @Override
   public void startNewSharedScreenGame() {

      final GamemodeController gmController = new SharedScreenController();
      executeConsumerMethod(cont -> cont.startNewGame(gmController));
   }
}
