package de.htwg.se.battleship.aview.gui;

import de.htwg.se.battleship.aview.gui.impl.MainFrameImplementation;
import de.htwg.se.battleship.controller.ControllerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Factory for GUI Elements
 *
 * @author niwehrle
 */
public class GuiFactory {

   // bind(MainFrame.class).to(de.htwg.se.battleship.aview.gui.impl.MainFrameImplementation.class);

   public static MainFrame createMainFrame() {
      try {
         return new MainFrameImplementation(ControllerFactory.createIController(), ControllerFactory
               .getInitGameController());
      } catch (URISyntaxException | IOException e) {
         //FIXME Better Excpetionhandling
         throw new RuntimeException(e);
      }
   }

}
