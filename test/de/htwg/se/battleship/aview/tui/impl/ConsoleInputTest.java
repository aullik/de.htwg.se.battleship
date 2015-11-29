package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.util.singleton.SingletonInjector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ConsoleInputTest {

   private final static String text = "test";
   private ConsoleInput input;
   private InputStream backup;

   @Before
   public void setUp() {
      SingletonInjector.resetValue(ConsoleInput.class);
      backup = System.in;
   }

   @After
   public void tearDown() {
      System.setIn(backup);
   }


   @Test
   public void testWithOneLine() throws Exception {

      String lineSeperator = System.getProperty("line.separator");
      String string = ConsoleInputTest.text + lineSeperator;
      byte[] bytes = string.getBytes("UTF-8");

      final InputStream old = System.in;
      try {
         System.setIn(new ByteArrayInputStream(bytes));
         input = ConsoleInput.getInstance();
         assertEquals(ConsoleInputTest.text, input.getInputLine());
      } finally {
         System.setIn(old);
      }
   }

   @Test
   public void testWithTwoLines() throws Exception {
      String lineSeparator = System.getProperty("line.separator");
      String string = ConsoleInputTest.text + lineSeparator;

      byte[] bytes = (string + string).getBytes("UTF-8");
      System.setIn(new ByteArrayInputStream(bytes));

      input = ConsoleInput.getInstance();
      assertEquals(ConsoleInputTest.text, input.getInputLine());
   }

   private void testRun(CountDownLatch finnished) {
      try {
         byte[] bytes = "".getBytes("UTF-8");
         System.setIn(new ByteArrayInputStream(bytes));
         input = ConsoleInput.getInstance();
         finnished.countDown();
         input.getInputLine();
      } catch (Exception e) {
         fail(e.getMessage());
      }
   }

   @Test
   public void testWithEmptyInput() throws InterruptedException {
      final CountDownLatch finnished = new CountDownLatch(1);
      Thread t = new Thread(() -> testRun(finnished));
      t.start();

      final boolean timeout = !finnished.await(1, TimeUnit.SECONDS);

      if (timeout)
         Assert.fail("timeout");

      input.close();
   }

   @Test
   public void testInputWord() throws Exception {
      String w1 = "word1";
      String w2 = "-word2";
      String string = w1 + " " + w2;

      byte[] bytes = (string).getBytes("UTF-8");
      System.setIn(new ByteArrayInputStream(bytes));

      input = ConsoleInput.getInstance();
      assertEquals(w1, input.getInputWord());
      assertEquals(w2, input.getInputWord());

   }

}
