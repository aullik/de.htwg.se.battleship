package de.htwg.se.battleship.util.controller.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author aullik on 29.12.2015.
 */
public class GamePlatform implements Runnable {

   private final Thread gameThread;
   private final BlockingQueue<Runnable> gameThreadQueue;
   private boolean running;

   protected GamePlatform() {
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

   public void runLater(Runnable r) {
      gameThreadQueue.offer(r);
   }

   public boolean isGameThread() {
      return Thread.currentThread().equals(gameThread);
   }

}

