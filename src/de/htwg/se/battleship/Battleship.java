package de.htwg.se.battleship;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.tui.impl.TextUI;

/**
 * Initiate logging and dependency Injector and start battleship application.
 * 
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public final class Battleship {

    /**
     * Start Battleship application.
     */
    protected Battleship(AbstractModule module) {
        PropertyConfigurator.configure("log4j.properties");

        Injector injector = Guice.createInjector(module);
        injector.getInstance(MainFrame.class);
        injector.getInstance(TextUI.class);
    }

    /**
     * Starting point for a java application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        new Battleship(new BattleshipModule());
    }
}