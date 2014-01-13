/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IMenuEntry {

    /**
     * Contains a strategy specific implementation.
     */
    void action();

    /**
     * Menu command
     * @return
     */
    String command();

    /**
     * Menu entry description
     * @return
     */
    String description();
}