package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.impl.ConsoleInput;
import de.htwg.se.battleship.aview.tui.impl.MainMenuUi;
import de.htwg.se.battleship.aview.tui.impl.UserInterface;
import de.htwg.se.battleship.aview.tui.menu.MainMenu;
import de.htwg.se.battleship.aview.tui.menu.TUIMenu;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.impl.OLDInitGameController;

/**
 * Factory for TUI Elements
 *
 * @author niwehrle
 */
public abstract class TuiFactory {

   protected static TuiFactory instance;

   private static TuiFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }


   // bind(UserInterface.class).to(de.htwg.se.battleship.aview.tui.DefaultImpl.MainMenuUi.class);
   protected abstract UserInterface _createUserInterface();

   public static UserInterface createUserInterface() {
      return getInstance()._createUserInterface();
   }

   // bind(IMenu.class).to(de.htwg.se.battleship.aview.tui.menu.TUIMenu.class);
   protected abstract TUIMenu _createTUIMenu();

   public static TUIMenu createTUIMenu() {
      return getInstance()._createTUIMenu();
   }


   // bind(IInitGameController.class).to(de.htwg.se.battleship.controller.DefaultImpl.OLDInitGameController.class);
   protected abstract IInitGameController _createIInitGameController();

   public static IInitGameController createIInitGameController() {
      return getInstance()._createIInitGameController();
   }


   // bind(IInitGameUI.class).to(de.htwg.se.battleship.aview.tui.InitGameUI.class);
   protected abstract InitGameUI _createInitGameUI();

   public static InitGameUI createInitGameUI() {
      return getInstance()._createInitGameUI();
   }

   public static class DefaultImpl extends TuiFactory {

      @Override
      protected UserInterface _createUserInterface() {
         return new MainMenuUi(ConsoleInput.getInstance(), ControllerFactory.createIController(),
               createInitGameUI(), createIInitGameController());
      }

      @Override
      protected TUIMenu _createTUIMenu() {
         return MainMenu.getInstance();
      }

      @Override
      protected IInitGameController _createIInitGameController() {
         return OLDInitGameController.getInstance();
      }

      @Override
      protected InitGameUI _createInitGameUI() {
         return new InitGameUI(createIInitGameController());
      }
   }

}


