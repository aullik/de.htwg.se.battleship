package de.htwg.se.battleship.aview.tui.gridpPainter;

import de.htwg.se.battleship.model.read.REnemyGrid;

import java.util.StringJoiner;

/**
 * @author aullik on 13.01.2016.
 */
public abstract class GridPainterBase<G extends REnemyGrid> implements GridPainter {

   final private static char space12 = 0x2001;
   final private static char space6 = 0x2002;
   final private static char space4 = 0x2004;
   final private static char space3 = 0x2005;
   final private static char space2 = 0x2006;
   final private static char linebreak = '\n';


   final private static char c_w_border = 0x2500;
   final private static char c_h_border = 0x2502;
   final private static char c_corner = 0x253C;
   final private static String w_border = "" + c_w_border;
   final private static String h_border = "" + c_h_border;
   final private static String corner = "" + c_corner;

   final private static char c_ship = 0x25A1;
   final private static char c_hit = 0x2573;
   final private static char c_shot = 0x20DD;

   final private static String empty = "" + space12 + space12;
   final private static String ship = "" + space3 + space4 + c_ship + space3 + space4;
   final private static String hit = "" + space6 + c_hit + space6;
   final private static String shot = "" + space4 + space12 + space3 + c_shot + space2 + space3;

   protected final G grid;
   final int size;
   private final String border;

   protected GridPainterBase(G grid) {
      this.grid = grid;
      this.size = grid.getSize();
      this.border = createBorder();
   }


   @Override
   public String paintGrid() {
      String newline = "" + linebreak;
      String border = printBorder();
      final StringJoiner sj = new StringJoiner(newline + border + newline, border + newline, newline + border);
      for (int l = 0; l < size; l++) {
         sj.add(printCellLine(l));
      }

      return sj.toString();
   }

   protected String printBorder() {
      return border;
   }

   private String createBorder() {
      String left = corner + space6;
      String right = space6 + corner;
      final StringJoiner sj = new StringJoiner(right + space6, left, right);
      for (int i = 0; i < size; i++)
         sj.add(w_border);
      return sj.toString();
   }

   protected String printCellLine(int l) {
      final StringJoiner sj = new StringJoiner(h_border, h_border, h_border);
      for (int i = 0; i < size; i++)
         sj.add(decideCellState(i, l).paint());

      return sj.toString();
   }

   protected abstract CELL_STATE decideCellState(int line, int num);

   protected enum CELL_STATE {
      EMPTY(empty),
      SHIP(ship),
      SHOT(shot),
      HIT(hit);

      private final String paint;

      CELL_STATE(String paint) {
         this.paint = paint;
      }

      private String paint() {
         return this.paint;
      }
   }

}
