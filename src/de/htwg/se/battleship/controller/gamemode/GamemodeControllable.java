package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.controller.Controllable;

/**
 * Interface for all Gamemodes
 * <p>
 * Created by aullik on 29.11.2015.
 */
public interface GamemodeControllable extends Controllable {

   void setPlayer(RPlayer player);

   void setInitGameController(InitGameController cont);

   void setInGameController(IngameController cont);

   void abortGame();

}
