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

    private final Logger        logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
    private final IntController mycontroller;

    public TextUI(IntController controller) {
        this.mycontroller = controller;
        mycontroller.addObserver(this);
        mycontroller.updateNotify();

    }

    /**
     * prints status to stdout and logger
     */
    @Override
    public void update(Event e) {
        if (e == null) {
            logger.info("Nullpointer error");
        } else {
            logger.info(e.getMessage());
        }

    }

    /**
     * prints status to stdout and logger
     */

}
