package de.htwg.se.battleship;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.tui.TextUI;

/**
 * Initial class to start java program
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public final class Battleship {

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
        new Battleship();
    }
}