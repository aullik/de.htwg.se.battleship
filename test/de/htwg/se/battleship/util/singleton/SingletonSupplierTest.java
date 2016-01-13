package de.htwg.se.battleship.util.singleton;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by aullik on 08.11.2015.
 */
public class SingletonSupplierTest extends TestCase {

   @Test
   public void testGetNew() {
      final SingletonSupplier<Object> supp = new SingletonSupplier<>(() -> new Object() {});

      Object o = supp.get();
      assertSame(o, supp.get());
   }

   @Test
   public void testGetExisting() {

      Object o = new Object() {};
      final SingletonSupplier<Object> supp = new SingletonSupplier<>(() -> o);

      assertSame(o, supp.get());
   }


   interface Timed {

      long getTime();
   }

   @Test
   public void testCreationTime() {

      final SingletonSupplier<Timed> supp = new SingletonSupplier<>(() -> new Timed() {
         long time = System.currentTimeMillis();

         @Override
         public long getTime() {
            return time;
         }
      });

      long time = System.currentTimeMillis();
      assertTrue(supp.get().getTime() > time);

   }
}