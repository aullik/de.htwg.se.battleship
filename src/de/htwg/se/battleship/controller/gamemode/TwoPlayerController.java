package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;
import de.htwg.se.battleship.model.readwrite.RWPlayer;

/**
 * Created by aullik on 29.11.2015.
 */
public class TwoPlayerController extends GamemodeControllerBase<GamemodeControllable> {

   private final static String P1 = "Player1";
   private final static String P2 = "Player2";

   private final InitGameControllerImpl initCont1;
   private final InitGameControllerImpl initCont2;

   private RWPlayer player1 = null;
   private RWPlayer player2 = null;

   public TwoPlayerController() {
      this.initCont1 = createThreadSaveController(p -> new InitGameControllerImpl(p, P1, this::setPlayer1));
      this.initCont2 = createThreadSaveController(p -> new InitGameControllerImpl(p, P2, this::setPlayer2));
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

      }
   }


   @Override
   protected void start() {
      executePlayer1ConsumerMethod(gmc -> gmc.setInitGameController(initCont1));
      executePlayer2ConsumerMethod(gmc -> gmc.setInitGameController(initCont2));
   }

   @Override
   public void abortGame() {
      // TODO
   }
}
