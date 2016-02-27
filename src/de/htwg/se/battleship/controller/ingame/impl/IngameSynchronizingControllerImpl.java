package de.htwg.se.battleship.controller.ingame.impl;

import de.htwg.se.battleship.controller.ingame.IngameSynchronizingControllable;
import de.htwg.se.battleship.controller.ingame.IngameSynchronizingController;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.controller.impl.ThreadSaveControllerBase;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;
import de.htwg.se.battleship.util.platform.ThreadPlatform;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author aullik on 05.01.2016.
 */
public class IngameSynchronizingControllerImpl extends ThreadSaveControllerBase<IngameSynchronizingControllable>
      implements IngameSynchronizingController, Runnable {

   private final Consumer<List<RPlayer>> onFinish;
   private final List<RPlayer> registeredPlayers;
   private final List<RPlayer> resultPlayersLost;

   private boolean running = false;

   public IngameSynchronizingControllerImpl(ThreadPlatform platform, Consumer<List<RPlayer>> onFinish) {
      super(platform);
      this.onFinish = onFinish;
      this.registeredPlayers = new ArrayList<>();
      resultPlayersLost = new LinkedList<>();
   }

   @Override
   public void registerControllable(final IngameSynchronizingControllable cont) {
      registeredPlayers.add(cont.getPlayer());
      super.registerControllable(cont);
   }

   @Override
   public void unregisterControllable(final IngameSynchronizingControllable cont) {
      registeredPlayers.remove(cont.getPlayer());
      super.unregisterControllable(cont);
   }


   @Override
   public void run() {
      if (running)
         throw new IllegalStateException("Already running");

      running = true;
      platform.runLater(this::checkResult);

   }

   private void checkResult() {
      executeConsumerMethod(this::checkSingleResult);
      if (resultPlayersLost.isEmpty())
         platform.runLater(this::playRound);
      else if (resultPlayersLost.size() == registeredPlayers.size())
         onFinish.accept(resultPlayersLost);
      else {
         List<RPlayer> ret = new ArrayList<>(registeredPlayers);
         resultPlayersLost.forEach(ret::remove);
         onFinish.accept(ret);
      }

   }

   private void checkSingleResult(IngameSynchronizingControllable cont) {
      final Optional<RPlayer> opt = cont.checkGameLost();
      if (opt.isPresent())
         resultPlayersLost.add(opt.get());
   }

   private void playRound() {
      AtomicInteger playersShooting = new AtomicInteger();
      executeConsumerMethod((cont) -> awaitCellsShot(cont, playersShooting));
   }

   private void awaitCellsShot(IngameSynchronizingControllable cont, final AtomicInteger playersShooting) {
      playersShooting.incrementAndGet();
      cont.awaitCellShot(new SingleUseRunnable(() -> awaitAllResponses(playersShooting), platform, false));
   }

   private void awaitAllResponses(AtomicInteger playersShooting) {
      if (playersShooting.decrementAndGet() == 0)
         //next Step
         platform.runLater(this::checkResult);
   }

}
