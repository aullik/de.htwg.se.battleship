package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Helperclass for Controllers to avoid copy paste.
 *
 * @author aullik on 05.01.2016.
 */
public class ControllerHelper<E extends Controllable> implements Controller<E> {

   private final List<E> controllables;
   private Consumer<E> current = null;

   public ControllerHelper() {
      this.controllables = new LinkedList<>();
   }

   @Override
   public void registerControllable(final E cont) {
      controllables.add(cont);
      if (current != null)
         current.accept(cont);
   }

   @Override
   public void unregisterControllable(final E cont) {
      controllables.remove(cont);
   }

   @Override
   public void executeConsumerMethod(final Consumer<E> executor) {
      this.current = executor;
      controllables.forEach(executor);
   }
}
