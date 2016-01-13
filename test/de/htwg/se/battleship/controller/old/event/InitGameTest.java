package de.htwg.se.battleship.controller.old.event;

import de.htwg.se.battleship.controller.old.impl.OLDInitGameController;
import de.htwg.se.battleship.model.old.ModelFabric;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InitGameTest {

   private class TestController extends OLDInitGameController {

      public TestController(ModelFabric fabric) {
         super(fabric);
      }

   }

   @Test
   public void test() {
      TestController controller = new TestController(null);
      InitGame event = new InitGame(controller);
      assertEquals(controller, event.getController());
   }

}
