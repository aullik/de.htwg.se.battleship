/**
 *
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllerBase;
import de.htwg.se.battleship.controller.gamemode.impl.TwoPlayerController;
import de.htwg.se.battleship.util.controller.Controller;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author aullik on 27.11.2015.
 */
public class GameStateControllerImpl implements
      GameStateController, Controller<GameStateControllable> {

   private static final SingletonSupplier<GameStateControllerImpl> INST_SUPP = new SingletonSupplier<>
         (GameStateControllerImpl::new);

   public static GameStateControllerImpl getInstance() {
      return INST_SUPP.get();
   }


   private final List<GameStateControllable> controllables;

   /**
    * Initialize InitGameController
    */
   private GameStateControllerImpl() {
      this.controllables = new LinkedList<>();
   }

   @Override
   public void startNewSharedScreenGame() {

      final TwoPlayerController gmController = new TwoPlayerController();
      executeConsumerMethod(cont -> cont.startNewGame(controllable ->
            registerSharedScreenGameControllables(gmController, controllable)));

      gmController.run();
   }

   private <C extends GamemodeControllable> void registerSharedScreenGameControllables(
         GamemodeControllerBase<C> gmController, C controllable) {
      gmController.registerControllablePlayer1(controllable);
      gmController.registerControllablePlayer2(controllable);
   }


   @Override
   public synchronized void registerControllable(final GameStateControllable cont) {
      controllables.add(cont);
   }

   @Override
   public synchronized void unregisterControllable(final GameStateControllable cont) {
      controllables.remove(cont);
   }

   @Override
   public synchronized void executeConsumerMethod(final Consumer<GameStateControllable> executor) {
      controllables.forEach(executor);
   }
}
