package de.htwg.se.battleship.aview.tui.menu;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import de.htwg.se.battleship.aview.tui.IMenuEntry;

public class MainMenuTest {

    @Test
    public void test() {
        MainMenu mainMenu = new MainMenu(null);
        Map<String, IMenuEntry> map = mainMenu.get();
        assertNotNull(map);
        assertFalse(map.isEmpty());
    }
}