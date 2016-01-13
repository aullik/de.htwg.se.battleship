package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.util.singleton.SingletonInjector;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextUITest {

   private boolean inputGet;
   private boolean inputClose;
   private boolean showText;
   private boolean executeInput;
   private final static String text = "test";

   private class TestInput1 extends ConsoleInput {

      @Override
      public String getInputLine() throws IOException {
         inputGet = true;
         return TextUITest.text;
      }

      @Override
      public void close() {
         inputClose = true;
      }
   }

   private class TestInput2 extends ConsoleInput {

      @Override
      public String getInputLine() throws IOException {
         throw new IOException();
      }

      @Override
      public void close() {
         inputClose = true;
      }

   }

   private class TestUi extends UserInterface {

      @Override
      public void showText() {
         showText = true;
      }

      @Override
      public UserInterface executeInput(String input) {
         executeInput = true;
         return this;
      }
   }

   private class TestTUI extends TextUI {

      public TestTUI(final TestInput1 testInput1, final UserInterface ui) {
         super(testInput1, ui);
         SingletonInjector.injectSingleton(this);
      }
   }

   @Before
   public void setUp() {
      inputGet = false;
      inputClose = false;
      showText = false;
      executeInput = false;
   }


   @Test
   public void testNormalCall() {
      UserInterface ui = new TestUi();
      ui.deactivateProcess();
      new TestTUI(new TestInput1(), ui);

      assertFalse(inputGet);
      assertTrue(inputClose);
      assertFalse(showText);
      assertFalse(executeInput);
   }

   @Test
   public void testException() {
      new TextUI(new TestInput2(), new TestUi());

      assertFalse(inputGet);
      assertTrue(inputClose);
      assertTrue(showText);
      assertFalse(executeInput);
   }
}
