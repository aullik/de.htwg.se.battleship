package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.controller.Controllable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author aullik on 06.01.2016.
 */
public class ControllerHelperTest {

   class TestControllable implements Controllable {

      boolean called = false;

      boolean isCalled() {
         return called;
      }

      void setCalled() {
         called = true;
      }

   }

   ControllerHelper<TestControllable> helper;
   TestControllable tc1;
   TestControllable tc2;

   @Before
   public void setup() {
      helper = new ControllerHelper<>();

      tc1 = new TestControllable();
      tc2 = new TestControllable();

      helper.registerControllable(tc1);
      helper.registerControllable(tc2);
   }


   @Test
   public void testRegister() {

      helper.executeConsumerMethod(TestControllable::setCalled);

      Assert.assertTrue(tc1.isCalled());
      Assert.assertTrue(tc2.isCalled());
   }

   @Test
   public void testUnregister() {
      final ControllerHelper<TestControllable> helper = new ControllerHelper<>();


      final TestControllable tc1 = new TestControllable();
      final TestControllable tc2 = new TestControllable();

      helper.registerControllable(tc1);
      helper.registerControllable(tc2);

      helper.unregisterControllable(tc2);

      helper.executeConsumerMethod(TestControllable::setCalled);

      Assert.assertTrue(tc1.isCalled());
      Assert.assertFalse(tc2.isCalled());
   }

}