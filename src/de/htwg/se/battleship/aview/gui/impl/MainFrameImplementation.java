package de.htwg.se.battleship.aview.gui.impl;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.IMenuController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.Player;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author aullik
 */
public class MainFrameImplementation extends MainFrame {

   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private static final String TITLE = "Ballteship";
   private static final String FILENAME = File.separator + "Resource" + File.separator + "ocean_battleship.jpg";
   private static final int JPANEL_INGAME = 0;
   private static final int JPANEL_MENUPANEL = 1;
   private static final int GAMEFIELD_SCALING = 8;
   private static final int CELL_QUANTITY = 10;
   private BufferedImage image;
   private Dimension size;
   private Gamefield field1;
   private Gamefield field2;
   private JPanel inGame;
   private JPanel menuPanel;
   private Menu menu;
   private Integer gameState;
   private JButton openMenueButton;
   private final IMenuController controller;
   private final IInitGameController initC;

   /**
    * @param controller GameController
    * @param initC      Ingame Controller
    * @throws URISyntaxException
    * @throws IOException
    */
   public MainFrameImplementation(IMenuController controller, IInitGameController initC) throws URISyntaxException,
                                                                                                IOException {

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

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#initGamefield()
    */
   @Override
   public void initGamefield() {
      field1 = new Gamefield(image.getWidth() / GAMEFIELD_SCALING, CELL_QUANTITY, this, initC);
      field2 = new Gamefield(image.getWidth() / GAMEFIELD_SCALING, CELL_QUANTITY, this, initC);
   }

   @Override
   public void newGame(Player player1, Player player2) {
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
      openMenueButton = new JButton("TUIMenu");
      openMenueButton.addActionListener(this);
      menu = new Menu(this, controller, initC);
      menu.addPanels();
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

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#resetButtons()
    */
   @Override
   public void resetButtons() {
      menu.clear();
      menu.addPanels();
      this.getContentPane().validate();
      repaint();
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#swapPanel()
    */
   @Override
   public void swapPanel() {
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

   private void createImage() throws IOException {
      File f = new File(System.getProperty("user.dir") + FILENAME);
      image = ImageIO.read(f);
      size = new Dimension(image.getWidth() / 2, image.getHeight() / 2);
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#update(de.htwg.se.battleship.controller.event.InitGame)
    */
   @Override
   public void update(InitGame e) {
      initC.init();

   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#paint(java.awt.Graphics)
    */
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

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowClosing(java.awt.event.WindowEvent)
    */
   @Override
   public void windowClosing(WindowEvent arg0) {
      int n = JOptionPane.showConfirmDialog(this.getParent(),
            "Are you sure you want to quit?", "Confirm Dialog",
            JOptionPane.YES_NO_OPTION);

      if (n == JOptionPane.YES_OPTION) {
         controller.close();
      }
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#windowOpened(java.awt.event.WindowEvent)
    */
   @Override
   public void windowOpened(WindowEvent arg0) {
      this.requestFocus();
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#keyPressed(java.awt.event.KeyEvent)
    */
   @Override
   public void keyPressed(KeyEvent ke) {
      if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
         swapPanel();
      }
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#actionPerformed(java.awt.event.ActionEvent)
    */
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == openMenueButton) {
         swapPanel();
      }
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#update(de.htwg.se.battleship.controller.event.Winner)
    */
   @Override
   public void update(Winner e) {
      gameState = JPANEL_INGAME;
      swapPanel();
      field1 = null;
      field2 = null;
      this.getContentPane().validate();
      repaint();
      pack();
   }

   /* (non-Javadoc)
    * @see de.htwg.se.battleship.aview.gui.impl.MainFrame#update(de.htwg.se.battleship.controller.event.CloseProgamm)
    */
   @Override
   public void update(CloseProgamm e) {
      setVisible(false);
      dispose();
   }
}
