/**
 * 
 */
package de.htwg.se.battleship;

import com.google.inject.AbstractModule;

import de.htwg.se.battleship.aview.tui.IMenu;
import de.htwg.se.battleship.aview.tui.IScannerFactory;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class BattleshipModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IController.class).to(de.htwg.se.battleship.controller.impl.Controller.class);
        bind(IMenu.class).to(de.htwg.se.battleship.aview.tui.menu.MainMenu.class);
        bind(IScannerFactory.class).to(de.htwg.se.battleship.aview.tui.ScannerFactory.class);
    }

}
