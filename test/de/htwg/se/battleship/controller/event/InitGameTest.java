package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.ModelFabric;

public class InitGameTest {

    private class TestController extends InitGameController {

        public TestController(ModelFabric fabric) {
            super(fabric);
        }

    }

    @Test
    public void test() {
        TestController controller = new TestController(null);
        InitGame event = new InitGame(controller);
        assertEquals(controller, event.getController());
    }

}
