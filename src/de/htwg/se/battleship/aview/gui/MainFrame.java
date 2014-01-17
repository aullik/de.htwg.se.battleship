package de.htwg.se.battleship.aview.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.inject.Inject;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author aullik
 */

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements WindowListener, KeyListener,
        ActionListener, IObserver {

    private static final String  TITLE            = "Ballteship";
    private static final String  FILENAME         = "ocean_battleship.jpg";
    private static final Integer JPANEL_INGAME    = 0;
    private static final Integer JPANEL_MENUPANEL = 1;
    private BufferedImage        image;
    private Dimension            size;
    private Gamefield            field1;
    private Gamefield            field2;
    private JPanel               inGame;
    private JPanel               menuPanel;
    private Menu                 menu;
    private Integer              gameState;
    private JButton              openMenueButton;
    private IController          controller;
    private IInitGameController  initC;

    @Inject
    public MainFrame(IController controller, IInitGameController initC) {
        this.controller = controller;
        controller.addObserver(this);
        this.initC = initC;
        initC.addObserver(this);

        createImage();

        initPanels();
        this.add(menuPanel);

        this.getContentPane().validate();
        this.setVisible(true);
        repaint();

    }

    protected void initGamefield() {
        field1 = new Gamefield(image.getWidth() / 8, 10, this, initC);
        field1.selectable(false);
        field2 = new Gamefield(image.getWidth() / 8, 10, this, initC);
        field2.selectable(false);
    }

    protected void newGame(IPlayer player1, IPlayer player2) {
        field1.setPlayer(player1);
        field2.setPlayer(player2);
        JPanel fieldpanel = new JPanel();
        fieldpanel.setLayout(new BoxLayout(fieldpanel, BoxLayout.X_AXIS));
        fieldpanel.add(new JPanel());
        fieldpanel.add(field1);
        fieldpanel.add(new JPanel());
        fieldpanel.add(field2);
        fieldpanel.add(new JPanel());

        JPanel up = new JPanel();
        up.setLocation(0, 0);
        up.setLayout(new BoxLayout(up, BoxLayout.Y_AXIS));
        up.add(openMenueButton);
        up.add(new JPanel());

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(left, BoxLayout.LINE_AXIS));
        left.add(up);
        left.add(new JPanel());

        inGame = new JPanel();
        inGame.setLayout(new BoxLayout(inGame, BoxLayout.Y_AXIS));
        inGame.add(left);
        inGame.add(new JPanel());
        inGame.add(new JPanel());
        inGame.add(new JPanel());
        inGame.add(fieldpanel);
        inGame.add(new JPanel());
        this.getContentPane().validate();
    }

    private void initPanels() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(TITLE);
        this.addWindowListener(this);
        this.addKeyListener(this);
        this.setPreferredSize(size);
        this.setResizable(true);
        this.gameState = JPANEL_MENUPANEL;
        openMenueButton = new JButton("Menu");
        openMenueButton.addActionListener(this);
        menu = new Menu(this, controller, initC);
        menuPanel = new JPanel();

        JPanel ypanel = new JPanel();
        ypanel.setLayout(new BoxLayout(ypanel, BoxLayout.Y_AXIS));
        ypanel.add(new JPanel());
        ypanel.add(new JPanel());
        ypanel.add(menu);
        ypanel.add(new JPanel());

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.add(new JPanel());
        menuPanel.add(ypanel);
        menuPanel.add(new JPanel());

    }

    protected void resetButtons() {
        menu.clear();
        menu.setButtons();
        this.getContentPane().validate();
        repaint();

    }

    protected void swapPanel() {
        if (gameState == JPANEL_INGAME) {
            this.remove(inGame);
            this.add(menuPanel);
            gameState = JPANEL_MENUPANEL;
            this.getContentPane().validate();
            repaint();
            return;
        }
        if (gameState == JPANEL_MENUPANEL && inGame != null) {
            this.remove(menuPanel);
            this.add(inGame);
            gameState = JPANEL_INGAME;
            this.getContentPane().validate();
            repaint();

        }

    }

    private void createImage() {
        URI uri;

        try {
            uri = this.getClass().getResource(FILENAME).toURI();
            image = ImageIO.read(new File(uri.normalize()));
        } catch (Exception e) {
        }
        size = new Dimension(image.getWidth() / 2, image.getHeight() / 2);
    }

    public void update(InitGame e) {
        initC.init();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (field1 != null) {
            field1.repaint();
        }
        if (field2 != null) {
            field2.repaint();
        }
        this.pack();

        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), 0, 0,
                image.getWidth(), image.getHeight(), null);
        menu.updateButtons();

        openMenueButton.setSelected(true);
        openMenueButton.setSelected(false);
        this.requestFocus();

    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        int n = JOptionPane.showConfirmDialog(this.getParent(),
                "Are you sure you want to quit?", "Confirm Dialog",
                JOptionPane.YES_NO_OPTION);

        if (n == JOptionPane.YES_OPTION) {
            controller.close();
        }

    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        this.requestFocus();

    }

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            swapPanel();
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openMenueButton) {
            swapPanel();
        }
    }

    @Override
    public void update(Event e) {

    }

    public void update(CloseProgamm e) {
        System.exit(0);
    }

}
