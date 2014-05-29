/**
 * 
 */
package de.htwg.se.battleship;

import com.google.inject.AbstractModule;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.tui.IInitGameUI;
import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.Input;
import de.htwg.se.battleship.aview.tui.UserInterface;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.model.ModelFabric;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class BattleshipModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IController.class).to(de.htwg.se.battleship.controller.impl.Controller.class);
        bind(ModelFabric.class).to(de.htwg.se.battleship.model.impl.ModelFabricImpl.class);

        // TUI
        bind(Input.class).to(de.htwg.se.battleship.aview.tui.impl.ConsoleInput.class);
        bind(UserInterface.class).to(de.htwg.se.battleship.aview.tui.impl.MainMenuUi.class);
        bind(IMenu.class).to(de.htwg.se.battleship.aview.tui.menu.MainMenu.class);
        bind(IInitGameController.class).to(de.htwg.se.battleship.controller.impl.InitGameController.class);
        bind(IInitGameUI.class).to(de.htwg.se.battleship.aview.tui.InitGameUI.class);

        bind(MainFrame.class).to(de.htwg.se.battleship.aview.gui.impl.MainFrameImplementation.class);
    }
}