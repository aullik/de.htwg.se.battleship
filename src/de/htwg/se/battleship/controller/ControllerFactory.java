package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.impl.Controller;
import de.htwg.se.battleship.controller.impl.InitGameController;
import de.htwg.se.battleship.model.ModelFactory;

/**
 * Factory for controller elements
 *
 * @author niwehrle
 */
public class ControllerFactory {

   private static Controller instance_Controller;
   private static InitGameController instace_InitGameController;

   /**
    * @return singleton instance of Controller
    */
   public static Controller getController() {
      if (instance_Controller == null)
         instance_Controller = new Controller();
      return instance_Controller;
   }

   /**
    * @return singleton instance of getInitGameController
    */
   public static InitGameController getInitGameController() {
      if (instace_InitGameController == null)
         instace_InitGameController = new InitGameController(ModelFactory.createModelFabric());
      return instace_InitGameController;
   }

   //         bind(IController.class).to(de.htwg.se.battleship.controller.impl.Controller.class);

   public static IController createIController() {
      return getController();
   }

}
