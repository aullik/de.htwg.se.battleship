package de.htwg.se.battleship.util.platform;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author aullik on 29.12.2015.
 */
public class ThreadPlatform implements Runnable {

   private Thread platform;
   private final BlockingQueue<Runnable> threadQueue;
   private boolean running;

   public ThreadPlatform() {
      this.threadQueue = new LinkedBlockingQueue<>();
      this.running = false;
   }

   @Override
   public void run() {
      if (running)
         throw new IllegalStateException("Platform is already running");

      this.platform = Thread.currentThread();
      running = true;

      while (running) {
         final Runnable runnable;
         try {
            runnable = threadQueue.take();
         } catch (InterruptedException e) {
            return;
         }
         runnable.run();
      }
   }

   protected void close() {
      running = false;
      // wakeup if sleeping
      threadQueue.offer(() -> {
      });
   }

   public void runLater(Runnable r) {
      threadQueue.offer(r);
   }

   public boolean isPlatformThread() {
      return Thread.currentThread().equals(platform);
   }

}

