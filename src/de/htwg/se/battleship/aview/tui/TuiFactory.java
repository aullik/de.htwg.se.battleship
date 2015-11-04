package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.impl.ConsoleInput;
import de.htwg.se.battleship.aview.tui.impl.MainMenuUi;
import de.htwg.se.battleship.aview.tui.impl.UserInterface;
import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.impl.InitGameController;

/**
 * Factory for TUI Elements
 *
 * @author niwehrle
 */
public class TuiFactory {


   // bind(UserInterface.class).to(de.htwg.se.battleship.aview.tui.impl.MainMenuUi.class);
   // bind(IMenu.class).to(de.htwg.se.battleship.aview.tui.menu.MainMenu.class);
   // bind(IInitGameController.class).to(de.htwg.se.battleship.controller.impl.InitGameController.class);
   // bind(IInitGameUI.class).to(de.htwg.se.battleship.aview.tui.InitGameUI.class);

   public static UserInterface createUserInterface() {
      return new MainMenuUi(ConsoleInput.getInstance(), createIMenu(), ControllerFactory.createIController(),
            createInitGameUI(), createIInitGameController());
   }

   public static IMenu createIMenu() {
      return MainMenu.getInstance();
   }

   public static IInitGameController createIInitGameController() {
      return InitGameController.getInstance();
   }

   public static IInitGameUI createIInitGameUI() {
      return createInitGameUI();
   }

   public static InitGameUI createInitGameUI() {
      return new InitGameUI(createIInitGameController());
   }

}
