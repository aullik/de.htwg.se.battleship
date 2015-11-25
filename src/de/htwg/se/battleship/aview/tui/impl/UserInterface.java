/**
 *
 */
package de.htwg.se.battleship.aview.tui.impl;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observer;
import org.apache.log4j.Logger;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public abstract class UserInterface implements Observer {

   private final Logger logger = Logger.getLogger("de.htwg.se.battleship.aview.tui");
   private boolean process = true; //FIXME move this to controller
   private StringBuilder sb;

   @Override
   public void update(Event e) {
   }

   /**
    * Return process flag.
    *
    * @return
    */
   public boolean getProcess() {
      return process;
   }

   /**
    * Stop processing
    */
   protected void deactivateProcess() {
      process = false;
   }

   /**
    * Returns Logger instance
    *
    * @return Logger
    */
   protected Logger getLogger() {
      return logger;
   }

   /**
    * Game name as ASCII-Art:
    * http://patorjk.com/software/taag/#p=display&f=Big&t=Battleship
    */
   protected String header() {
      sb = new StringBuilder("\n");
      appendHeaderFrame();
      append("*      ____        _   _   _           _     _            *");
      append("*     |  _ \\      | | | | | |         | |   (_)           *");
      append("*     | |_) | __ _| |_| |_| | ___  ___| |__  _ _ __       *");
      append("*     |  _ < / _` | __| __| |/ _ \\/ __| '_ \\| | '_ \\      *");
      append("*     | |_) | (_| | |_| |_| |  __/\\__ \\ | | | | |_) |     *");
      append("*     |____/ \\__,_|\\__|\\__|_|\\___||___/_| |_|_| .__/      *");
      append("*                                             | |         *");
      append("*                                             |_|         *");
      appendHeaderFrame();
      return sb.toString();
   }

   private void appendHeaderFrame() {
      append("***********************************************************");
   }

   private void append(String s) {
      sb.append(s + "\n");
   }

   protected void output(String string) {
      getLogger().info(string);
   }

   /**
    * Show Some Hints for the user
    */
   public abstract void showText();

   /**
    * Send input to UI.
    *
    * @param input String
    * @return boolean
    */
   public abstract UserInterface executeInput(String input);

}
