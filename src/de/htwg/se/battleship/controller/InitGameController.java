/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.impl.Player;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameController extends Observable implements IInitGameController {

    private IPlayer p1;
    private IPlayer p2;

    @Override
    public void init() {
        notifyObservers(new SetPlayer());
    }

    @Override
    public void player(String p1, String p2) {
        checkEmpty(p1, "Player one name is empty");
        checkEmpty(p2, "Player two name is empty");

        this.p1 = new Player(p1);
        this.p2 = new Player(p2);
    }

    private void checkEmpty(String s, String message) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException(message);
        }
    }
}
