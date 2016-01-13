package de.htwg.se.battleship.util.platform;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author aullik on 29.12.2015.
 */
public class ThreadPlatform implements Runnable {

   private final Thread platform;
   private final BlockingQueue<Runnable> threadQueue;
   private final AtomicBoolean running;
   private volatile boolean acceptJobs;

   public ThreadPlatform() {
      this.threadQueue = new LinkedBlockingQueue<>();
      this.running = new AtomicBoolean(false);
      this.acceptJobs = true;
      this.platform = new Thread(() -> _run(threadQueue, running));
   }

   @Override
   public void run() {
      if (isRunning())
         throw new IllegalStateException("Platform is already running");

      if (isClosed())
         throw new IllegalStateException("Platform is terminated");

      running.set(true);
      platform.start();
   }

   // must be a static method or ThreadPlatform will never be garbage collected
   private static void _run(final BlockingQueue<Runnable> threadQueue, final AtomicBoolean running) {
      while (running.get()) {
         final Runnable runnable;
         try {
            runnable = threadQueue.take();
         } catch (InterruptedException e) {
            running.set(false);
            return;
         }
         runnable.run();
      }
   }

   /**
    * will close the Platform. Currently executed Job will be finished.
    */
   protected synchronized void closeImmediately() {
      if (!running.get())
         throw new IllegalStateException("Trying to close Platform that is not running");
      acceptJobs = false;
      running.set(false);
      // wakeup if sleeping
      threadQueue.offer(() -> {
      });
   }

   /**
    * will close the Platform. All jobs in queue will be executed.
    */
   protected synchronized void closeInputQueue() {
      if (!acceptJobs)
         return;

      this.acceptJobs = false;
      //this will be the last executed job
      threadQueue.offer(() -> running.set(false));
   }

   public boolean isRunning() {
      return running.get();
   }

   /**
    * @return whether the platform has run before but is closed
    */
   public boolean isClosed() {
      return Thread.State.TERMINATED.equals(platform.getState());
   }

   public boolean inputClosed() {
      return !acceptJobs;
   }

   public synchronized void runLater(Runnable r) {
      if (!acceptJobs)
         throw new IllegalStateException("ThreadPlatform is closed or closing");

      threadQueue.offer(r);
   }

   /**
    * @return whether or not currentThread is platformThread
    */
   public boolean isPlatformThread() {
      return Thread.currentThread().equals(platform);
   }


   /**
    * @param timeout the timeout
    * @param unit    the timeunit of the timeout
    * @return true if the thread has finished, false if the timeout occured
    * @throws InterruptedException if the thread was interrupted during waiting;
    */
   public boolean awaitClosed(long timeout, TimeUnit unit) throws InterruptedException {
      platform.join(unit.toMillis(timeout));
      return !platform.isAlive();
   }

   @Override
   protected final void finalize() throws Throwable {
      synchronized (this) {
         if (acceptJobs)
            closeInputQueue();
      }
      super.finalize();
   }
}

