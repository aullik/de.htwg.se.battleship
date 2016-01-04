package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.Controller;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Very simple Baseclass for {@link de.htwg.se.battleship.util.controller.Controller}. Implementing all necessary
 * methods.
 * Created by aullik on 27.11.2015.
 */
public abstract class ThreadSaveControllerBase<C extends Controllable> extends AbstractThreadSave implements
      Controller<C> {

   private final List<C> list;
   private Consumer<C> current;

   public ThreadSaveControllerBase(final GamePlatform platform) {
      super(platform);
      list = new LinkedList<>();
   }

   @Override
   public void unregisterControllable(final C cont) {
      list.remove(cont);
   }


   @Override
   public void registerControllable(final C cont) {
      list.add(cont);
      if (current != null)
         current.accept(cont);
   }

   @Override
   public void executeConsumerMethod(final Consumer<C> executor) {
      current = executor;
      list.forEach(executor);
   }


   // TODO rename method and params
   public <T> void executeSingleUse(final BiConsumer<C, SingleUseConsumer<T>> executor, Consumer<T> cons) {
      executeConsumerMethod(ct -> executor.accept(ct, new SingleUseConsumerImpl<>(cons)));
   }


   class SingleUseConsumerImpl<T> extends SingleUseConsumer<T> {

      private SingleUseConsumerImpl(final Consumer<T> cons) {
         super(cons, platform);
      }

   }

}
