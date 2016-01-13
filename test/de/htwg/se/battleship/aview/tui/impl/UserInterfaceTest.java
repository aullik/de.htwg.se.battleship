/**
 *
 */
package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.controller.old.event.CloseProgamm;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class UserInterfaceTest {

   private class TestClass extends UserInterface {

      @Override
      public void showText() {
      }

      @Override
      public UserInterface executeInput(String input) {
         return null;
      }
   }

   private TestAppender testAppender;

   @Before
   public void setUp() {
      testAppender = new TestAppender();
      Logger.getRootLogger().removeAllAppenders();
      Logger.getRootLogger().addAppender(testAppender);
   }

   @Test
   public void test() {
      UserInterface ui = new TestClass();
      ui.update(new CloseProgamm());
      assertTrue(testAppender.getLog().isEmpty());

      ui.output(ui.header());
      assertFalse(testAppender.getLog().isEmpty());

      assertTrue(ui.getProcess());
      ui.deactivateProcess();
      assertFalse(ui.getProcess());

      ui.update(null);
   }
}