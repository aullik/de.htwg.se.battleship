package de.htwg.se.battleship.util.stringmap;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by aullik on 25.11.2015.
 */
public class StringMatchMapTest {

   private static final String TEST_STRING1 = "Test";
   private static final String TEST_STRING2 = "LongTest";
   private static final String TEST_STRING3 = "longerTest";
   private static final String TEST_STRING4 = "testing";
   private static final String TEST_STRING5 = "tester";
   private static final String TEST_STRING6 = "testers";


   private StringMatchMap<String> map;

   @Before
   public void setUp() throws Exception {
      map = new StringMatchMap<>();
      map.put(TEST_STRING1, TEST_STRING1);
      map.put(TEST_STRING2, TEST_STRING2);
      map.put(TEST_STRING3, TEST_STRING3);
      map.put(TEST_STRING4, TEST_STRING4);
      map.put(TEST_STRING5, TEST_STRING5);
      map.put(TEST_STRING6, TEST_STRING6);

   }

   @Test
   public void testGetEmpty() {
      final List<String> approx = map.getApprox("");
      assertEquals(map.size(), approx.size());
   }

   @Test
   public void testGet() throws Exception {
      String s1 = "a";
      String s2 = "bb";
      String s3 = "ccc";
      assertNull(map.put(s1, s1));
      assertNull(map.put(s2, s2));
      assertNull(map.put(s3, s3));

      assertEquals(s1, map.get(s1));
      assertEquals(s2, map.get(s2));
      assertEquals(s3, map.get(s3));

   }

   @Test
   public void testGetAprox() throws Exception {
      String s1 = "a";
      String s2 = "aa";
      String s3 = "aaa";
      int amount = 3;

      assertNull(map.put(s1, s1));
      assertNull(map.put(s2, s2));
      assertNull(map.put(s3, s3));

      final List<String> strings = map.getApprox(s1);
      assertTrue(strings.contains(s1));
      assertTrue(strings.contains(s2));
      assertTrue(strings.contains(s3));
      assertEquals(amount, strings.size());
   }

   @Test (expected = IllegalArgumentException.class)
   public void testPut_NotLatin() throws Exception {
      String s = "1!";
      map.put(s, s);
   }

   @Test
   public void testPut() throws Exception {
      String key = "key";
      String value = "value";
      map.put(key, value);
      assertEquals(value, map.get(key));
   }

   @Test
   public void testRemove() throws Exception {
      String key = "key";
      String value = "value";
      map.put(key, value);
      assertTrue(map.remove(key));
      assertFalse(map.remove("qwerty"));
      assertFalse(map.contains(key));
   }

   @Test
   public void testContains() throws Exception {
      String s1 = "a";
      map.put(s1, s1);
      assertTrue(map.contains(s1));
      assertFalse(map.contains("qwerty"));
   }

   @Test
   public void testSize() throws Exception {
      final int start = map.size();
      String s1 = "a";
      String s2 = "aa";
      String s3 = "aaa";
      int amount = 3;
      assertNull(map.put(s1, s1));
      assertNull(map.put(s3, s3));
      assertNull(map.put(s2, s2));
      assertEquals(start + amount, map.size());

      assertTrue(map.contains(s1));
      assertTrue(map.contains(s2));
      assertTrue(map.contains(s3));


      assertTrue(map.remove(s1));
      assertTrue(map.remove(s3));
      assertTrue(map.remove(s2));
      assertEquals(start, map.size());

   }

   @Test
   public void testClear() throws Exception {
      map.clear();
      assertEquals(0, map.size());
      assertFalse(map.contains(TEST_STRING1));
      assertFalse(map.contains(TEST_STRING2));
      assertFalse(map.contains(TEST_STRING3));
      assertFalse(map.contains(TEST_STRING4));
      assertFalse(map.contains(TEST_STRING5));
      assertFalse(map.contains(TEST_STRING6));
   }
}