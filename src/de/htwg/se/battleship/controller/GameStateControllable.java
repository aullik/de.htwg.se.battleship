package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.gamemode.GamemodeController;
import de.htwg.se.battleship.util.controller.Controllable;

/**
 * Created by aullik on 27.11.2015.
 */
public interface GameStateControllable extends Controllable {

   void startNewSharedScreenGame(GamemodeController gmController);

   String getName();

}
