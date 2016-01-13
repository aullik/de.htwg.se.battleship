package de.htwg.se.battleship.aview.gui;

import de.htwg.se.battleship.aview.gui.impl.MainFrameImplementation;
import de.htwg.se.battleship.aview.tui.TuiFactory;
import de.htwg.se.battleship.controller.ControllerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Factory for GUI Elements
 *
 * @author niwehrle
 */
public abstract class GuiFactory {

   protected static GuiFactory instance;

   private static GuiFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }

   // bind(MainFrame.class).to(de.htwg.se.battleship.aview.gui.impl.MainFrameImplementation.class);
   protected abstract MainFrame _createMainFrame();

   public static MainFrame createMainFrame() {
      return getInstance()._createMainFrame();
   }

   public static class DefaultImpl extends GuiFactory {

      @Override
      protected MainFrame _createMainFrame() {
         try {
            return new MainFrameImplementation(ControllerFactory.createIController(), TuiFactory
                  .createIInitGameController());
         } catch (URISyntaxException | IOException e) {
            //FIXME Better Excpetionhandling
            throw new RuntimeException(e);
         }
      }
   }

}
