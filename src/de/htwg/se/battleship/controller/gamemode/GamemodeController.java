package de.htwg.se.battleship.controller.gamemode;

/**
 * Interface for all Gamemodes
 * <p>
 * Created by aullik on 29.11.2015.
 */
public interface GamemodeController<C extends GamemodeControllable> extends Runnable {


   /**
    * removes {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} from all Controllable-lists
    *
    * @param cont {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    */
   void unregisterControllablePlayer(C cont);

}
