/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.util.Scanner;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public interface IScannerFactory {

    /**
     * Returns instance of Scanner
     * @return Scanner
     */
    Scanner getScanner();
}