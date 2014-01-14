/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameController extends Observable implements IInitGameController {

    @Override
    public void init() {
        notifyObservers(new SetPlayer());
    }
}
