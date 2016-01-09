package de.htwg.se.battleship.util.platform;

import javafx.beans.property.SimpleBooleanProperty;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * @author aullik on 09.01.2016.
 */
public class SingleUseRunnableTest {


   @Test
   public void test() {
      final ThreadPlatform platform = new ThreadPlatform();
      new Thread(platform::run).start();
      final CountDownLatch finished = new CountDownLatch(1);
      final SingleUseRunnable runnable = new SingleUseRunnable(finished::countDown, platform);

      try {
         runnable.run();
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         fail();
      }

      try {
         finished.await(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }
      platform.close();
   }

   @Test
   public void testConstructor2() {
      final ThreadPlatform platform = new ThreadPlatform();
      new Thread(platform::run).start();
      final CountDownLatch finished = new CountDownLatch(1);
      final SingleUseRunnable runnable = new SingleUseRunnable(finished::countDown, platform, false);

      final SimpleBooleanProperty failed = new SimpleBooleanProperty(false);

      platform.runLater(() -> {
         try {
            runnable.run();
         } catch (AlreadyExecutedException | NotUIThreadException e) {
            failed.set(true);
         }
      });

      try {
         finished.await(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }

      assertFalse(failed.get());
      platform.close();
   }

}