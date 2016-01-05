package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.Controller;
import de.htwg.se.battleship.util.platform.SingleUseConsumer;
import de.htwg.se.battleship.util.platform.ThreadPlatform;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Very simple Baseclass for {@link de.htwg.se.battleship.util.controller.Controller}. Implementing all necessary
 * methods.
 * Created by aullik on 27.11.2015.
 */
public abstract class ThreadSaveControllerBase<C extends Controllable> extends AbstractThreadSave implements
      Controller<C> {

   private final ControllerHelper<C> helper;

   public ThreadSaveControllerBase(final ThreadPlatform platform) {
      super(platform);
      helper = new ControllerHelper<>();
   }

   @Override
   public void unregisterControllable(final C cont) {
      helper.unregisterControllable(cont);
   }


   @Override
   public void registerControllable(final C cont) {
      helper.registerControllable(cont);
   }

   @Override
   public void executeConsumerMethod(final Consumer<C> executor) {
      helper.executeConsumerMethod(executor);
   }


   // TODO rename method and params
   public <T> void executeSingleUse(final BiConsumer<C, SingleUseConsumer<T>> executor, Consumer<T> cons) {
      //must be outside of executeConsumerMethod or multiple SingleUseConsumers will be created
      final SingleUseConsumerImpl<T> consumer = new SingleUseConsumerImpl<>(cons);
      executeConsumerMethod(ct -> executor.accept(ct, consumer));
   }

   // must be done or SingleUseConsumerImpl constructor will try to access AbstractSingleUse.platform
   ThreadPlatform gamePlatform = platform;

   class SingleUseConsumerImpl<T> extends SingleUseConsumer<T> {

      private SingleUseConsumerImpl(final Consumer<T> cons) {
         super(cons, gamePlatform);
      }
   }

}
