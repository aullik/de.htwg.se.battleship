package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.impl.ConsoleInput;
import de.htwg.se.battleship.aview.tui.impl.MainMenuUi;
import de.htwg.se.battleship.aview.tui.impl.TextUI;
import de.htwg.se.battleship.aview.tui.impl.UserInterface;
import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.ModelFactory;

/**
 * Factory for TUI Elements
 *
 * @author niwehrle
 */
public class TuiFactory {

   private static TextUI instance_TextUI;
   private static ConsoleInput instance_ConsoleInput;

   /**
    * @return singleton instance of ConsoleInput
    */
   public static ConsoleInput getConsoleInput() {
      if (instance_ConsoleInput == null)
         instance_ConsoleInput = new ConsoleInput();

      return instance_ConsoleInput;
   }

   /**
    * @return singleton instance of TextUI
    */
   public static TextUI getTextUI() {
      if (instance_TextUI == null)
         instance_TextUI = new TextUI();

      return instance_TextUI;
   }


   // bind(UserInterface.class).to(de.htwg.se.battleship.aview.tui.impl.MainMenuUi.class);
   // bind(IMenu.class).to(de.htwg.se.battleship.aview.tui.menu.MainMenu.class);
   // bind(IInitGameController.class).to(de.htwg.se.battleship.controller.impl.InitGameController.class);
   // bind(IInitGameUI.class).to(de.htwg.se.battleship.aview.tui.InitGameUI.class);

   public static UserInterface createUserInterface() {
      return new MainMenuUi(getConsoleInput(), createIMenu(), ControllerFactory.createIController(),
            createInitGameUI(), createIInitGameController());
   }

   public static IMenu createIMenu() {
      return new MainMenu(ControllerFactory.createIController());
   }

   public static IInitGameController createIInitGameController() {
      return new InitGameController(ModelFactory.createModelFabric());
   }

   public static IInitGameUI createIInitGameUI() {
      return createInitGameUI();
   }

   public static InitGameUI createInitGameUI() {
      return new InitGameUI(createIInitGameController());
   }

}
