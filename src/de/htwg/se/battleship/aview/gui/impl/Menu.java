/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package de.htwg.se.battleship.aview.gui.impl;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.se.battleship.aview.gui.Action;
import de.htwg.se.battleship.aview.gui.action.CloseAction;
import de.htwg.se.battleship.aview.gui.action.ContinueAction;
import de.htwg.se.battleship.aview.gui.action.RenamePlayerAction;
import de.htwg.se.battleship.aview.gui.action.ResetGameAction;
import de.htwg.se.battleship.aview.gui.action.GameModeAction;
import de.htwg.se.battleship.aview.gui.action.StartAction;
import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.SetPlayerSuccess;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author aullik
 */
public class Menu extends JPanel implements ActionListener, IObserver {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final int GAMESTATE_NOGAME    = 0;
    public static final int GAMESTATE_SPGAME    = 1;
    public static final int GAMESTATE_MPGAME    = 2;
    public static final int GAMESTATE_SPSETSHIP = 3;
    public static final int GAMESTATE_MPSETSHIP = 4;
    public static final String  STDPLAYER_1         = "Player 1";
    public static final String  STDPLAYER_2         = "Player 2";
    public static final String BUTTON_TEXT_RESET_PLAYER = "Reset Game";
    private static final int FONT_SCALING = 4;
    private final StringBuilder currentPlayer1;
    private final StringBuilder currentPlayer2;
    private JButton closeButton;
    private JPanel closeButtonPanel;
    private JButton startButton;
    private JPanel startButtonPanel;
    private JButton continueButton;
    private JPanel continueButtonPanel;
    private JButton renameP1Button;
    private JPanel renameP1ButtonPanel;
    private JButton renameP2Button;
    private JPanel renameP2ButtonPanel;
    private JButton resetgameButton;
    private JPanel resetgameButtonPanel;
    private JButton singleplayerButton;
    private JPanel singleplayerButtonPanel;
    private JButton multiplayerButton;
    private JPanel multiplayerButtonPanel;
    private Integer gameState;
    private final MainFrame parent;
    private final IController controller;
    private final IInitGameController initC;
    private final Map<JButton, Action> actions;

    /**
     * 
     * @param f
     * @param controller
     * @param initC
     */
    public Menu(MainFrame f, IController controller, IInitGameController initC) {
        this.gameState = GAMESTATE_NOGAME;

        currentPlayer1 = new StringBuilder(STDPLAYER_1);
        currentPlayer2 = new StringBuilder(STDPLAYER_2);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.parent = f;
        this.controller = controller;
        controller.addObserver(this);
        this.initC = initC;
        initC.addObserver(this);
        this.actions = new HashMap<JButton, Action>();
        initButtons();
        initActions();
    }

    /**
     * 
     * @param i
     */
    public void setGamestate(int i) {
        if (i < GAMESTATE_NOGAME || i > GAMESTATE_MPSETSHIP) {
            return;
        }
        if (gameState.equals(i)) {
            return;
        }
        gameState = i;
        clear();
        addPanels();
    }

