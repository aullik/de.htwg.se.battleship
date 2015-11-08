package de.htwg.se.battleship.aview.tui.menu;

import de.htwg.se.battleship.aview.tui.IMenuEntry;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class MainMenuTest {

   @Test
   public void test() {
      MainMenu mainMenu = new MainMenu(null);
      Map<String, IMenuEntry> map = mainMenu.get();
      assertNotNull(map);
      assertFalse(map.isEmpty());
   }
}