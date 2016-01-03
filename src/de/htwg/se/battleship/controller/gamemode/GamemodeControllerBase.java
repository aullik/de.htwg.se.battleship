package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.util.controller.impl.GamePlatform;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveController;

/**
 * BaseClass for {@link de.htwg.se.battleship.controller.gamemode.GamemodeController}
 * Created by aullik on 29.11.2015.
 */
public abstract class GamemodeControllerBase extends ThreadSaveController<GamemodeControllable> implements
      GamemodeController {


   public GamemodeControllerBase() {
      super(createPlatform());
   }

   private static class MyGamePlatform extends GamePlatform {}

   private static GamePlatform createPlatform() {
      return new MyGamePlatform();
   }

   @Override
   public final void run() {
      platform.runLater(this::start);
      runPlatform();
   }

   /**
    * Start this gamemode, Current Thread will be used as GameThread
    */
   public abstract void start();

}
