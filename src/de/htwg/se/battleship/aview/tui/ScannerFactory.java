/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.util.Scanner;

import javax.inject.Singleton;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
@Singleton
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
