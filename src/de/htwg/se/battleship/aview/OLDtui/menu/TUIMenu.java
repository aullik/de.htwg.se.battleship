/**
 *
 */
package de.htwg.se.battleship.aview.OLDtui.menu;

import de.htwg.se.battleship.aview.OLDtui.IMenuEntry;
import de.htwg.se.battleship.util.stringmap.StringMatchMap;

import java.util.List;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public class TUIMenu {


   private final StringMatchMap<IMenuEntry> map;

   /**
    * Initial TUIMenu with list of MenuEnty.
    */
   protected TUIMenu() {
      map = new StringMatchMap<>();
   }


   protected void add(final IMenuEntry entry) {
      map.put(stripCommand(entry.command()), entry);
   }

   private String stripCommand(final String command) {
      String ret = command;
      while (ret.startsWith("-")) {
         ret = ret.substring(1);
      }
      return ret;
   }

   public IMenuEntry get(String command) {
      return map.get(stripCommand(command));
   }

   public List<IMenuEntry> getApproximateCommands(String command) {
      return map.getApprox(stripCommand(command));
   }

   public List<IMenuEntry> getAllCommands() {
      return getApproximateCommands("");
   }

}