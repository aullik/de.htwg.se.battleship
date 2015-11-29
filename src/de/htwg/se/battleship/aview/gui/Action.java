package de.htwg.se.battleship.aview.gui;

import de.htwg.se.battleship.controller.IMenuController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 */
public abstract class Action {

   private final MainFrame mainFrame;
   private final IMenuController controller;

   /**
    * Create new instance of an Action with all needed dependencies.
    *
    * @param panel
    */
   public Action(MainFrame mainFrame, IMenuController controller) {
      this.mainFrame = mainFrame;
      this.controller = controller;
   }

   protected MainFrame getMainFrame() {
      return mainFrame;
   }

   protected IMenuController getController() {
      return controller;
   }

   /**
    * Execute internal business logic of an action
    */
   public abstract void perform();
}