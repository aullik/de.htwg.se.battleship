/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package de.htwg.se.battleship.aview.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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

    public static final Integer GAMESTATE_NOGAME    = 0;
    public static final Integer GAMESTATE_SPGAME    = 1;
    public static final Integer GAMESTATE_MPGAME    = 2;
    public static final Integer GAMESTATE_SPSETSHIP = 3;
    public static final Integer GAMESTATE_MPSETSHIP = 4;
    public static final String  STDPLAYER_1         = "Player 1";
    public static final String  STDPLAYER_2         = "Player 2";
    private String              currentPlayer_1;
    private String              currentPlayer_2;
    private JButton             closeButton;
    private JPanel              closeButtonPanel;
    private JButton             startButton;
    private JPanel              startButtonPanel;
    private JButton             continueButton;
    private JPanel              continueButtonPanel;
    private JButton             renameP1Button;
    private JPanel              renameP1ButtonPanel;
    private JButton             renameP2Button;
    private JPanel              renameP2ButtonPanel;
    private JButton             resetgameButton;
    private JPanel              resetgameButtonPanel;
    private JButton             singleplayerButton;
    private JPanel              singleplayerButtonPanel;
    private JButton             multiplayerButton;
    private JPanel              multiplayerButtonPanel;
    private Integer             gameState;
    private MainFrame           parent;
    private IController         controller;
    private IInitGameController initC;

    public Menu(MainFrame f, IController controller, IInitGameController initC) {
        this.gameState = GAMESTATE_NOGAME;
        initButtons();
        setButtons();
        currentPlayer_1 = STDPLAYER_1;
        currentPlayer_2 = STDPLAYER_2;
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.parent = f;
        this.controller = controller;
        controller.addObserver(this);
        this.initC = initC;
        initC.addObserver(this);

    }

    public void setGamestate(Integer i) {
        if (i < GAMESTATE_NOGAME || i > GAMESTATE_MPSETSHIP) {
            return;
        }
        if (gameState == i) {
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
                myFont.getSize() * 4);
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

        resetgameButton = new JButton("Reset Game");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object s = e.getSource();
        if (s == closeButton) {
            int n = JOptionPane.showConfirmDialog(this.getParent(),
                    "Are you sure you want to quit?", "Confirm Dialog",
                    JOptionPane.YES_NO_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                controller.close();
            }
            return;
        }
        if (s == renameP1Button) {
            String old = currentPlayer_1;
            currentPlayer_1 = (String) JOptionPane.showInputDialog(this,
                    "Enter new Player name:", renameP1Button.getText(),
                    JOptionPane.QUESTION_MESSAGE, null /* icon */,
                    null /* possibilities */, null /* default */);
            if (currentPlayer_1.isEmpty()) {
                currentPlayer_1 = old;
                return;
            }
            renameP1Button.setText("Rename " + currentPlayer_1);
            parent.repaint();
            return;
        }
        if (s == renameP2Button) {
            String old = currentPlayer_2;
            currentPlayer_2 = (String) JOptionPane.showInputDialog(this,
                    "Enter new Player name:", renameP1Button.getText(),
                    JOptionPane.QUESTION_MESSAGE, null /* icon */,
                    null /* possibilities */, null /* default */);
            if (currentPlayer_2.isEmpty()) {
                currentPlayer_2 = old;
                return;
            }
            renameP2Button.setText("Rename " + currentPlayer_2);
            parent.repaint();
            return;
        }
        if (s == resetgameButton) {
            int n = JOptionPane.showConfirmDialog(this.getParent(),
                    "Are you sure you want to abort current Game?",
                    resetgameButton.getText(), JOptionPane.YES_NO_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                controller.reset();
            }
            return;
        }
        if (s == singleplayerButton) {
            gameState = GAMESTATE_SPGAME;
            parent.resetButtons();
            controller.newGame();
            parent.initGamefield();
            return;
        }
        if (s == multiplayerButton) {
            gameState = GAMESTATE_MPGAME;
            parent.resetButtons();
            controller.newGame();
            parent.initGamefield();
            return;
        }
        if (s == continueButton) {
            parent.swapPanel();
            return;
        }
        if (s == startButton) {
            if (gameState == GAMESTATE_SPGAME) {
                currentPlayer_2 = null;
                gameState = GAMESTATE_SPSETSHIP;
            } else {
                gameState = GAMESTATE_MPSETSHIP;
            }
            initC.player(currentPlayer_1, currentPlayer_2);
            parent.resetButtons();

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
        currentPlayer_1 = STDPLAYER_1;
        currentPlayer_2 = STDPLAYER_2;
        parent.repaint();

    }

    @Override
    public void update(Event e) {

    }
}
