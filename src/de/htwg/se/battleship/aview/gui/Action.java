package de.htwg.se.battleship.aview.gui;

import de.htwg.se.battleship.aview.gui.impl.MainFrame;
import de.htwg.se.battleship.controller.IController;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public abstract class Action {

    private final MainFrame mainFrame;
    private final IController controller;

    /**
     * Create new instance of an Action with all needed dependencies.
     * @param panel
     */
    public Action(MainFrame mainFrame, IController controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
    }

    protected MainFrame getMainFrame() {
        return mainFrame;
    }

    protected IController getController() {
        return controller;
    }

    /**
     * Execute internal business logic of an action
     */
    public abstract void perform();
}