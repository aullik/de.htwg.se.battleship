package de.htwg.se.battleship.util.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;
import de.htwg.se.battleship.util.observer.Observable;

public class OberservableTest {
    private boolean ping=false;
    private TestObserver testObserver;
    private Observable testObservable;

    class TestObserver implements IObserver {
        @Override
        public void update(Event e) {
            ping=true;
        }

        public void update(TestEvent1 e) {
            ping=true;
        }

    }

    class TestEvent1 extends Event {}

    class TestEvent2 extends Event {}

    @Before
    public void setUp() throws Exception {
        testObserver = new TestObserver();
        testObservable = new Observable();
        testObservable.addObserver(testObserver);
    }

    @Test
    public void testNotify() {
        assertFalse(ping);
        testObservable.notifyObservers();
        assertTrue(ping);
    }

    @Test
    public void testNotifyWithEvent() {
        assertFalse(ping);
        testObservable.notifyObservers(new TestEvent1());
        assertTrue(ping);
    }

    @Test
    public void testNotifyWithEventFallback() {
        assertFalse(ping);
        testObservable.notifyObservers(new TestEvent2());
        assertTrue(ping);
    }

    @Test
    public void testRemove() {
        assertFalse(ping);
        testObservable.removeObserver(testObserver);
        testObservable.notifyObservers();
        assertFalse(ping);
    }

    @Test
    public void testRemoveAll() {
        assertFalse(ping);
        testObservable.removeAllObservers();
        testObservable.notifyObservers();
        assertFalse(ping);
    }

}
