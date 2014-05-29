/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import com.google.inject.Singleton;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.ModelFabric;
import de.htwg.se.battleship.model.impl.ModelFabricImpl;
import de.htwg.se.battleship.util.observer.impl.ObservableImpl;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
@Singleton
public class Controller extends ObservableImpl implements IController {

    private final InitGameController initGame;

    /**
     * Initialize InitGameController
     */
    public Controller() {
        ModelFabric fabric = new ModelFabricImpl();
        initGame = new InitGameController(fabric);
    }

    @Override
    public void newGame() {
        notifyObservers(new InitGame(initGame));
    }

    @Override
    public void close() {
        notifyObservers(new CloseProgamm());
    }

    @Override
    public void reset() {
        notifyObservers(new Winner(null));
    }
}
