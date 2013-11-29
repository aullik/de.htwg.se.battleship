package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * Controller is observable
 * 
 * @author aullik
 */
public class Controller extends Observable implements IntController {

    private Player player1;
    private Player player2;
    private Grid   grid1;
    private Grid   grid2;

    @Override
    public void updateNotify() {
        notifyObservers(null);
    }

    @Override
    public void newgame() {
        final int stdFieldSize = 10;
        final String playername1 = "Player1";
        final String playername2 = "Player2";

        newgame(stdFieldSize, playername1, playername2);

    }

    @Override
    public void newgame(final int size, final String playername1,
            final String playername2) {
        grid1 = new Grid(size, player1);
        grid2 = new Grid(size, player2);
        player1 = new Player(playername1);
        player2 = new Player(playername2);
    }
}
