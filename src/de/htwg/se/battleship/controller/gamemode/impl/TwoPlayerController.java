package de.htwg.se.battleship.controller.gamemode.impl;

import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllerBase;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.ingame.impl.IngameControllerImpl;
import de.htwg.se.battleship.controller.ingame.impl.IngameSynchronizingControllerImpl;
import de.htwg.se.battleship.controller.ingame.impl.OpponentCellShooter;
import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.model.readwrite.RWPlayer;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;

import java.util.List;
import java.util.function.Supplier;

/**
 * Controller for a Standard 2 player game
 * Created by aullik on 29.11.2015.
 */
public class TwoPlayerController extends GamemodeControllerBase<GamemodeControllable> {

   private final static String P1FallbackName = "Player 1";
   private final static String P2FallbackName = "Player 2";


   private RWPlayer player1 = null;
   private RWPlayer player2 = null;
   private Runnable abortGame;

   public TwoPlayerController() {
   }

   private void setPlayer1(RWPlayer player1) {
      this.player1 = player1;
      checkPlayersSet();
   }

   private void setPlayer2(RWPlayer player2) {
      this.player2 = player2;
      checkPlayersSet();
   }

   private void checkPlayersSet() {
      if (player1 != null && player2 != null) {
         startIngame();
      }
   }

   private void startIngame() {
      final IngameSynchronizingControllerImpl synchro = new IngameSynchronizingControllerImpl(platform,
            this::checkResult);

      final IngameController inCont1 = new IngameControllerImpl(platform, player1, player2.getGrid(),
            new OpponentCellShooter(player2.getGrid()), synchro);
      final IngameController inCont2 = new IngameControllerImpl(platform, player2, player1.getGrid(),
            new OpponentCellShooter(player1.getGrid()), synchro);

      this.abortGame = () -> {
         inCont1.abort();
         inCont2.abort();
      };

      synchro.run();

      executePlayer1ConsumerMethod(gmc -> gmc.setInGameController(inCont1));
      executePlayer2ConsumerMethod(gmc -> gmc.setInGameController(inCont2));

   }

   private void checkResult(List<RPlayer> winningPlayers) {
      if (winningPlayers.isEmpty())
         throw new IllegalStateException("no winner");

      winningPlayers.forEach(this::setWinner);
   }

   private RPlayer getPlayer1() {
      if (player1 != null)
         return player1;
      else
         return new Player(P1FallbackName);
   }

   private RPlayer getPlayer2() {
      if (player2 != null)
         return player2;
      else
         return new Player(P2FallbackName);
   }

   private void setOnSurrender(final GamemodeControllable gmc, Supplier<RPlayer> winningPlayerSupp) {
      gmc.setSurrenderGameExecutable(new SingleUseRunnable(() -> this.playerSurrendered(winningPlayerSupp), platform));
   }


   private void playerSurrendered(final Supplier<RPlayer> otherPlayer) {
      abortGame.run();
      setWinner(otherPlayer.get());
   }

   private void setWinner(final RPlayer player) {
      executePlayer1ConsumerMethod(cont -> cont.setWinner(player));
      executePlayer2ConsumerMethod(cont -> cont.setWinner(player));
      closePlatform();
   }


   @Override
   protected void start() {
      final InitGameControllerImpl initCont1 = new InitGameControllerImpl(platform, P1FallbackName, this::setPlayer1);
      final InitGameControllerImpl initCont2 = new InitGameControllerImpl(platform, P2FallbackName, this::setPlayer2);

      this.abortGame = () -> {
         initCont1.abort();
         initCont2.abort();
      };

      executePlayer1ConsumerMethod(gmc -> setOnSurrender(gmc, this::getPlayer2)); //P1 surrenders -> P2 wins
      executePlayer2ConsumerMethod(gmc -> setOnSurrender(gmc, this::getPlayer1)); //P2 surrenders -> P1 wins

      executePlayer1ConsumerMethod(gmc -> gmc.setInitGameController(initCont1));
      executePlayer2ConsumerMethod(gmc -> gmc.setInitGameController(initCont2));
   }

}
