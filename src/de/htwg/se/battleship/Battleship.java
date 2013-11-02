package de.htwg.se.battleship;

/**
 * Initial class to start java program
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public final class Battleship {

    private static Battleship instance = null;

    private Battleship() {
    }

    /**
     * Return always the same instance of Battleship
     * @return Battleship instance
     */
    public static Battleship getInstance() {
        if (instance == null) {
            instance = new Battleship();
        }

        return instance;
    }

    /**
     * Start java program
     * @param args
     */
    public static void main(String[] args) {
        Battleship.getInstance();
    }

}
