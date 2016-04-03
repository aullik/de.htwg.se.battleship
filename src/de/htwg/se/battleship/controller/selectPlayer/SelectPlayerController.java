package de.htwg.se.battleship.controller.selectPlayer;

import de.htwg.se.battleship.util.controller.Controller;

/**
 */
public interface SelectPlayerController extends Controller<SelectPlayerControllable>{

   enum PlayerNumber {
      One, Two;
   }

   interface UI {

      String getName();
   }

}
