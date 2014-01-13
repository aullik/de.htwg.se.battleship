package de.htwg.se.battleship.util.observer.events;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;

public class ErrorEventTest {

    private static final String STDMESSAGE = "Test1";

    @Test
    public void test() {
        Event event = new ErrorEvent(STDMESSAGE);
        assertEquals(STDMESSAGE, event.getMessage());
    }

}
