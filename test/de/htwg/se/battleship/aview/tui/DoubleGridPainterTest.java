package de.htwg.se.battleship.aview.tui;

import org.junit.Test;

/**
 * @author aullik on 13.01.2016.
 */
public class DoubleGridPainterTest {

   char w_border = 0x2500;
   char h_border = 0x2502;
   char corner = 0x253C;
   char space12 = 0x2001;
   char space6 = 0x2002;
   char space4 = 0x2004;
   char space3 = 0x2005;
   char space2 = 0x2006;


   String empty = "" + space12 + space12;
   char c_ship = 0x25A1;
   String ship = "" + space3 + space4 + c_ship + space3 + space4;
   char c_hit = 0x2573;
   String hit = "" + space6 + c_hit + space6;
   char c_shot = 0x20DD;
   String shot = "" + space4 + space12 + space3 + c_shot + space2 + space3;

   char shot1 = 0x25EF;
   char shot2 = 0x25CB;
   char shot3 = 0x3007;

   @Test
   public void test() {

      printCell("" + space6 + c_shot + space6);
      printCell("" + space6 + shot1 + space6);
      printCell("" + space4 + space12 + space3 + shot2 + space6);
      printCell("" + space6 + shot3 + space6);

      System.out.println("accepted:");
      printCell(empty);
      printCell(shot);
      printCell(ship);
      printCell(hit);
   }

   private void printCell(String middle) {
      printBorder();
      System.out.println(h_border + middle + h_border);
      printBorder();
   }

   private void printBorder() {
      System.out.println("" + corner + space6 + w_border + space6 + corner);
   }

}