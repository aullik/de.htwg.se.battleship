package de.htwg.se.battleship;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;

import de.htwg.se.battleship.aview.gui.MainFrame;
import de.htwg.se.battleship.aview.tui.TextUI;
import de.htwg.se.battleship.aview.tui.menuentry.Close;
import de.htwg.se.battleship.controller.event.CloseProgamm;
import de.htwg.se.battleship.controller.event.InitGame;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.model.Player;

/**
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class BattleshipTest {

    private static boolean tui = false;
    private static boolean gui = false;

    public static class TestTui extends TextUI {

        @Inject
        public TestTui() {
            tui = true;
        }
    }

    public static class TestGui extends MainFrame {

        @Inject
        public TestGui() {
            gui = true;
        }

        private static final long serialVersionUID = 1L;
        @Override
        public void newGame(Player player1, Player player2) {}
        @Override
        public void initGamefield() {}
        @Override
        public void resetButtons() {}
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

    private class TestModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(TextUI.class).to(TestTui.class);
            bind(MainFrame.class).to(TestGui.class);
        }

    }

    @Test
    public void test() {
        AbstractModule am = new TestModule();
        new Battleship(am);

        assertTrue(tui);
        assertTrue(gui);
    }

    private InputStream backup;

    @Before
    public void setUp() throws UnsupportedEncodingException {
        backup = System.in;

        String s = Close.CMD + System.getProperty("line.separator");
        System.setIn(new ByteArrayInputStream(s.getBytes("UTF-8")));
    }

    @After
    public void tearDown() {
        System.setIn(backup);
    }

    @Test
    public void testMain() {
        Battleship.main(new String[0]);
    }
}