package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.controller.Controller;

/**
 * Keeps track of the gamestate
 * <p>
 *
 * @author aullik on 27.11.2015.
 */
public interface GameStateController extends Controller<GameStateControllable> {

   void startNewGame();

}
