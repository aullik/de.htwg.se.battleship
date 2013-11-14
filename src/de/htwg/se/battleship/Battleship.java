package de.htwg.se.battleship;

import org.apache.log4j.PropertyConfigurator;

public class Battleship {

    private static Battleship instance = null;

    public static Battleship getInstance() {
        if (instance == null) {
            instance = new Battleship();
        }
        return instance;
    }

    public Battleship() {
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
    }

    public static void main(String[] args) {
        Battleship.getInstance();
    }

}
