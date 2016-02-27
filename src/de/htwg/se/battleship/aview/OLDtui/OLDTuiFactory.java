package de.htwg.se.battleship.aview.OLDtui;

import de.htwg.se.battleship.aview.OLDtui.impl.ConsoleInput;
import de.htwg.se.battleship.aview.OLDtui.impl.MainMenuUi;
import de.htwg.se.battleship.aview.OLDtui.impl.UserInterface;
import de.htwg.se.battleship.aview.OLDtui.menu.MainMenu;
import de.htwg.se.battleship.aview.OLDtui.menu.TUIMenu;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.old.IInitGameController;
import de.htwg.se.battleship.controller.old.impl.OLDInitGameController;

/**
 * Factory for TUI Elements
 *
 * @author niwehrle
 */
public abstract class OLDTuiFactory {

   protected static OLDTuiFactory instance;

   private static OLDTuiFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }


   // bind(UserInterface.class).to(de.htwg.se.battleship.aview.tui.DefaultImpl.MainMenuUi.class);
   protected abstract UserInterface _createUserInterface();

   public static UserInterface createUserInterface() {
      return getInstance()._createUserInterface();
   }

   // bind(IMenu.class).to(TUIMenu.class);
   protected abstract TUIMenu _createTUIMenu();

   public static TUIMenu createTUIMenu() {
      return getInstance()._createTUIMenu();
   }


   // bind(IInitGameController.class).to(de.htwg.se.battleship.controller.DefaultImpl.OLDInitGameController.class);
   protected abstract IInitGameController _createIInitGameController();

   public static IInitGameController createIInitGameController() {
      return getInstance()._createIInitGameController();
   }


   // bind(IInitGameUI.class).to(InitGameUI.class);
   protected abstract InitGameUI _createInitGameUI();

   public static InitGameUI createInitGameUI() {
      return getInstance()._createInitGameUI();
   }

   public static class DefaultImpl extends OLDTuiFactory {

      @Override
      protected UserInterface _createUserInterface() {
         return new MainMenuUi(ConsoleInput.getInstance(), ControllerFactory.createOLDIController(),
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


