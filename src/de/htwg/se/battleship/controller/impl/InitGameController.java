/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetShips;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
@Singleton
public class InitGameController extends Observable implements IInitGameController {

    public static final String MSG_PLAYER_EMPTY = "Player %s name is empty";
    public static final String P1               = "one";
    public static final String P2               = "two";

    private final IModelFabric fabric;

    @Inject
    public InitGameController(IModelFabric fabric) {
        this.fabric = fabric;
    }

    @Override
    public void init() {
        notifyObservers(new SetPlayer());
    }

    @Override
    public void player(String p1, String p2) {
        checkEmpty(p1, String.format(MSG_PLAYER_EMPTY, P1));
        checkEmpty(p2, String.format(MSG_PLAYER_EMPTY, P2));

        IPlayer player1 = fabric.createPlayer(p1);
        IPlayer player2 = fabric.createPlayer(p2);

        IGrid[] grids = new IGrid[2];
        grids[0] = fabric.createGrid(player1);
        grids[1] = fabric.createGrid(player2);

        notifyObservers(new SetShips());
    }

    private void checkEmpty(String s, String message) {
        if (s == null || s.equals("")) {
            //TODO should not be done with an exception, because only requested TUI gets it
            throw new IllegalArgumentException(message);
        }
    }
}
