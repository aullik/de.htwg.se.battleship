package de.htwg.se.battleship.aview.tui.command.impl;

import de.htwg.se.battleship.aview.tui.command.CommandBase;

/**
 * @author aullik on 01.03.2016.
 */
public class ExitGame extends CommandBase {

   private final static String COMMAND = "exit";
   private final static String DESC = "Exit the program.";
   private final Runnable exitGame;

   public ExitGame(final Runnable exitGame) {
      super(COMMAND, DESC);
      this.exitGame = exitGame;
   }

   @Override
   public void doAction() {
      exitGame.run();
   }
}
