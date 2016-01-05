package de.htwg.se.battleship.controller.ingame.impl;

import de.htwg.se.battleship.controller.ingame.IngameControllable;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.util.controller.impl.GamePlatform;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;

/**
 * @author aullik on 05.01.2016.
 */
public class IngameControllerImpl extends ThreadSaveControllerBase<IngameControllable> implements IngameController {

   public IngameControllerImpl(final GamePlatform platform, final RWPlayer player1) {
      super(platform);
   }
}
