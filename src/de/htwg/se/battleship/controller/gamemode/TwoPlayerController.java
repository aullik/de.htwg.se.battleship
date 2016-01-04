package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;
import de.htwg.se.battleship.model.readwrite.RWPlayer;

/**
 * Created by aullik on 29.11.2015.
 */
public class TwoPlayerController extends GamemodeControllerBase implements GamemodeController {

   private final static String P1 = "Player1";
   private final static String P2 = "Player2";

   private final InitGameControllerImpl initCont1;
   private final InitGameControllerImpl initCont2;

   private RWPlayer player1;
   private RWPlayer player2;

   public TwoPlayerController() {
      this.initCont1 = createThreadSaveController(p -> new InitGameControllerImpl(p, P1, this::setPlayer1));
      this.initCont2 = createThreadSaveController(p -> new InitGameControllerImpl(p, P2, this::setPlayer2));
   }

   private void setPlayer1(RWPlayer player1) {
      this.player1 = player1;
   }

   private void setPlayer2(RWPlayer player2) {
      this.player2 = player2;
   }


   @Override
   public void start() {
      executeConsumerMethod(gmc -> gmc.setInitGameController(initCont1));
   }

   @Override
   public void endGame() {
      // TODO
   }
}
