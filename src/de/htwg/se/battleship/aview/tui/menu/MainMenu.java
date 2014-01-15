/**
 * 
 */
package de.htwg.se.battleship.aview.tui.menu;

import java.util.HashMap;
import java.util.Map;

import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.aview.tui.menuentry.Close;
import de.htwg.se.battleship.aview.tui.menuentry.NewGame;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp
 *
 */
public class MainMenu implements IMenu {

    private final Map<String, IMenuEntry> list;

    public MainMenu(IController controller) {
        list = new HashMap<String, IMenuEntry>();
        add(new NewGame(controller));
        add(new Close(controller));
    }

    private void add(IMenuEntry entry) {
        list.put(entry.command(), entry);
    }

    @Override
    public Map<String, IMenuEntry> get() {
        return list;
    }

}
