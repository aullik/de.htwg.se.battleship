package de.htwg.se.battleship.util.stringmap;

import java.util.Arrays;

/**
 * @author aullik on 12.01.2016.
 */
public class CharArrayIter {

   private final char[] array;

   private final int max;
   private int pos;

   public CharArrayIter(final char[] array) {
      this.array = array;
      this.pos = -1;
      this.max = array.length - 1;
   }

   public boolean hasNext() {
      return pos < max;
   }

   public char next() throws ArrayIndexOutOfBoundsException {
      pos++;
      if (pos > max)
         throw new ArrayIndexOutOfBoundsException(pos);
      return get();
   }

   public char get() {
      return array[pos];
   }

   public void setTo(final int i) {
      if (i > max)
         throw new ArrayIndexOutOfBoundsException(i);
      pos = i;
   }

   public int getPos() {
      return pos;
   }

   /**
    * @return return rest array starting with current Pos
    */
   public char[] getRest() {
      if (pos < 0 || pos >= array.length)
         throw new ArrayIndexOutOfBoundsException(pos);

      return Arrays.copyOfRange(array, pos, array.length);
   }
}

