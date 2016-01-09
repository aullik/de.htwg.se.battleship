package de.htwg.se.battleship.util.platform;

import javafx.beans.property.SimpleBooleanProperty;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author aullik on 29.12.2015.
 */
public class ThreadPlatformTest {

   private Thread thread;
   private ThreadPlatform platform;

   @Before
   public void setup() {
      this.platform = new ThreadPlatform();
      this.thread = new Thread(() -> platform.run());
      thread.start();

      final CountDownLatch started = new CountDownLatch(1);
      platform.runLater(started::countDown);
      try {
         assertTrue(started.await(100, TimeUnit.MILLISECONDS));
      } catch (InterruptedException e) {
         fail();
      }

   }

   @Test (expected = IllegalStateException.class)
   public void testDoubleRun() {
      assertTrue(platform.isRunning());
      platform.run();
   }


   @Test
   public void testBeforeRunning() {
      ThreadPlatform platform2 = new ThreadPlatform();
      assertFalse(platform2.isClosed());
      assertFalse(platform2.isRunning());
      assertFalse(platform2.isPlatformThread());
   }

   @Test (expected = IllegalStateException.class)
   public void testCloseNonRunning() {
      ThreadPlatform platform2 = new ThreadPlatform();
      platform2.close();
   }

   @Test
   public void testRunLaterBeforeRunning() {
      ThreadPlatform platform2 = new ThreadPlatform();
      platform2.runLater(platform2::close);
      Thread t = new Thread(platform2::run);
      t.run();
      try {
         t.join(100);
      } catch (InterruptedException e) {
         fail();
      }
   }

   @Test
   public void testRunLater() {
      final CountDownLatch latch = new CountDownLatch(1);
      final SimpleBooleanProperty failed = new SimpleBooleanProperty(false);

      platform.runLater(() -> {
         if (!platform.isPlatformThread())
            failed.set(true);

         if (!platform.isRunning())
            failed.set(true);

         if (platform.isClosed())
            failed.set(true);

         latch.countDown();
      });

      try {
         assertTrue(latch.await(100, TimeUnit.MILLISECONDS));
      } catch (InterruptedException e) {
         fail();
      }

      assertFalse(failed.get());
   }

   @Test (expected = IllegalStateException.class)
   public void testRunLaterAfterClose() {
      platform.close();
      platform.runLater(() -> {
      });
   }

   @Test
   public void testInterrupt() {
      assertTrue(platform.isRunning());
      thread.interrupt();
      try {
         thread.join(100);
      } catch (InterruptedException e) {
         fail();
      }
      assertFalse(platform.isRunning());
      assertTrue(platform.isClosed());
   }

   @After
   public void tearDown() {
      if (!platform.isClosed())
         platform.close();
   }

}