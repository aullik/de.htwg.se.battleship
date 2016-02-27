package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.command.Command;
import de.htwg.se.battleship.util.stringmap.StringMatchMap;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author aullik on 13.01.2016.
 */
public class BufferedCommandMatchMap extends StringMatchMap<Command> {

   private final LinkedList<Command> buffer;
   private final int buffSize;


   public BufferedCommandMatchMap(int buffSize) {
      this.buffSize = buffSize;
      this.buffer = new LinkedList<>();
   }

   private void clearBuff() {
      // super will call clear before buffer is initialized
      if (buffer == null)
         return;

      buffer.clear();
   }

   private boolean bufferContains(String key) {
      for (final Command command : buffer) {
         if (command.getCommandStripped().equals(key))
            return true;
      }
      return false;
   }

   private boolean bufferRemove(Command cmd) {
      return buffer.remove(cmd);
   }

   private Command bufferPut(Command cmd) {
      buffer.push(cmd);
      if (buffer.size() > buffSize)
         return buffer.removeLast();
      else
         return null;
   }


   @Override
   public void clear() {
      super.clear();
      clearBuff();
   }


   @Override
   public boolean contains(final String key) {
      if (bufferContains(key))
         return false;

      return super.contains(key);
   }

   @Override
   public Command put(final String key, final Command value) {
      if (!key.equals(value.getCommandStripped()))
         throw new IllegalArgumentException("key must be Stripped Command");
      return put(value);
   }

   public Command put(final Command value) {
      if (!bufferRemove(value))
         return super.put(value.getCommandStripped(), value);
      else
         return null;
   }


   @Override
   public Command get(final String key) {
      if (bufferContains(key))
         return null;

      return super.get(key);
   }

   @Override
   public List<Command> getApprox(final String key) {
      final List<Command> approx = super.getApprox(key);
      final Iterator<Command> iter = approx.iterator();
      while (iter.hasNext()) {
         if (buffer.contains(iter.next()))
            iter.remove();
      }
      return approx;
   }

   @Override
   public int size() {
      return super.size() - buffer.size();
   }

   public boolean remove(Command command) {
      return remove(command.getCommandStripped());
   }

   @Override
   public boolean remove(final String key) {
      final Command cmd = super.get(key);
      if (cmd == null)
         return false;

      final Command rem = bufferPut(cmd);
      if (rem != null)
         super.remove(key);
      return true;
   }

}
