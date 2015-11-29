/**
 *
 */
package de.htwg.se.battleship.controller.old.impl;

import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;
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
   public void startNewSplitscreenGame() {

      final InitGameControllerImpl initGameController = new InitGameControllerImpl();
      execute(cont -> cont.startNewGame(initGameController));
   }
}
