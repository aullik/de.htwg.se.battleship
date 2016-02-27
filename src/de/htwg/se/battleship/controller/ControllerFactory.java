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

   protected abstract int _getNumberOfSize2Ships();

   public static int getNumberOfSize2Ships() {
      return getInstance()._getNumberOfSize2Ships();
   }

   protected abstract int _getNumberOfSize3Ships();

   public static int getNumberOfSize3Ships() {
      return getInstance()._getNumberOfSize3Ships();
   }

   protected abstract int _getNumberOfSize4Ships();

   public static int getNumberOfSize4Ships() {
      return getInstance()._getNumberOfSize4Ships();
   }

   protected abstract int _getNumberOfSize5Ships();

   public static int getNumberOfSize5Ships() {
      return getInstance()._getNumberOfSize5Ships();
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

      @Override
      protected int _getNumberOfSize2Ships() {
         return 4;
      }

      @Override
      protected int _getNumberOfSize3Ships() {
         return 3;
      }

      @Override
      protected int _getNumberOfSize4Ships() {
         return 2;
      }

      @Override
      protected int _getNumberOfSize5Ships() {
         return 1;
      }

   }

}
