/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IMenu {

    /**
     * Return a list of IMenuEntry.
     * @return Map<String, IMenuEntry>
     */
    Map<String, IMenuEntry> get();
}
