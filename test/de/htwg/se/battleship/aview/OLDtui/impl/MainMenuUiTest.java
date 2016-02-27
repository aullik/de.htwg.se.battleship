package de.htwg.se.battleship.aview.OLDtui.impl;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.aview.OLDtui.IMenuEntry;
import de.htwg.se.battleship.aview.OLDtui.InitGameUI;
import de.htwg.se.battleship.aview.OLDtui.OLDTuiFactory;
import de.htwg.se.battleship.aview.OLDtui.menu.TUIMenu;
import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.controller.old.event.CloseProgamm;
import de.htwg.se.battleship.controller.old.event.InitGame;
import de.htwg.se.battleship.controller.old.event.SetPlayer;
import de.htwg.se.battleship.controller.old.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.old.event.SetShip;
import de.htwg.se.battleship.controller.old.event.SetShipSuccess;
import de.htwg.se.battleship.util._observer.impl.ObservableImpl;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MainMenuUiTest {

   private MainMenuUi ui;
   private static TUIMenu menu;
   private TestAppender testAppender;
   static TestOLDTuiFactory tuiFactory;


   private boolean inputClose;
   private boolean init;
   private boolean menuAction;

   private static final String CMD = "EntryCmd";
   private static final String DESC = "EntryDescription";

   private class TestController extends ObservableImpl implements IOLDController {

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
      public String getInputLine() throws IOException {
         return null;
      }

      @Override
      public String getInputWord() throws IOException {
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

   private static class TestOLDTuiFactory extends OLDTuiFactory.DefaultImpl {

      public TestOLDTuiFactory() {
         instance = this;
      }

      public void clear() {
         instance = null;
      }

      @Override
      protected TUIMenu _createTUIMenu() {
         return menu;
      }
   }


   private class TestMenu extends TUIMenu {

      private TestMenu() {
         add(new TestMenuEntry());
      }

   }

   @BeforeClass
   public static void setUpClass() {
      tuiFactory = new TestOLDTuiFactory();
   }

   @Before
   public void setUp() {
      testAppender = new TestAppender();
      Logger.getRootLogger().removeAllAppenders();
      Logger.getRootLogger().addAppender(testAppender);

      ConsoleInput input = new TestInput();
      this.menu = new TestMenu();
      IOLDController controller = new TestController();
      IInitGameController igc = new TestInitGameController();
      InitGameUI igui = new TestUi(igc);
      ui = new MainMenuUi(input, controller, igui, igc);
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

   @AfterClass
   public static void tearDown() {
      tuiFactory.clear();
   }
}
