package de.htwg.se.battleship;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.se.battleship.aview.tui.TextUI;
import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.controller.impl.Controller;

/**
 * Initial class to start java program
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public final class Battleship {

    private static Battleship    instance = null;

    @SuppressWarnings("unused")
    private static TextUI        tui;
    private static IntController controller;

    /**
     * Return always the same instance of Battleship
     * 
     * @return Battleship instance
     */
    public static Battleship getInstance() {
        if (instance == null) {
            instance = new Battleship();
        }
        return instance;
    }

    public Battleship() {
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
        controller = new Controller();
        tui = new TextUI(controller);

    }

    /**
     * Start java program
     * 
     * @param args
     */

    public static void main(String[] args) {
        Battleship.getInstance();
    }

}
