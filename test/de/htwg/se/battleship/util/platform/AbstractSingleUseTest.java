package de.htwg.se.battleship.util.platform;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author aullik on 07.01.2016.
 */
public class AbstractSingleUseTest {

   ThreadPlatform platform;

   class SingleUseTest extends AbstractSingleUse {

      public SingleUseTest(final ThreadPlatform platform, final boolean checkThread) {
         super(platform, checkThread);

      }
   }

   @Before
   public void setup() {
      platform = new ThreadPlatform();
      new Thread(() -> {
         platform.run();
      }).start();
   }

   @Test
   public void test() {
      final BooleanProperty check = new SimpleBooleanProperty(false);
      final CountDownLatch latch = new CountDownLatch(1);
      final SingleUseTest test = new SingleUseTest(platform, false);
      assertFalse(test.isExecuted());
      try {
         test.runChecked(() -> {
            check.set(platform.isPlatformThread());
            latch.countDown();
         });
      } catch (NotUIThreadException | AlreadyExecutedException e) {
         fail();
      }
      try {
         latch.await(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }
      assertTrue(test.isExecuted());
   }


   @Test
   public void testNotUIException() {
      final CountDownLatch latch = new CountDownLatch(1);
      final SingleUseTest test = new SingleUseTest(platform, true);
      final BooleanProperty checkFalse = new SimpleBooleanProperty(false);
      final BooleanProperty checkTrue = new SimpleBooleanProperty(false);
      platform.runLater(() -> {
         System.out.println(platform.isPlatformThread());
         try {
            test.runChecked(() -> {
               System.out.println(platform.isPlatformThread());
               checkFalse.set(true);
               System.out.println("executed");
            });
         } catch (NotUIThreadException e) {
            checkTrue.set(true);
            System.out.println("NotUIThreadException");
         } catch (AlreadyExecutedException e) {
            checkFalse.set(true);
            System.out.println("AlreadyExecutedException");
         }
         latch.countDown();
      });
      try {
         latch.await(100, TimeUnit.MILLISECONDS);
      } catch (InterruptedException e) {
         fail();
      }
      assertFalse(test.isExecuted());
      assertFalse(checkFalse.get());
      assertTrue(checkTrue.get());
   }

}