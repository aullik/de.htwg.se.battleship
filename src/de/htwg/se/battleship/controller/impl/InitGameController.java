/**
 * 
 */
package de.htwg.se.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.model.IGrid;
import de.htwg.se.battleship.model.IModelFabric;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.model.IRound;
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
    private IRound round;

    /**
     * Create InitGameController with an instance of IModelFabric.
     * @param fabric IModelFabric
     */
    @Inject
    public InitGameController(IModelFabric fabric) {
        this.fabric = fabric;
    }

    @Override
    public void init() {
        notifyObservers(new SetPlayer());
        notifyObservers(new SetShip(round));
    }

    @Override
    public void player(String p1, String p2) {
        checkEmpty(p1, String.format(MSG_PLAYER_EMPTY, P1));
        checkEmpty(p2, String.format(MSG_PLAYER_EMPTY, P2));

        IPlayer player1 = fabric.createPlayer(p1);
        IPlayer player2 = fabric.createPlayer(p2);

        IGrid g1 = fabric.createGrid(player1);
        IGrid g2 = fabric.createGrid(player2);
        round = fabric.createRound(g1, g2);

        notifyObservers(new SetPlayerSuccess(round));
    }

    private void checkEmpty(String s, String message) {
        if (s == null || s.equals("")) {
            //TODO should not be done with an exception, because only requested TUI gets it
            throw new IllegalArgumentException(message);
        }
    }

    @Override
    public void ship(int startX, int startY, int endX, int endY) {

    }
}
