package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.controller.impl.Controller;

/**
 * Factory for controller elements
 *
 * @author niwehrle
 */
public class ControllerFactory {

   //         bind(IController.class).to(de.htwg.se.battleship.controller.impl.Controller.class);

   public static IController createIController() {
      return Controller.getInstance();
   }

}
