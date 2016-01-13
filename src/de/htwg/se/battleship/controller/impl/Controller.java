/**
 *
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.util.observer.impl.ObservableImpl;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;


/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class Controller extends ObservableImpl implements IController {

   private static final SingletonSupplier<Controller> INST_SUPP = new SingletonSupplier<>(Controller::new);

   public static Controller getInstance() {
      return INST_SUPP.get();
   }

   private final InitGameController initGame;

   /**
    * Initialize InitGameController
    */
   private Controller() {
      initGame = InitGameController.getInstance();
   }


   @Override
   public void newGame() {
      notifyObservers(new InitGame(initGame));
   }

   @Override
   public void close() {
      notifyObservers(new CloseProgamm());
   }

   @Override
   public void reset() {
      notifyObservers(new Winner(null));
   }
}
