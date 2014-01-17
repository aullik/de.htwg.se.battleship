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

/**
 * @author aullik
 */
@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener {

    public static final Integer GAMESTATE_NOGAME    = 0;
    public static final Integer GAMESTATE_SPGAME    = 1;
    public static final Integer GAMESTATE_MPGAME    = 2;
    public static final Integer GAMESTATE_SPSETSHIP = 3;
    public static final Integer GAMESTATE_MPSETSHIP = 4;
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
    private JButton             endgameButton;
    private JPanel              endgameButtonPanel;
    private JButton             singleplayerButton;
    private JPanel              singleplayerButtonPanel;
    private JButton             multiplayerButton;
    private JPanel              multiplayerButtonPanel;
    private Integer             gameState;
    private MainFrame           parent;

    public Menu(MainFrame f) {
        this.gameState = GAMESTATE_NOGAME;
        initButtons();
        addButtons();
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.parent = f;

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
        addButtons();
    }

    private void clear() {
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
        endgameButton.setSelected(true);
        endgameButton.setSelected(false);
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

        renameP1Button = new JButton("rename Player 1");
        renameP1Button.setFont(myFont);
        renameP1Button.addActionListener(this);
        renameP1ButtonPanel = new JPanel();
        renameP1ButtonPanel.setLayout(new BoxLayout(renameP1ButtonPanel,
                BoxLayout.X_AXIS));
        renameP1ButtonPanel.add(renameP1Button);

        renameP2Button = new JButton("rename Player 2");
        renameP2Button.setFont(myFont);
        renameP2Button.addActionListener(this);
        renameP2ButtonPanel = new JPanel();
        renameP2ButtonPanel.setLayout(new BoxLayout(renameP2ButtonPanel,
                BoxLayout.X_AXIS));
        renameP2ButtonPanel.add(renameP2Button);

        endgameButton = new JButton("End Game");
        endgameButton.setFont(myFont);
        endgameButton.addActionListener(this);
        endgameButtonPanel = new JPanel();
        endgameButtonPanel.setLayout(new BoxLayout(endgameButtonPanel,
                BoxLayout.X_AXIS));
        endgameButtonPanel.add(endgameButton);

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

    private void addButtons() {
        if (gameState == GAMESTATE_NOGAME) {
            this.add(singleplayerButtonPanel);
            this.add(multiplayerButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_SPGAME) {
            this.add(startButtonPanel);
            this.add(renameP1ButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_MPGAME) {
            this.add(startButtonPanel);
            this.add(renameP1ButtonPanel);
            this.add(renameP2ButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_SPSETSHIP) {
            this.add(continueButtonPanel);
            this.add(endgameButtonPanel);
            this.add(closeButtonPanel);
            return;
        }
        if (gameState == GAMESTATE_MPSETSHIP) {
            this.add(continueButtonPanel);
            this.add(endgameButtonPanel);
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
                System.exit(0);
            }
            return;
        }
        if (s == renameP1Button) {
            return;
        }
        if (s == renameP2Button) {
            return;
        }
        if (s == endgameButton) {
            int n = JOptionPane.showConfirmDialog(this.getParent(),
                    "Are you sure you want to abort current Game?",
                    "Confirm Dialog", JOptionPane.YES_NO_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                // TODO:
            }
            return;
        }
        if (s == singleplayerButton) {
            parent.newGame();
            parent.swapPanel();
            return;
        }
        if (s == multiplayerButton) {
            parent.newGame();
            parent.swapPanel();
            return;
        }
        if (s == continueButton) {
            parent.swapPanel();
            return;
        }
        if (s == startButton) {
        }
    }
}
