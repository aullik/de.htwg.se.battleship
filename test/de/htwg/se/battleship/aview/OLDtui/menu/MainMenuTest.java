package de.htwg.se.battleship.aview.OLDtui.menu;

import de.htwg.se.battleship.aview.OLDtui.IMenuEntry;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MainMenuTest {

   @Test
   public void test() {
      TUIMenu mainMenu = new MainMenu(null);
      List<IMenuEntry> list = mainMenu.getAllCommands();
      assertNotNull(list);
      assertFalse(list.isEmpty());
   }
}