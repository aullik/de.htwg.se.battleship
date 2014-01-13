package de.htwg.se.battleship.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.commands.CloseGame;
import de.htwg.se.battleship.controller.commands.GameWon;
import de.htwg.se.battleship.controller.commands.NewGame;
import de.htwg.se.battleship.controller.commands.RenamePlayer;
import de.htwg.se.battleship.controller.commands.StartGame;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.controller.input.MainMenu;

public class InputControllerTest {

    private static final InitGame INIT        = new InitGame();
    private static final int      Player1     = 1;
    private static final String   LINE        = "line";
    private static final String   NOT_IN_LINE = "x";
    private static final String[] OUT         = { LINE, "", "" };
    InputController               controller  = new InputController();
    INTInputState                 state       = new MainMenu();
    INTCommands                   close       = new CloseGame();
    INTCommands                   newgame     = new NewGame();
    INTCommands                   start       = new StartGame(INIT);
    INTCommands                   player      = new RenamePlayer(Player1, INIT);
    INTCommands                   win         = new GameWon();
    Set<String>                   set         = new TreeSet<String>();

    @Before
    public void setUp() {
        set.add(NOT_IN_LINE);

    }

    @Test
    public void test() {
        assertEquals(state.getEvent(), controller.setState(state));
        assertTrue(controller.processInputLine(null));
        assertTrue(controller.processInputLine(newgame.getCommand()));
        assertTrue(controller.processInputLine(player.getCommand()
                + start.getCommand() + win.getCommand()));
        assertFalse(controller.processInputLine(close.getCommand()));
        assertArrayEquals(OUT, InputController.splitInput(LINE, set));

    }

}
