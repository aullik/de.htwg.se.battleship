/**
 *
 */
package de.htwg.se.battleship.aview.tui.menu;

import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.IMenuEntry;
import de.htwg.se.battleship.aview.tui.menuentry.Close;
import de.htwg.se.battleship.aview.tui.menuentry.NewGame;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class MainMenu implements IMenu {

   private final static SingletonSupplier<MainMenu> INST_SUPP = new SingletonSupplier<>(MainMenu::new);

   public static MainMenu getInstance() {
      return INST_SUPP.get();
   }

   private final Map<String, IMenuEntry> list;


   private MainMenu() {
      this(ControllerFactory.createIController());
   }

   /**
    * Initial MainMenu with list of MenuEnty.
    *
    * @param controller IController
    */
   protected MainMenu(IController controller) {
      list = new HashMap<>();
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