package de.htwg.se.battleship.controller.gamemode;

import java.util.function.Consumer;

/**
 * Interface for all Gamemodes
 * <p>
 * Created by aullik on 29.11.2015.
 */
public interface GamemodeController<C extends GamemodeControllable> extends Runnable {


   /**
    * adds {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} to a list.
    * <p>
    * All GamemodeControllables that are registered for Player1 will be notified {@see
    * OLDController#executeConsumerMethod}
    *
    * @param cont {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    */
   void registerControllablePlayer1(C cont);

   /**
    * removes {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} from the Player1Controllable-list
    *
    * @param cont {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    */
   void unregisterControllablePlayer1(C cont);

   /**
    * Notifies all {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} registered for Player 1.
    * <p>
    * The executor can executeConsumerMethod all methods of
    * {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    *
    * @param executor {@link java.util.function.Consumer} for an
    *                 {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} registered for Player1
    */
   void executePlayer1ConsumerMethod(Consumer<C> executor);

   /**
    * adds {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} to a list.
    * <p>
    * All GamemodeControllables that are registered for Player1 will be notified {@see
    * OLDController#executeConsumerMethod}
    *
    * @param cont {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    */
   void registerControllablePlayer2(C cont);

   /**
    * removes {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} from the Player1Controllable-list
    *
    * @param cont {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    */
   void unregisterControllablePlayer2(C cont);

   /**
    * Notifies all {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} registered for Player 1.
    * <p>
    * The executor can executeConsumerMethod all methods of
    * {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable}
    *
    * @param executor {@link java.util.function.Consumer} for an
    *                 {@link de.htwg.se.battleship.controller.gamemode.GamemodeControllable} registered for Player1
    */
   void executePlayer2ConsumerMethod(Consumer<C> executor);

   
   // TODO move to another interface

   /**
    * abort the running Game
    */
   void abortGame();

}
