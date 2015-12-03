package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.ThreadSaveController;

/**
 * Created by aullik on 29.11.2015.
 */
public abstract class GamemodeControllerBase extends ThreadSaveController<GamemodeControllable> implements
      GamemodeController {


   public GamemodeControllerBase() {
      super(createPlatform());
   }

   private static class GamePlatform extends _Platform {}

   private static _Platform createPlatform() {
      return new GamePlatform();
   }

   @Override
   public final void run() {
      runLater(this::start);
      runPlatform();
   }

   /**
    * Start this gamemode, Current Thread will be used as GameThread
    */
   public abstract void start();

}