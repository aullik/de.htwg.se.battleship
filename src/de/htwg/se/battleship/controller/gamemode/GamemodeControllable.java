package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.platform.SingleUseRunnable;

/**
 * Interface for all Gamemodes
 * <p>
 * Created by aullik on 29.11.2015.
 */
public interface GamemodeControllable extends Controllable {

   void setInitGameController(InitGameController cont);

   void setInGameController(IngameController cont);

   void setSurrenderGameExecutable(SingleUseRunnable surrenderGame);

   void setWinner(RPlayer player);

}
