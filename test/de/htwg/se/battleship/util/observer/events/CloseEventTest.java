package de.htwg.se.battleship.util.observer.events;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;

public class CloseEventTest {
    private static final String MESSAGE = "terminated";

    @Test
    public void test() {

        Event close = new CloseEvent();
        assertEquals(MESSAGE, close.getMessage());
    }

}
