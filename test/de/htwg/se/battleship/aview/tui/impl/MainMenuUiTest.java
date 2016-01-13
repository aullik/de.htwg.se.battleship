package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.aview.tui.InitGameUI;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.SetPlayer;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.util.observer.impl.ObservableImpl;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MainMenuUiTest {

   private MainMenuUi ui;
   private TestAppender testAppender;

   private boolean inputClose;
   private boolean init;
   private boolean menuAction;

   private static final String CMD = "EntryCmd";
   private static final String DESC = "EntryDescription";

   private class TestController extends ObservableImpl implements IController {

      @Override
      public void newGame() {
      }

      @Override
      public void close() {
      }

      @Override
      public void reset() {
      }
   }

   private class TestInitGameController extends ObservableImpl implements IInitGameController {

      public TestInitGameController() {
         init = false;
      }

      @Override
      public void init() {
         init = true;
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

   private class TestUi extends InitGameUI {

      public TestUi(IInitGameController controller) {
         super(controller);
      }

      @Override
      public void update(SetPlayer e) {
      }

      @Override
      public void update(SetShip e) {
      }

      @Override
      public void update(SetPlayerSuccess e) {
      }

      @Override
      public void update(SetShipSuccess e) {
      }
   }

   private class TestInput extends ConsoleInput {

      public TestInput() {
         inputClose = false;
      }

      @Override
      public String getInput() throws IOException {
         return null;
      }

      @Override
      public void close() {
         inputClose = true;
      }

   }

   private class TestMenuEntry implements IMenuEntry {

      public TestMenuEntry() {
         menuAction = false;
      }

      @Override
      public void action() {
         menuAction = true;
      }

      @Override
      public String command() {
         return CMD;
      }

      @Override
      public String description() {
         return DESC;
      }

   }

   private class TestMenu implements IMenu {

      @Override
      public Map<String, IMenuEntry> get() {
         Map<String, IMenuEntry> list = new HashMap<String, IMenuEntry>();
         list.put(CMD, new TestMenuEntry());
         return list;
      }

   }

   @Before
   public void setUp() {
      testAppender = new TestAppender();
      Logger.getRootLogger().removeAllAppenders();
      Logger.getRootLogger().addAppender(testAppender);

      ConsoleInput input = new TestInput();
      IMenu menu = new TestMenu();
      IController controller = new TestController();
      IInitGameController igc = new TestInitGameController();
      InitGameUI igui = new TestUi(igc);
      ui = new MainMenuUi(input, menu, controller, igui, igc);
   }

   @Test
   public void testExecuteInputWithEntryNotExist() throws UnsupportedEncodingException {
      String s = "test";
      ui.executeInput(s);

      String log = testAppender.getLog().toString();
      assertFalse(log.isEmpty());
      assertTrue(log.contains(String.format(MainMenuUi.MSG_DEFAULT_MENU, s)));
   }

   @Test
   public void testUpdateCloseProgamm() throws UnsupportedEncodingException {
      assertTrue(ui.getProcess());
      assertTrue(testAppender.getLog().isEmpty());
      assertFalse(inputClose);

      ui.update(new CloseProgamm());

      assertFalse(ui.getProcess());
      assertFalse(testAppender.getLog().isEmpty());
      assertTrue(testAppender.getLog().contains(MainMenuUi.MSG_EXIT));
      assertTrue(inputClose);
   }

   @Test
   public void testUpdateInitGame() {
      ui.update(new CloseProgamm());

      assertFalse(init);
      assertEquals(ui, ui.executeInput(null));

      ui.update(new InitGame(null));

      assertTrue(init);
      assertNotEquals(ui, ui.executeInput(null));
   }

   @Test
   public void testShowText() {
      assertTrue(testAppender.getLog().isEmpty());
      ui.showText();
      String log = testAppender.getLog();

      assertFalse(log.isEmpty());
      assertTrue(log.contains(String.format(MainMenuUi.MENU_FORMAT, CMD, DESC)));
      assertTrue(log.contains(MainMenuUi.MENU_HEAD));
   }

   @Test
   public void testExecuteInput() {
      assertTrue(testAppender.getLog().isEmpty());
      assertFalse(menuAction);

      ui.executeInput(CMD);
      assertTrue(testAppender.getLog().isEmpty());
      assertTrue(menuAction);
   }
}
