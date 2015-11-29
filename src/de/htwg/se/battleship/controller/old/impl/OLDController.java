/**
 *
 */
package de.htwg.se.battleship.controller.old.impl;

import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.controller.old.event.CloseProgamm;
import de.htwg.se.battleship.controller.old.event.InitGame;
import de.htwg.se.battleship.controller.old.event.Winner;
import de.htwg.se.battleship.util._observer.impl.ObservableImpl;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;


/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class OLDController extends ObservableImpl implements IOLDController {

   private static final SingletonSupplier<OLDController> INST_SUPP = new SingletonSupplier<>
         (OLDController::new);

   public static OLDController getInstance() {
      return INST_SUPP.get();
   }

   private final OLDInitGameController initGame;

   /**
    * Initialize OLDInitGameController
    */
   private OLDController() {
      initGame = OLDInitGameController.getInstance();
   }


   @Override
   public void newGame() {
      new InitGame(initGame);
   }

   @Override
   public void close() {
      new CloseProgamm();
   }

   @Override
   public void reset() {
      new Winner(null);
   }

}
