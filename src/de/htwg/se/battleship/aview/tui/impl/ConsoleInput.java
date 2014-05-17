/**
 * 
 */
package de.htwg.se.battleship.aview.tui.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import de.htwg.se.battleship.aview.tui.Input;

/**
 * Read input from console and can be interrupt.
 * 
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class ConsoleInput implements Input {

    private static final int SLEEP_TIME = 10;

    private final Scanner scanner;
    private final InputStream stream;
    private Thread thread;

    /**
     * 
     */
    public ConsoleInput() {
        stream = System.in;
        scanner = new Scanner(stream);
    }

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.tui.Input#get()
     */
    @Override
    public String get() throws IOException {
        String string = "";
        if (hasNextLine()) {
            string = scanner.nextLine();
        }
        return string;
    }

    private boolean hasNextLine() throws IOException {
        thread = Thread.currentThread();
        while(stream.available() == 0) {
            try {
                Thread.currentThread();
                Thread.sleep(ConsoleInput.SLEEP_TIME);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return scanner.hasNextLine();
    }

    @Override
    public void close() {
        thread.interrupt();
    }
}
