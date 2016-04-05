package de.htwg.se.battleship;

import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.model.ModelFactory;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

/**
 * @author aullik on 25.01.2016.
 */
public class BattleShip2x2GridTest {


   /**
    * Starting point for a java application.
    */
   public static void main(String[] args) throws UnsupportedEncodingException {
      new BattleShip2x2GridTest().playGame();
      // initGame();
   }

   public static void initGame() {
      new TestControllerFactory();
      new TestModelFactory();
      new Battleship();
   }

   static class TestControllerFactory extends ControllerFactory.DefaultImpl {

      private TestControllerFactory() {
         instance = this;
      }

      @Override
      protected int _getNumberOfSize2Ships() {
         return 1;
      }

      @Override
      protected int _getNumberOfSize3Ships() {
         return 0;
      }

      @Override
      protected int _getNumberOfSize4Ships() {
         return 0;
      }

      @Override
      protected int _getNumberOfSize5Ships() {
         return 0;
      }
   }

   static class TestModelFactory extends ModelFactory.DefaultImpl {

      private TestModelFactory() {
         instance = this;
      }

      @Override
      protected int _getGridSize() {
         return 2;
      }
   }


   @Test
   public void playGame() throws UnsupportedEncodingException {
      InputStream backup = System.in;
      String seperator = System.getProperty("line.separator");

      List<String> commands = new LinkedList<>();
      commands.add("-new");
      commands.add("1, TUI");
      commands.add("2, TUI");
      commands.add("-finish");

      commands.add("p1");
      commands.add("p2");

      commands.add("2");
      commands.add("2");

      //commands.add("-surrender");

      commands.add("0,0R");
      commands.add("0,1R");

      commands.add("0,0");
      commands.add("0,0");

      commands.add("1,0");
      commands.add("1,0");

      commands.add("-exit");

      final Vector<InputStream> vector = new Vector<>();
      for (String cmd : commands)
         vector.add(new ByteArrayInputStream((cmd + seperator).getBytes("UTF-8")));

      vector.add(backup);
      System.setIn(new SequenceInputStream(vector.elements()));

      initGame();

   }

}
