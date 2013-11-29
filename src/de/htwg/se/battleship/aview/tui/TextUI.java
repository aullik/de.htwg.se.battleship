package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * Text User Interface is an Observer
 * 
 * @author aullik
 */
public class TextUI implements IObserver {

    private final static String       HELLOWORLD = "Hello World";
    private final String        newLine    = System.getProperty("line.separator");
    private final Logger        logger     = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    @SuppressWarnings("unused")
    private final IntController mycontroller;

    public TextUI(IntController controller) {
        this.mycontroller = controller;
        controller.addObserver(this);

    }

    @Override
    public void update(Event e) {
        printTUI();
    }

    /**
     * prints status to stdout and logger
     */
    private void printTUI() {
    	System.out.println(HELLOWORLD);
    	logger.info(newLine + HELLOWORLD);
        
    }

}
