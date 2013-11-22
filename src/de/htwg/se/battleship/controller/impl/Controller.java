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

    private Player     player1;
    private Player     player2;
    private Grid       grid1;
    private Grid       grid2;
    private static int STDFIELDSIZE = 10;

    @Override
    public void updateNotify() {
        notifyObservers(null);
    }

    @Override
    public void newgame() {
        newgame(STDFIELDSIZE);

    }

    @Override
    public void newgame(final int size) {
        grid1 = new Grid(size);
        grid2 = new Grid(size);
    }

}
