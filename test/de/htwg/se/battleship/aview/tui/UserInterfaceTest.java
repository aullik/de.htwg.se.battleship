/**
 * 
 */
package de.htwg.se.battleship.aview.tui;

import java.util.Scanner;

import org.junit.Test;
import de.htwg.se.battleship.controller.event.CloseProgamm;

/**
 * @author Philipp
 *
 */
public class UserInterfaceTest {

    private class TestClass extends UserInterface {

        public TestClass(Scanner scanner) {
            super(scanner);
        }

    }

    @Test(expected=IllegalArgumentException.class)
    public void test() {
        new TestClass(null).update(new CloseProgamm());
    }

}
