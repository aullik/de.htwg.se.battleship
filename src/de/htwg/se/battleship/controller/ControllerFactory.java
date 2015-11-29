package de.htwg.se.battleship.controller;

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


   // bind(IOLDController.class).to(de.htwg.se.battleship.controller.old.impl.OLDController.class);
   protected abstract IOLDController _createIController();

   public static IOLDController createIController() {
      return getInstance()._createIController();
   }


   public static class DefaultImpl extends ControllerFactory {

      @Override
      protected IOLDController _createIController() {
         return OLDController.getInstance();
      }
   }

}
