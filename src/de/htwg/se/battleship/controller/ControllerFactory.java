package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.impl.GameStateControllerImpl;
import de.htwg.se.battleship.controller.old.IOLDController;
import de.htwg.se.battleship.controller.old.impl.OLDController;

/**
 * Factory for controller elements
 *
 * @author niwehrle
 */
public abstract class ControllerFactory {

   protected static ControllerFactory instance;

   private static ControllerFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }


   protected abstract GameStateController _getController();

   public static GameStateController getController() {
      return getInstance()._getController();
   }

   // bind(IOLDController.class).to(de.htwg.se.battleship.controller.old.impl.OLDController.class);
   protected abstract IOLDController _createOLDIController();

   public static IOLDController createOLDIController() {
      return getInstance()._createOLDIController();
   }


   public static class DefaultImpl extends ControllerFactory {

      @Override
      protected GameStateController _getController() {
         return GameStateControllerImpl.getInstance();
      }

      @Override
      protected IOLDController _createOLDIController() {
         return OLDController.getInstance();
      }
   }

}
