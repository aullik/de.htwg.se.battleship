package de.htwg.se.battleship.controller.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.controller.INTCommands;
import de.htwg.se.battleship.controller.input.InitGame;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.events.ContinueEvent;
import de.htwg.se.battleship.util.observer.events.ErrorEvent;
import de.htwg.se.battleship.util.observer.events.StandardEvent;

public class ChangeFieldsizeTest {
    private static final int    NEW_SIZE  = 99;
    private static final String SOMETHING = "what ever";
    private static final String MESSAGE   = "changed fieldsize to ";
    private static final String EMPTY     = "couldn't change fieldsize, currently: ";
    private static final String NO_INT    = " is no number";

    private InitGame            init      = new InitGame();
    private INTCommands         command   = new ChangeFieldsize(init);
    private String[]            in;
    private Event[]             out;
    private String              cont;

    @Before
    public void setup() {
        cont = (String) init.getKeySet().toArray()[0] + SOMETHING;
        in = new String[] { "", "", NEW_SIZE + cont };
        // new String[] { "2", command.getCommand(), cont };
    }

    @Test
    public void actiontest() {
        out = command.action(in, init, null);
        assertEquals(MESSAGE + NEW_SIZE, out[0].getMessage());
        assertTrue(out[0] instanceof StandardEvent);
        assertEquals(cont, out[1].getMessage());
        assertTrue(out[1] instanceof ContinueEvent);

        in = new String[] { "", "", SOMETHING };
        out = command.action(in, init, null);
        assertEquals(SOMETHING + NO_INT, out[0].getMessage());
        assertTrue(out[0] instanceof ErrorEvent);
        assertNull(out[1]);

        in = new String[] { "", "", "" };
        out = command.action(in, init, null);
        assertEquals(EMPTY + NEW_SIZE, out[0].getMessage());
        assertTrue(out[0] instanceof ErrorEvent);
        assertNull(out[1]);

    }

}
