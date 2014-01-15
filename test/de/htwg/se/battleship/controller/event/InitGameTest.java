/**
 * 
 */
package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.controller.IControllerFactory;
import de.htwg.se.battleship.controller.IInitGameController;

/**
 * @author Philipp
 *
 */
public class InitGameTest {

    private class TestClass implements IControllerFactory {

        @Override
        public IInitGameController createIInitGameController() {
            return null;
        }
    }

    @Test
    public void test() {
        IControllerFactory f = new TestClass();
        InitGame e = new InitGame(f);
        assertEquals(f, e.getFactory());
    }

}
