package de.htwg.se.battleship;


import org.apache.log4j.PropertyConfigurator;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.tui.TextUI;
import com.google.inject.Guice;
import com.google.inject.Injector;

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

        Injector injector = Guice.createInjector(new BattleshipModule());

        injector.getInstance(MainFrame.class);
        injector.getInstance(TextUI.class);
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
