package de.htwg.se.battleship.controller.gamemode;

import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.controller.Controllable;

import java.util.function.Consumer;

/**
 * Interface for all Gamemodes
 * <p>
 * Created by aullik on 29.11.2015.
 */
public interface GamemodeControllable extends Controllable {

   void setInitGameController(InitGameController cont);

   void setInGameController(IngameController cont);

   void getWinner(Consumer<Player> cons);

   void endGame();

}
