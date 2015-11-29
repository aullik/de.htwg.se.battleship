package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Very simple Baseclass for {@link de.htwg.se.battleship.util.controller.Controller}. Implementing all necessary
 * methods.
 * Created by aullik on 27.11.2015.
 */
public abstract class ControllerBase<C extends Controllable> implements Controller<C> {

   private final List<C> list;

   public ControllerBase() {
      list = new LinkedList<>();
   }

   @Override
   public void registerControllable(final C cont) {
      list.add(cont);

   }

   @Override
   public void unregisterControllable(final C cont) {
      list.remove(cont);
   }

   @Override
   public void execute(final Consumer<C> executor) {
      list.forEach(executor);
   }
}
