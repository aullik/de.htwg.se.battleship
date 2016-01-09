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
            running = false;
            return;
         }
         runnable.run();
      }
   }

   protected void close() {
      if (!running)
         throw new IllegalStateException("Trying to close Platform that is not running");
      running = false;
      // wakeup if sleeping
      threadQueue.offer(() -> {
      });
   }

   public boolean isRunning() {
      return running;
   }

   /**
    * @return whether the platform has run before but is closed
    */
   public boolean isClosed() {
      return platform != null && !running;
   }

   public void runLater(Runnable r) {
      if (isClosed())
         throw new IllegalStateException("ThreadPlatform is closed");

      threadQueue.offer(r);
   }

   public boolean isPlatformThread() {
      return Thread.currentThread().equals(platform);
   }

}

