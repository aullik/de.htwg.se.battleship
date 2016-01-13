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

   private ThreadPlatform platform;

   @Before
   public void setup() {
      this.platform = new ThreadPlatform();
      platform.run();

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

   @Test (expected = IllegalStateException.class)
   public void testRunAfterClosure() throws InterruptedException {
      assertTrue(platform.isRunning());
      platform.closeImmediately();
      platform.awaitClosed(1, TimeUnit.SECONDS);
      assertTrue(platform.isClosed());

      platform.run();
   }

   @Test
   public void testCloseTwice() throws InterruptedException {
      assertTrue(platform.isRunning());
      platform.closeInputQueue();
      assertTrue(platform.inputClosed());
      platform.closeInputQueue();
      assertTrue(platform.inputClosed());
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
      platform2.closeImmediately();
   }

   @Test
   public void testRunLaterBeforeRunning() {
      ThreadPlatform platform2 = new ThreadPlatform();
      platform2.runLater(platform2::closeImmediately);
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
      platform.closeImmediately();
      platform.runLater(() -> {
      });
      platform = new ThreadPlatform();
   }

   @Test
   public void testInterrupt() {
      assertTrue(platform.isRunning());
      platform.runLater(() -> Thread.currentThread().interrupt());

      try {
         platform.awaitClosed(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }
      assertFalse(platform.isRunning());
      assertTrue(platform.isClosed());
   }

   @After
   public void tearDown() {
      if (!platform.inputClosed())
         platform.closeInputQueue();
   }

   @Test
   public void testFinalize() throws Throwable {
      //i know this is very very bad style but there aint no other way to ensure a 100% test coverage
      platform.finalize();

   }

}