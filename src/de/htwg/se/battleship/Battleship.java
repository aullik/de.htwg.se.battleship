package de.htwg.se.battleship;

import de.htwg.se.battleship.controller.impl.Controller;
import de.htwg.se.battleship.view.tui.MainMenu;

/**
 * Initial class to start java program
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public final class Battleship {

    private Battleship() {
        new MainMenu(new Controller(), System.in, System.out);
    }

    /**
     * Start java program. Init Controller and UIs
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Battleship();
    }

}
