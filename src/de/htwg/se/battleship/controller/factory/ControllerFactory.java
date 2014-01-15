/**
 * 
 */
package de.htwg.se.battleship.controller.factory;

import de.htwg.se.battleship.controller.IControllerFactory;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.impl.InitGameController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public class ControllerFactory implements IControllerFactory {

    @Override
    public IInitGameController createIInitGameController() {
        return new InitGameController();
    }

}
