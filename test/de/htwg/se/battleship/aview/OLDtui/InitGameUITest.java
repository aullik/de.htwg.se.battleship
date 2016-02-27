/**
 *
 */
package de.htwg.se.battleship.aview.OLDtui;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.event.SetPlayer;
import de.htwg.se.battleship.util._observer.impl.ObservableImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class InitGameUITest {

   private InitGameUI ui;
   private TestAppender testAppender;

   private class TestClass extends ObservableImpl implements IInitGameController {

      @Override
      public void init() {
      }

      @Override
      public void player(String p1, String p2) {
      }

      @Override
      public void ship(Integer startX, Integer startY, Integer endX, Integer endY) {
      }

      @Override
      public void shot(Integer x, Integer y) {
      }
   }

   @Before
   public void setUp() {
      ui = new InitGameUI(new TestClass());

      testAppender = new TestAppender();
      Logger.getRootLogger().removeAllAppenders();
      Logger.getRootLogger().addAppender(testAppender);
   }

   @Test
   public void testUpdateSetPlayer() {
      assertTrue(testAppender.getLog().isEmpty());
      ui.update(new SetPlayer());
      assertTrue(testAppender.getLog().isEmpty());
   }
}