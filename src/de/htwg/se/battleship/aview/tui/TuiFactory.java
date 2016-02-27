package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.view.ConsoleInput;

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


   protected abstract TUIMain _createTUIMain();

   public static TUIMain createTUIMain() {
      return getInstance()._createTUIMain();
   }

   protected abstract ConsoleInput _createConsoleInput();

   public static ConsoleInput getConsoleInput() {
      return getInstance()._createConsoleInput();
   }


   public static class DefaultImpl extends TuiFactory {


      @Override
      protected TUIMain _createTUIMain() {
         return TUIMain.getInstance();
      }

      @Override
      protected ConsoleInput _createConsoleInput() {
         return ConsoleInput.getInstance();
      }
   }

}


