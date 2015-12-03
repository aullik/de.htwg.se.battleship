package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.initgame.impl.InitGameControllerImpl;

/**
 * Created by aullik on 29.11.2015.
 */
public class SharedScreenController extends GamemodeControllerBase implements GamemodeController {

   private final InitGameControllerImpl initCont1;
   private final InitGameControllerImpl initCont2;

   public SharedScreenController() {
      this.initCont1 = new InitGameControllerImpl();
      this.initCont2 = new InitGameControllerImpl();
   }

   @Override
   public void start() {
      executeConsumerMethod(gmc -> gmc.setInitGameController(initCont1));
   }
}
