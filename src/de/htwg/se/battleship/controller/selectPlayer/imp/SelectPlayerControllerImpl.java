package de.htwg.se.battleship.controller.selectPlayer.imp;

import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerControllable;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerController;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;
import de.htwg.se.battleship.util.platform.ThreadPlatform;

/**
 * Created by niwehrle on 23.03.2016.
 */
public class SelectPlayerControllerImpl extends ThreadSaveControllerBase<SelectPlayerControllable> implements SelectPlayerController {

   public SelectPlayerControllerImpl() {
      super(new ThreadPlatform());
      runPlatform();
      platform.runLater(this::runLoop);
   }

   private void runLoop(){

   }

}