    protected void clear() {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }
    }

    /**
     * 
     */
    public void updateButtons() {
        closeButton.setSelected(true);
        closeButton.setSelected(false);
        startButton.setSelected(true);
        startButton.setSelected(false);
        continueButton.setSelected(true);
        continueButton.setSelected(false);
        renameP1Button.setSelected(true);
        renameP1Button.setSelected(false);
        renameP2Button.setSelected(true);
        renameP2Button.setSelected(false);
        resetgameButton.setSelected(true);
        resetgameButton.setSelected(false);
        singleplayerButton.setSelected(true);
        singleplayerButton.setSelected(false);
        multiplayerButton.setSelected(true);
        multiplayerButton.setSelected(false);
    }

    private void initButtons() {
        Font myFont = new JButton().getFont();
        myFont = new Font(myFont.getName(), myFont.getStyle(), myFont.getSize() * FONT_SCALING);

        closeButton         = new JButton("Close Game");
        startButton         = new JButton("Start Game");
        continueButton      = new JButton("Continue");
        renameP1Button      = new JButton("Rename Player 1");
        renameP2Button      = new JButton("Rename Player 2");
        resetgameButton     = new JButton(BUTTON_TEXT_RESET_PLAYER);
        singleplayerButton  = new JButton("SinglePlayer");
        multiplayerButton   = new JButton("Multiplayer");

        closeButtonPanel        = new JPanel();
        startButtonPanel        = new JPanel();
        continueButtonPanel     = new JPanel();
        renameP1ButtonPanel     = new JPanel();
        renameP2ButtonPanel     = new JPanel();
        resetgameButtonPanel    = new JPanel();
        singleplayerButtonPanel = new JPanel();
        multiplayerButtonPanel  = new JPanel();

        initButton(closeButton,        closeButtonPanel,        myFont);
        initButton(startButton,        startButtonPanel,        myFont);
        initButton(continueButton,     continueButtonPanel,     myFont);
        initButton(renameP1Button,     renameP1ButtonPanel,     myFont);
        initButton(renameP2Button,     renameP2ButtonPanel,     myFont);
        initButton(resetgameButton,    resetgameButtonPanel,    myFont);
        initButton(singleplayerButton, singleplayerButtonPanel, myFont);
        initButton(multiplayerButton,  multiplayerButtonPanel,  myFont);
    }

    private void initButton(JButton button, JPanel panel, Font myFont) {
        button.setFont(myFont);
        button.addActionListener(this);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(button);
    }

    /**
     * 
     */
    public void addPanels() {
        if (gameState == GAMESTATE_NOGAME) {
            this.add(singleplayerButtonPanel);
            this.add(multiplayerButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_SPGAME) {
            this.add(startButtonPanel);
            this.add(renameP1ButtonPanel);
            this.add(resetgameButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_MPGAME) {
            this.add(startButtonPanel);
            this.add(renameP1ButtonPanel);
            this.add(renameP2ButtonPanel);
            this.add(resetgameButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_SPSETSHIP) {
            this.add(continueButtonPanel);
            this.add(resetgameButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_MPSETSHIP) {
            this.add(continueButtonPanel);
            this.add(resetgameButtonPanel);
            this.add(closeButtonPanel);
        }
    }

    private void initActions() {
        actions.put(startButton,        new StartAction(parent, controller, gameState, currentPlayer1, currentPlayer2, initC));
        actions.put(renameP1Button,     new RenamePlayerAction(parent, controller, currentPlayer1, renameP1Button));
        actions.put(renameP1Button,     new RenamePlayerAction(parent, controller, currentPlayer2, renameP2Button));
        actions.put(resetgameButton,    new ResetGameAction(parent, controller));
        actions.put(singleplayerButton, new GameModeAction(parent, controller, gameState, GAMESTATE_SPGAME));
        actions.put(multiplayerButton,  new GameModeAction(parent, controller, gameState, GAMESTATE_MPGAME));
        actions.put(continueButton,     new ContinueAction(parent, controller));
        actions.put(closeButton,        new CloseAction(parent, controller));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        Action a = actions.get(s);
        if (a != null) {
            a.perform();
        }
    }

    /**
     * 
     * @param e
     */
    public void update(SetPlayerSuccess e) {
        IPlayer player1 = e.getPlayer();
        e.getRound().next();

        parent.newGame(player1, e.getPlayer());
        e.getRound().next();
        parent.swapPanel();
    }

    /**
     * 
     * @param e
     */
    public void update(Winner e) {
        this.gameState = GAMESTATE_NOGAME;
        clear();
        addPanels();
        currentPlayer1.setLength(0);
        currentPlayer1.append(STDPLAYER_1);
        currentPlayer2.setLength(0);
        currentPlayer2.append(STDPLAYER_2);
        parent.repaint();
    }

    @Override
    public void update(Event e) {

    }
}
