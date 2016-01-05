/**
 *
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.gamemode.GamemodeController;
import de.htwg.se.battleship.controller.gamemode.TwoPlayerController;
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

      final GamemodeController gmController = new TwoPlayerController();
      executeConsumerMethod(cont -> cont.startNewSharedScreenGame(gmController));
   }

   @Override
   public void registerControllable(final GameStateControllable cont) {
      controllables.add(cont);
   }

   @Override
   public void unregisterControllable(final GameStateControllable cont) {
      controllables.remove(cont);
   }

   @Override
   public void executeConsumerMethod(final Consumer<GameStateControllable> executor) {
      controllables.forEach(executor);
   }
}
