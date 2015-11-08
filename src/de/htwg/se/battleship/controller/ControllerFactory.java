package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.impl.Controller;

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


   // bind(IController.class).to(de.htwg.se.battleship.controller.impl.Controller.class);
   protected abstract IController _createIController();

   public static IController createIController() {
      return getInstance()._createIController();
   }


   public static class DefaultImpl extends ControllerFactory {

      @Override
      protected IController _createIController() {
         return Controller.getInstance();
      }
   }

}
