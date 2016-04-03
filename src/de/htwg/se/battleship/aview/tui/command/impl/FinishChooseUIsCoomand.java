package de.htwg.se.battleship.aview.tui.command.impl;

import de.htwg.se.battleship.aview.tui.command.CommandBase;

/**
 */
public class FinishChooseUIsCoomand extends CommandBase {

   private final static String COMMAND = "finish";
   private final static String DESC = "Finish choosing the UIs for both Players.";
   private final Runnable onFinish;

   public FinishChooseUIsCoomand(final Runnable onFinish) {
      super(COMMAND, DESC);
      this.onFinish = onFinish;
   }

   @Override
   public void doAction() {
      onFinish.run();
   }
}
