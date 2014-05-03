/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package de.htwg.se.battleship.aview.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

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
@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener, IObserver {

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

    public Menu(MainFrame f, IController controller, IInitGameController initC) {
        this.gameState = GAMESTATE_NOGAME;
        initButtons();
        setButtons();
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
        initActions();
    }

    public void setGamestate(int i) {
        if (i < GAMESTATE_NOGAME || i > GAMESTATE_MPSETSHIP) {
            return;
        }
        if (gameState.equals(i)) {
            return;
        }
        gameState = i;
        clear();
        setButtons();
    }

    protected void clear() {
        for (Component c : this.getComponents()) {
            this.remove(c);
        }
    }

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

        closeButton = new JButton("Close Game");
        Font myFont = closeButton.getFont();
        myFont = new Font(myFont.getName(), myFont.getStyle(),
                myFont.getSize() * FONT_SCALING);
        closeButton.addActionListener(this);
        closeButton.setFont(myFont);
        closeButtonPanel = new JPanel();
        closeButtonPanel.setLayout(new BoxLayout(closeButtonPanel,
                BoxLayout.X_AXIS));
        closeButtonPanel.add(closeButton);

        startButton = new JButton("Start Game");
        startButton.setFont(myFont);
        startButton.addActionListener(this);
        startButtonPanel = new JPanel();
        startButtonPanel.setLayout(new BoxLayout(startButtonPanel,
                BoxLayout.X_AXIS));
        startButtonPanel.add(startButton);

        continueButton = new JButton("Continue");
        continueButton.setFont(myFont);
        continueButton.addActionListener(this);
        continueButtonPanel = new JPanel();
        continueButtonPanel.setLayout(new BoxLayout(continueButtonPanel,
                BoxLayout.X_AXIS));
        continueButtonPanel.add(continueButton);

        renameP1Button = new JButton("Rename Player 1");
        renameP1Button.setFont(myFont);
        renameP1Button.addActionListener(this);
        renameP1ButtonPanel = new JPanel();
        renameP1ButtonPanel.setLayout(new BoxLayout(renameP1ButtonPanel,
                BoxLayout.X_AXIS));
        renameP1ButtonPanel.add(renameP1Button);

        renameP2Button = new JButton("Rename Player 2");
        renameP2Button.setFont(myFont);
        renameP2Button.addActionListener(this);
        renameP2ButtonPanel = new JPanel();
        renameP2ButtonPanel.setLayout(new BoxLayout(renameP2ButtonPanel,
                BoxLayout.X_AXIS));
        renameP2ButtonPanel.add(renameP2Button);

        resetgameButton = new JButton(BUTTON_TEXT_RESET_PLAYER);
        resetgameButton.setFont(myFont);
        resetgameButton.addActionListener(this);
        resetgameButtonPanel = new JPanel();
        resetgameButtonPanel.setLayout(new BoxLayout(resetgameButtonPanel,
                BoxLayout.X_AXIS));
        resetgameButtonPanel.add(resetgameButton);

        singleplayerButton = new JButton("SinglePlayer");
        singleplayerButton.setFont(myFont);
        singleplayerButton.addActionListener(this);
        singleplayerButtonPanel = new JPanel();
        singleplayerButtonPanel.setLayout(new BoxLayout(
                singleplayerButtonPanel, BoxLayout.X_AXIS));
        singleplayerButtonPanel.add(singleplayerButton);

        multiplayerButton = new JButton("Multiplayer");
        multiplayerButton.setFont(myFont);
        multiplayerButton.addActionListener(this);
        multiplayerButtonPanel = new JPanel();
        multiplayerButtonPanel.setLayout(new BoxLayout(multiplayerButtonPanel,
                BoxLayout.X_AXIS));
        multiplayerButtonPanel.add(multiplayerButton);

    }

    protected void setButtons() {
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

    public void update(SetPlayerSuccess e) {
        IPlayer player1 = e.getPlayer();
        e.getRound().next();

        parent.newGame(player1, e.getPlayer());
        e.getRound().next();
        parent.swapPanel();
    }

    public void update(Winner e) {
        this.gameState = GAMESTATE_NOGAME;
        clear();
        setButtons();
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
