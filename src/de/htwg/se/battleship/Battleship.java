package de.htwg.se.battleship;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import de.htwg.se.battleship.aview.tui.TextUI;
import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.impl.Controller;

/**
 * Initial class to start java program
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public final class Battleship {

    private static Battleship    instance = null;

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

    /**
     * close main
     */
    private Battleship() {
        PropertyConfigurator.configure("log4j.properties");
        IController controller = new Controller();
        new TextUI(controller, new Scanner(System.in), new MainMenu(controller).get());
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
