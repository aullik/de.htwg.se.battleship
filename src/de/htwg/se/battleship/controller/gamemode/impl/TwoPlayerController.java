package de.htwg.se.battleship.controller.gamemode.impl;

import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllerBase;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.ingame.IngameSynchronizingController;
import de.htwg.se.battleship.controller.ingame.impl.IngameControllerImpl;
import de.htwg.se.battleship.controller.ingame.impl.IngameSynchronizingControllerImpl;
import de.htwg.se.battleship.controller.ingame.impl.OpponentCellShooter;
import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.model.readwrite.RWPlayer;

import java.util.List;

/**
 * Controller for a Standard 2 player game
 * Created by aullik on 29.11.2015.
 */
public class TwoPlayerController extends GamemodeControllerBase<GamemodeControllable> {

   private final static String P1FallbackName = "Player1";
   private final static String P2FallbackName = "Player2";


   private RWPlayer player1 = null;
   private RWPlayer player2 = null;

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

      final IngameSynchronizingController synchro = new IngameSynchronizingControllerImpl(platform, this::checkResult);

      final IngameController inCont1 = new IngameControllerImpl(platform, player1,
            new OpponentCellShooter(player2.getGrid()), synchro);
      final IngameController inCont2 = new IngameControllerImpl(platform, player2,
            new OpponentCellShooter(player1.getGrid()), synchro);

      executePlayer1ConsumerMethod(gmc -> gmc.setInGameController(inCont1));
      executePlayer2ConsumerMethod(gmc -> gmc.setInGameController(inCont2));
   }

   private void checkResult(List<RPlayer> winningPlayers) {

   }


   @Override
   protected void start() {
      final InitGameControllerImpl initCont1 = createThreadSaveController(p ->
            new InitGameControllerImpl(p, P1FallbackName, this::setPlayer1));
      final InitGameControllerImpl initCont2 = createThreadSaveController(p ->
            new InitGameControllerImpl(p, P2FallbackName, this::setPlayer2));

      executePlayer1ConsumerMethod(gmc -> gmc.setInitGameController(initCont1));
      executePlayer2ConsumerMethod(gmc -> gmc.setInitGameController(initCont2));
   }

   @Override
   public void abortGame() {
      executePlayer1ConsumerMethod(GamemodeControllable::abortGame);
      executePlayer2ConsumerMethod(GamemodeControllable::abortGame);

   }
}
