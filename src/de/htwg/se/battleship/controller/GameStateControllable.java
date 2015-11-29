package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.util.controller.Controllable;

/**
 * Created by aullik on 27.11.2015.
 */
public interface GameStateControllable extends Controllable {

   void startNewGame(InitGameController initCont);

}