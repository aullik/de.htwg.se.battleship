package de.htwg.se.battleship.controller;

import de.htwg.se.battleship.util.controller.Controllable;
import javafx.beans.property.SimpleObjectProperty;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test for {@link de.htwg.se.battleship.controller.ThreadSaveController}
 * Created by aullik on 03.12.2015.
 */
public class ThreadSaveControllerTest {

   interface TestControllable extends Controllable {

      void test();

   }

   static class TestController extends ThreadSaveController<TestControllable> {

      static class TestPlatform extends Platform {}

      public TestController() {
         super(new TestPlatform());
      }
   }

   class TestGivePlatform extends ThreadSaveController<TestControllable> {

      public TestGivePlatform(final Platform platform) {
         super(platform);
      }
   }

   static class TestControllerWrapper {

      volatile TestController cont;
      final CountDownLatch latch;

      private TestControllerWrapper() {
         this.latch = new CountDownLatch(1);
      }

      void set(TestController cont) {
         this.cont = cont;
         this.latch.countDown();
      }

      TestController get() {
         try {
            this.latch.await();
         } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.fail();
         }
         return cont;
      }
   }

   private Thread gameThread;
   private TestController cont;
   private CountDownLatch endOfThread;

   @Before
   public void setup() {
      this.endOfThread = new CountDownLatch(1);
      TestControllerWrapper wrapper = new TestControllerWrapper();
      this.gameThread = new Thread(() -> {
         final TestController controller = new TestController();
         wrapper.set(controller);
         controller.runPlatform(); //wont return until controller is closed
         endOfThread.countDown();
      });
      this.gameThread.start();
      this.cont = wrapper.get();
   }

   @Test
   public void testPlatform() throws InterruptedException {
      assertFalse(cont.isGameThread());
      CountDownLatch finished = new CountDownLatch(1);
      cont.runLater(() -> {
         assertTrue(cont.isGameThread());
         finished.countDown();
      });
      finished.await();
   }

   @Test
   public void testInterrupt() {
      gameThread.interrupt();
      try {
         assertTrue(endOfThread.await(1, TimeUnit.SECONDS));
      } catch (InterruptedException e) {
         fail();
      }
   }

   @Test
   public void testClose() {
      cont.close();
      try {
         assertTrue(endOfThread.await(1, TimeUnit.SECONDS));
      } catch (InterruptedException e) {
         fail();
      }
   }

   @Test
   public void testRunTwice() {
      cont.runLater(() -> {
         try {
            cont.runPlatform();
         } catch (IllegalStateException exp) {
            return; //expected
         }
         fail();
      });
   }

   @Test (expected = IllegalStateException.class)
   public void testRunWrongThread() {
      cont.runPlatform();
   }

   @Test
   public void testExecute() {
      CountDownLatch latch = new CountDownLatch(1);
      TestControllable c = latch::countDown;
      cont.registerControllable(c);
      cont.executeConsumerMethod(TestControllable::test);
      try {
         latch.await(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
         e.printStackTrace();
         fail();
      }
   }


   @Test
   public void testRegisterLate() {
      CountDownLatch latch = new CountDownLatch(1);
      TestControllable c = latch::countDown;
      cont.executeConsumerMethod(TestControllable::test);

      //register after execute is called
      cont.registerControllable(c);

      try {
         latch.await(1, TimeUnit.SECONDS);
      } catch (InterruptedException e) {
         e.printStackTrace();
         fail();
      }
   }


   private <T> Consumer<T> getSingletonConsumer(Consumer<T> cons) throws InterruptedException {
      SimpleObjectProperty<Consumer<T>> prop = new SimpleObjectProperty<>();
      CountDownLatch set = new CountDownLatch(1);
      TestControllable test = () -> {
      };
      cont.registerControllable(test);
      cont.executeSingleUse((t, c) -> {
         prop.set(c);
         set.countDown();
      }, cons);
      set.await();
      return prop.get();
   }

   private void awaitPlatform() throws InterruptedException {
      CountDownLatch latch = new CountDownLatch(1);
      cont.runLater(latch::countDown);
      latch.await();
   }

   @Test
   public void testSingleUseExecute() throws InterruptedException {
      AtomicInteger ai = new AtomicInteger(0);
      final Consumer<AtomicInteger> singletonConsumer = getSingletonConsumer(AtomicInteger::incrementAndGet);

      CountDownLatch start = new CountDownLatch(1);
      cont.runLater(() -> {
         try {
            start.await();
         } catch (InterruptedException e) {
            fail();
         }
      });

      singletonConsumer.accept(ai); // ai increased to 1
      singletonConsumer.accept(ai); // nothing happens
      start.countDown();

      awaitPlatform();
      assertEquals(1, ai.get());
   }

   @Test
   public void testSingleUseExecute2() throws InterruptedException {
      AtomicInteger ai = new AtomicInteger(0);
      final Consumer<AtomicInteger> singletonConsumer = getSingletonConsumer(AtomicInteger::incrementAndGet);


      singletonConsumer.accept(ai); // ai increased to 1

      //first one already in q
      cont.runLater(() -> {
         singletonConsumer.accept(ai); // nothing happens
      });

      awaitPlatform();
      assertEquals(1, ai.get());
   }

   @Test
   public void extendPlatform() throws InterruptedException {
      AtomicInteger ai = new AtomicInteger(0);
      final TestGivePlatform testGivePlatform = cont.createThreadSaveController(TestGivePlatform::new);

      CountDownLatch start = new CountDownLatch(1);
      cont.runLater(() -> {
         try {
            start.await();
         } catch (InterruptedException e) {
            fail();
         }
      });

      cont.runLater(() -> assertEquals(1, ai.incrementAndGet()));
      testGivePlatform.runLater(() -> assertEquals(2, ai.incrementAndGet()));
      cont.runLater(() -> assertEquals(3, ai.incrementAndGet()));

      start.countDown();
      awaitPlatform();
      assertEquals(4, ai.incrementAndGet());

   }

}
