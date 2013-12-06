package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.model.Grid;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * Controller is observable
 * 
 * @author aullik
 */
public class MainController extends Observable  {

    private Player player1;
    private Player player2;
    private Grid   grid1;
    private Grid   grid2;

    
    public void updateNotify() {
        notifyObservers(null);
    }

    
    public void newgame() {
        final int stdFieldSize = 10;
        final String playername1 = "Player 1";
        final String playername2 = "Player 2";

        newgame(stdFieldSize, playername1, playername2);

    }

    
    public void newgame(final int size, final String playername1,
            final String playername2) {
    	
    	player1 = new Player(playername1);
        player2 = new Player(playername2);
        grid1 = new Grid(size, player1);
        grid2 = new Grid(size, player2);
       
        notifyObservers(null);
    }
}
