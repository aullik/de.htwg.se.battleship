package de.htwg.se.battleship.util.platform;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * @author aullik on 28.12.2015.
 */
public class SingleUseConsumerTest {

   @Test
   public void test() {
      final ThreadPlatform platform = new ThreadPlatform();
      new Thread(platform::run).start();
      final SingleUseConsumer<CountDownLatch> cons = new SingleUseConsumer<>
            (CountDownLatch::countDown, platform);

      final CountDownLatch finished = new CountDownLatch(1);

      try {
         cons.accept(finished);
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         fail();
      }

      try {
         finished.await(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }

      platform.closeImmediately();
   }

}