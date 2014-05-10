package de.htwg.se.battleship.aview.gui;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import org.junit.Test;

import de.htwg.se.battleship.controller.IController;
import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.Player;

public class MainFrameTest {

    private class TestMainFrame extends MainFrame {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public TestMainFrame(IController controller, IInitGameController initC) {
        }

        @Override
        public void newGame(Player player1, Player player2) {}

        @Override
        public void initGamefield() {}

        @Override
        public void resetButtons() { }

        @Override
        public void swapPanel() {}

        @Override
        public void update(InitGame e) {}

        @Override
        public void windowClosing(WindowEvent arg0) {}

        @Override
        public void windowOpened(WindowEvent arg0) {}

        @Override
        public void keyPressed(KeyEvent ke) {}

        @Override
        public void actionPerformed(ActionEvent e) {}

        @Override
        public void update(Winner e) {}

        @Override
        public void update(CloseProgamm e) {}

    }

    @Test
    public void test() {
        MainFrame mainFrame = new TestMainFrame(null, null);
        mainFrame.windowActivated(null);
        mainFrame.windowClosed(null);
        mainFrame.windowDeactivated(null);
        mainFrame.windowDeiconified(null);
        mainFrame.windowIconified(null);
        mainFrame.keyTyped(null);
        mainFrame.keyReleased(null);
        mainFrame.update(null);
    }
}