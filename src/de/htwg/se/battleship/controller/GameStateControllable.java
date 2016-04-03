package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.selectPlayer.SelectPlayerControllable;
import de.htwg.se.battleship.util.controller.Controllable;

import java.util.function.Consumer;

/**
 * Created by aullik on 27.11.2015.
 */
public interface GameStateControllable extends Controllable {

   void startNewGame(Consumer<GamemodeControllable> consumer);

   void startNewUiVsUiGame(Consumer<SelectPlayerControllable> consumer);

   String getName();

}
