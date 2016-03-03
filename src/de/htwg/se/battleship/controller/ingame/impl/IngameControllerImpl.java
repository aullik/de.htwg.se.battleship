package de.htwg.se.battleship.controller.ingame.impl;

import de.htwg.se.battleship.controller.ingame.IngameControllable;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.ingame.IngameSynchronizingControllable;
import de.htwg.se.battleship.controller.ingame.IngameSynchronizingController;
import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.read.REnemyGrid;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.model.read.RShip;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;
import de.htwg.se.battleship.util.platform.AlreadyExecutedException;
import de.htwg.se.battleship.util.platform.NotUIThreadException;
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
   private final REnemyGrid enemyGrid;
   private final Consumer<REnemyCell> enemyCellShooter;
   private final IngameSynchronizingController synchro;

   public IngameControllerImpl(final ThreadPlatform platform, final RWPlayer player, final REnemyGrid enemyGrid,
                               Consumer<REnemyCell> shootCell,
                               final IngameSynchronizingController synchro) {
      super(platform);
      this.player = player;
      this.enemyGrid = enemyGrid;
      this.enemyCellShooter = shootCell;
      this.synchro = synchro;
      synchro.registerControllable(this);
   }

   @Override
   public RPlayer getPlayer() {
      return player;
   }

   @Override
   public Optional<RPlayer> checkGameLost() {
      for (RShip ship : player.getShips())
         if (!ship.isDestroyed())
            return Optional.empty();

      return Optional.of(player);
   }

   @Override
   public void awaitCellShot(final SingleUseRunnable runnable) {
      executeSingleUse(IngameControllable::shoot, cell -> shootCell(cell, runnable));
   }

   private void shootCell(REnemyCell cell, SingleUseRunnable afterShot) {
      enemyCellShooter.accept(cell);
      try {
         afterShot.run();
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         // this cannot happen as long as this method will be executed inside a SingleUseConsumer
         throw new RuntimeException(e);
      }

   }

   @Override
   public void registerControllable(IngameControllable cont) {
      super.registerControllable(cont);
      cont.initIngameControllable(player, enemyGrid);
   }

   @Override
   public void abort() {
      this.synchro.unregisterControllable(this);
   }
}
