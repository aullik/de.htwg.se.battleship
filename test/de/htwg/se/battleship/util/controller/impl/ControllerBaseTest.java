package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import org.junit.Assert;
import org.junit.Test;

/**
 * Testclass for {@link de.htwg.se.battleship.util.controller.impl.ControllerBase}
 * Created by aullik on 27.11.2015.
 */
public class ControllerBaseTest {

   interface TestControllableInterface extends Controllable {

      void trigger();
   }

   class TestControllable implements TestControllableInterface {

      private final BooleanProperty triggered;

      public TestControllable(BooleanProperty triggered) {
         this.triggered = triggered;
      }

      @Override
      public void trigger() {
         this.triggered.set(true);

      }
   }

   class TestController extends ControllerBase<TestControllableInterface> {

      void triggerAll() {
         executeConsumerMethod(TestControllableInterface::trigger);
      }
   }


   @Test
   public void testExecute() throws Exception {
      final SimpleBooleanProperty triggered = new SimpleBooleanProperty(false);

      final TestController controller = new TestController();
      controller.registerControllable(new TestControllable(triggered));
      controller.triggerAll();

      Assert.assertTrue(triggered.get());
   }

   @Test
   public void testRemoved() throws Exception {
      final SimpleBooleanProperty triggered = new SimpleBooleanProperty(false);

      final TestController controller = new TestController();
      final TestControllable controllable = new TestControllable(triggered);
      controller.registerControllable(controllable);
      controller.unregisterControllable(controllable);
      controller.triggerAll();

      Assert.assertFalse(triggered.get());

   }

}