package de.htwg.se.battleship.aview.tui;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.IntController;

public class TextUITest {

    static Logger         logger = Logger.getLogger(TextUITest.class);
    @SuppressWarnings("unused")
    private TextUI        tui1;
    private IntController controller1;

    @Before
    public void setUp() {
        PropertyConfigurator.configure("log4j.properties");
        controller1 = new InputController();
        tui1 = new TextUI(controller1);

    }

    @Test
    public void test() {
        try {
            controller1.notifyObservers(null);
        } catch (NullPointerException n) {

        }
    }
}
