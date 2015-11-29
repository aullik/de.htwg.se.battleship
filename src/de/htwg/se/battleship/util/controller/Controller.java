package de.htwg.se.battleship.util.controller;

import java.util.function.Consumer;

/**
 * Interface for all Controllers.
 * <p>
 * Created by aullik on 27.11.2015.
 */
public interface Controller<C extends Controllable> {


   /**
    * adds {@link de.htwg.se.battleship.util.controller.Controllable} to a list.
    * <p>
    * All registered Controllables will be notified {@see OLDController#execute}
    *
    * @param cont {@link de.htwg.se.battleship.util.controller.Controllable}
    */
   void registerControllable(C cont);

   /**
    * removes {@link de.htwg.se.battleship.util.controller.Controllable} from this class
    *
    * @param cont {@link de.htwg.se.battleship.util.controller.Controllable}
    */
   void unregisterControllable(C cont);

   /**
    * Notifies all {@link de.htwg.se.battleship.util.controller.Controllable} registerd in this class.
    * <p>
    * The executor can execute all methods of {@link de.htwg.se.battleship.util.controller.Controllable}
    *
    * @param executor {@link java.util.function.Consumer} for an
    *                 {@link de.htwg.se.battleship.util.controller.Controllable}
    */
   void execute(Consumer<C> executor);

}
