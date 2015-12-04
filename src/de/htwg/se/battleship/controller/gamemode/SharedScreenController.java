package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;

/**
 * Created by aullik on 29.11.2015.
 */
public class SharedScreenController extends GamemodeControllerBase implements GamemodeController {

   private final static String P1 = "Player1";
   private final static String P2 = "Player2";

   private final InitGameControllerImpl initCont1;
   private final InitGameControllerImpl initCont2;

   public SharedScreenController() {
      this.initCont1 = createThreadSaveController(p -> new InitGameControllerImpl(p, P1));
      this.initCont2 = createThreadSaveController(p -> new InitGameControllerImpl(p, P2));
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
