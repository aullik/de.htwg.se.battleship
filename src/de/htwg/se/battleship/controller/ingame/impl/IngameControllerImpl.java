package de.htwg.se.battleship.controller.ingame.impl;

import de.htwg.se.battleship.controller.ingame.IngameControllable;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.ingame.IngameSynchronizingControllable;
import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;
import de.htwg.se.battleship.util.platform.ThreadPlatform;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author aullik on 05.01.2016.
 */
public class IngameControllerImpl extends ThreadSaveControllerBase<IngameControllable> implements IngameController,
      IngameSynchronizingControllable {

   private final RWPlayer player;
   private final Consumer<REnemyCell> shootCell;

   public IngameControllerImpl(final ThreadPlatform platform, final RWPlayer player, Consumer<REnemyCell> shootCell) {
      super(platform);
      this.player = player;
      this.shootCell = shootCell;
   }

   @Override
   public RPlayer getPlayer() {
      return player;
   }

   @Override
   public Optional<RPlayer> checkGameLost() {
      return null;
   }

   @Override
   public void awaitCellShot(final SingleUseRunnable runnable) {

   }
}
