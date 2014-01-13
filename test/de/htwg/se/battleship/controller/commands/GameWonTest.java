package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.INTInputState;
import de.htwg.se.battleship.controller.InputController;
import de.htwg.se.battleship.controller.input.MainMenu;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class GameWonTest {
    private static final String   WON_MSG = "you won!";
    private static final String[] EMPTY   = new String[] { "", "", "" };

    private InputController       controller;
    private INTCommands           command;
    private Event[]               out;
    private INTInputState         main;

    @Before
    public void setup() {
        command = new GameWon();
        controller = new InputController();
        main = new MainMenu();
    }

    @Test
    public void actiontest() {
        out = command.action(EMPTY, null, controller);
        assertTrue(out[0] instanceof StandardEvent);
        assertEquals(WON_MSG, out[0].getMessage());
        assertTrue(out[1] instanceof StandardEvent);
        assertEquals(main.getEvent().getMessage(), out[1].getMessage());
    }

}
