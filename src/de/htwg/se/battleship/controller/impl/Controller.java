package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.controller.event.CloseEvent;
import de.htwg.se.battleship.controller.event.NewGameEvent;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * Controller is observable
 * 
 * @author aullik
 */
public class Controller extends Observable implements IntController {

    @Override
    public void exit() {
        notifyObservers(new CloseEvent());
    }

    @Override
    public void newGame() {
        notifyObservers(new NewGameEvent());
    }
}
