/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.util.Scanner;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class ScannerFactory implements IScannerFactory {

    private final Scanner scanner;

    /**
     * Factory for Scanner instance.
     */
    public ScannerFactory() {
        scanner = new Scanner(System.in);
    }

    @Override
    public Scanner getScanner() {
        return scanner;
    }

}
