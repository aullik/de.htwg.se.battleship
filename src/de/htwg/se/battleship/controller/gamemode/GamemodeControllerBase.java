package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.util.controller.impl.AbstractThreadSave;
import de.htwg.se.battleship.util.controller.impl.ControllerHelper;
import de.htwg.se.battleship.util.platform.ThreadPlatform;

import java.util.function.Consumer;

/**
 * BaseClass for {@link de.htwg.se.battleship.controller.gamemode.GamemodeController}
 * Created by aullik on 29.11.2015.
 */
public abstract class GamemodeControllerBase<C extends GamemodeControllable> extends AbstractThreadSave implements
      GamemodeController<C> {

   private final ControllerHelper<C> player1Consumables;
   private final ControllerHelper<C> player2Consumables;

   public GamemodeControllerBase() {
      super(createPlatform());
      this.player1Consumables = new ControllerHelper<>();
      this.player2Consumables = new ControllerHelper<>();
   }

   @Override
   public void registerControllablePlayer1(final C cont) {
      player1Consumables.registerControllable(cont);
   }

   @Override
   public void unregisterControllablePlayer1(final C cont) {
      player1Consumables.unregisterControllable(cont);
   }


   @Override
   public void executePlayer1ConsumerMethod(final Consumer<C> executor) {
      player1Consumables.executeConsumerMethod(executor);
   }

   @Override
   public void registerControllablePlayer2(final C cont) {
      player2Consumables.registerControllable(cont);
   }

   @Override
   public void unregisterControllablePlayer2(final C cont) {
      player2Consumables.unregisterControllable(cont);
   }

   @Override
   public void executePlayer2ConsumerMethod(final Consumer<C> executor) {
      player2Consumables.executeConsumerMethod(executor);
   }

   private static class GamePlatform extends ThreadPlatform {}

   private static ThreadPlatform createPlatform() {
      return new GamePlatform();
   }

   @Override
   public final void run() {
      platform.runLater(this::start);
      runPlatform();
   }


   /**
    * Start this gamemode, Current Thread will be used as GameThread
    */
   protected abstract void start();

}
