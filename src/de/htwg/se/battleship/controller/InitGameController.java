/**
 * 
 */
package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetShips;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class InitGameController extends Observable implements IInitGameController {

    public static final String MSG_PLAYER_EMPTY = "Player %s name is empty";
    public static final String P1               = "one";
    public static final String P2               = "two";

    @Override
    public void init() {
        notifyObservers(new SetPlayer());
    }

    @Override
    public void player(String p1, String p2) {
        checkEmpty(p1, String.format(MSG_PLAYER_EMPTY, P1));
        checkEmpty(p2, String.format(MSG_PLAYER_EMPTY, P2));

        notifyObservers(new SetShips());
    }

    private void checkEmpty(String s, String message) {
        if (s == null || s.equals("")) {
            throw new IllegalArgumentException(message);
        }
    }
}
