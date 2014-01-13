package de.htwg.se.battleship.util.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;
import de.htwg.se.battleship.util.observer.Observable;

public class OberservableTest {
    private boolean ping=false;
    private boolean pong=false;
    private TestObserver observer;
    private Observable observable;

    class TestObserver implements IObserver {
        @Override
        public void update(Event e) {
            ping=true;
        }

        public void update(TestEvent2 e) {
            pong=true;
        }
    }

    class TestEvent1 implements Event {}
    class TestEvent2 implements Event {}

    @Before
    public void setUp() throws Exception {
        observer = new TestObserver();
        observable = new Observable();
        observable.addObserver(observer);
    }

    @Test
    public void testNotifyNull() {
        assertFalse(ping);
        observable.notifyObservers();
        assertTrue(ping);
    }

    @Test
    public void testNotifyEvent() {
        assertFalse(ping);
        observable.notifyObservers(new TestEvent1());
        assertTrue(ping);
    }

    @Test
    public void testNotifyTestEvent() {
        assertFalse(ping);
        assertFalse(pong);
        observable.notifyObservers(new TestEvent2());
        assertFalse(ping);
        assertTrue(pong);
    }

    @Test
    public void testRemove() {
        assertFalse(ping);
        observable.removeObserver(observer);
        observable.notifyObservers();
        assertFalse(ping);
    }

    @Test
    public void testRemoveAll() {
        assertFalse(ping);
        observable.removeAllObservers();
        observable.notifyObservers();
        assertFalse(ping);
    }

}
