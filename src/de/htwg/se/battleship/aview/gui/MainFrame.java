package de.htwg.se.battleship.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.Player;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.Observer;

/**
 * @author Philipp Daniels<philipp.daniels@gmail.com>
 *
 */
public abstract class MainFrame extends JFrame implements WindowListener, KeyListener, ActionListener, Observer  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @param player1
     * @param player2
     */
    public abstract void newGame(Player player1, Player player2);

    /**
     * Initialize game-fields.
     */
    public abstract void initGamefield();

    /**
     * Reset buttons.
     */
    public abstract void resetButtons();

    /**
     * Swap panel.
     */
    public abstract void swapPanel();

    /**
     * 
     * @param e
     */
    public abstract void update(InitGame e);

    @Override
    public abstract void windowClosing(WindowEvent arg0);

    @Override
    public abstract void windowOpened(WindowEvent arg0);

    @Override
    public abstract void keyPressed(KeyEvent ke);

    @Override
    public abstract void actionPerformed(ActionEvent e);

    /**
     * 
     * @param e
     */
    public abstract void update(Winner e);

    /**
     * 
     * @param e
     */
    public abstract void update(CloseProgamm e);

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowActivated(java.awt.event.WindowEvent)
     */
    @Override
    public void windowActivated(WindowEvent e) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowClosed(java.awt.event.WindowEvent)
     */
    @Override
    public void windowClosed(WindowEvent arg0) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowDeactivated(java.awt.event.WindowEvent)
     */
    @Override
    public void windowDeactivated(WindowEvent arg0) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowDeiconified(java.awt.event.WindowEvent)
     */
    @Override
    public void windowDeiconified(WindowEvent arg0) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowIconified(java.awt.event.WindowEvent)
     */
    @Override
    public void windowIconified(WindowEvent arg0) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(KeyEvent ke) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(KeyEvent ke) {}

    /* (non-Javadoc)
     * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#update(de.htwg.se.battleship.util.observer.Event)
     */
    @Override
    public void update(Event e) { }

}