package de.htwg.se.battleship.aview.OLDtui.menu;

import de.htwg.se.battleship.aview.OLDtui.menuentry.Close;
import de.htwg.se.battleship.aview.OLDtui.menuentry.NewGame;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;

/**
 * Created by aullik on 26.11.2015.
 */
public class MainMenu extends TUIMenu {

   private final static SingletonSupplier<MainMenu> INST_SUPP = new SingletonSupplier<>(MainMenu::new);

   public static MainMenu getInstance() {
      return INST_SUPP.get();
   }

   private MainMenu() {
      this(ControllerFactory.createOLDIController());
   }


   /**
    * Initial TUIMenu with list of MenuEnty.
    *
    * @param controller IOLDController
    */
   protected MainMenu(final IOLDController controller) {
      add(new NewGame(controller));
      add(new Close(controller));
   }
}
