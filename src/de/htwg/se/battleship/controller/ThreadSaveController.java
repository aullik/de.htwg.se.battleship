package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.controller.Controllable;
import de.htwg.se.battleship.util.controller.impl.ControllerBase;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * BaseClass for Controller
 * uses a Gamethread to make sure there ain't no concurrency problems
 * Created by aullik on 29.11.2015.
 */
public abstract class ThreadSaveController<C extends Controllable> extends ControllerBase<C> {

   private final Platform platform;
   private Consumer<C> current;

   public ThreadSaveController(final Platform platform) {
      this.platform = platform;
   }

   protected static class Platform implements Runnable {

      private final Thread gameThread;
      private final BlockingQueue<Runnable> gameThreadQueue;
      private boolean running;

      protected Platform() {
         this.gameThread = Thread.currentThread();
         this.gameThreadQueue = new LinkedBlockingQueue<>();
         this.running = false;
      }

      @Override
      public void run() {
         if (running)
            throw new IllegalStateException("Platform is already running");

         running = true;
         while (running) {
            final Runnable runnable;
            try {
               runnable = gameThreadQueue.take();
            } catch (InterruptedException e) {
               return;
            }
            runnable.run();
         }
      }

      protected void close() {
         running = false;
         gameThreadQueue.offer(() -> {
         });
      }
   }


   public void runLater(Runnable r) {
      platform.gameThreadQueue.offer(r);
   }

   public void close() {
      this.platform.close();
   }

   public boolean isGameThread() {
      return Thread.currentThread().equals(platform.gameThread);
   }


   public <T extends ThreadSaveController> T createThreadSaveController(Function<Platform, T> supp) {
      return supp.apply(platform);
   }


   protected final void runPlatform() {
      if (!isGameThread())
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
   public <T> void executeSingleUse(final BiConsumer<C, Consumer<T>> executor, Consumer<T> cons) {
      executeConsumerMethod(ct -> executor.accept(ct, new SingleUseConsumer<>(cons)));
   }


   class SingleUseConsumer<T> implements Consumer<T> {

      volatile boolean executed;
      Consumer<T> cons;

      private SingleUseConsumer(final Consumer<T> cons) {
         this.cons = cons;
         this.executed = false;
      }


      @Override
      public void accept(final T controllable) {
         if (executed)
            return;

         runLater(() -> {
            if (executed)
               return;

            executed = true;
            cons.accept(controllable);
         });

      }
   }
}
