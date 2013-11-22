package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.Battleship;
import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
public class TextUI implements IObserver {

    private static String       HELLOWORLD = "Hello World";
    private final String        newLine    = System.getProperty("line.separator");
    private final Logger        logger     = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    private final IntController controller;

    public TextUI(IntController controller) {
        this.controller = controller;
        controller.addObserver(this);

    }

    /**
     * switches thought input to get commands
     * 
     * @param in input from console
     */
    public void processInput(final String in) {
        switch (in) {
        case "close":
            logger.info(newLine + "terminated");
            Battleship.close();
            break;
        case "start":
            controller.newgame();
            break;
        default:
            System.out.println("Unknown Input: " + in);

        }

    }

    @Override
    public void update(Event e) {
        printTUI();
    }

    /**
     * prints status to stdout and logger
     */
    private void printTUI() {
        logger.info(newLine + HELLOWORLD);
        // System.out.println(HELLOWORLD);
    }

}
