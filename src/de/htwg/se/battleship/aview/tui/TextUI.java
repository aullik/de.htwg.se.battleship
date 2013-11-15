package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

public class TextUI implements IObserver {

    private static String HELLOWORLD = "Hello World";
    private final String newLine = System.getProperty("line.separator");
    private final Logger logger = Logger
            .getLogger("de.htwg.se.battleship.aview.tui");
    @SuppressWarnings("unused")
    private final IntController controller;

    public TextUI(IntController controller) {
        this.controller = controller;
        controller.addObserver(this);

    }

    @Override
    public void update(Event e) {
        printTUI();
    }

    private void printTUI() {
        logger.info(newLine + HELLOWORLD);
        System.out.println(HELLOWORLD);
    }

}
