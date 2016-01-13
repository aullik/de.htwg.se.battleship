package de.htwg.se.battleship.util.stringmap;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

/**
 * @author aullik on 12.01.2016.
 */
public class CharArrayIterTest {

   final char[] chars = {'h', 'a', 'l', 'l', 'o'};
   CharArrayIter iter;

   @Before
   public void setup() {
      iter = new CharArrayIter(chars);

   }


   @Test
   public void test() {
      for (int i = 0; i < chars.length; i++) {
         assertTrue(iter.hasNext());

         char c = iter.next();
         assertEquals(c, iter.get());
         assertEquals(chars[i], c);
         assertEquals(i, iter.getPos());
      }

      assertFalse(iter.hasNext());
   }

   @Test (expected = ArrayIndexOutOfBoundsException.class)
   public void testSetToOutOfBounds() {
      iter.setTo(5);
   }

   @Test (expected = ArrayIndexOutOfBoundsException.class)
   public void testGetOutOfBounds() {
      iter.setTo(4);
      iter.next();
   }

   @Test
   public void testSetTo() {
      iter.setTo(3);
      assertEquals('l', iter.get());
   }

   @Test (expected = ArrayIndexOutOfBoundsException.class)
   public void testGetRestOutOfBounds() {
      iter.getRest();

   }

   @Test
   public void testGetRest() {
      iter.next();
      final char[] rest = iter.getRest();
      assertTrue(Arrays.equals(chars, rest));
      assertNotSame(chars, rest);
   }

}