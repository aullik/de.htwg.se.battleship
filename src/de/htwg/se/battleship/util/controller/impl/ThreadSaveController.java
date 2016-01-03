package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * BaseClass for Controller
 * uses a Gamethread to make sure there ain't no concurrency problems
 * Created by aullik on 29.11.2015.
 */
public abstract class ThreadSaveController<C extends Controllable> extends ControllerBase<C> {

   protected final GamePlatform platform;
   private Consumer<C> current;

   public ThreadSaveController(final GamePlatform platform) {
      this.platform = platform;
   }


   public void close() {
      this.platform.close();
   }


   public <T extends ThreadSaveController> T createThreadSaveController(Function<GamePlatform, T> supp) {
      return supp.apply(platform);
   }


   protected final void runPlatform() {
      if (!platform.isGameThread())
         throw new IllegalStateException("run must be called from the same Thread that created this instance");
      this.platform.run();
   }

   @Override
   public void registerControllable(final C cont) {
      super.registerControllable(cont);
      if (current != null)
         current.accept(cont);
   }

   @Override
   public void executeConsumerMethod(final Consumer<C> executor) {
      current = executor;
      super.executeConsumerMethod(executor);
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
