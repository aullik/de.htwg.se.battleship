package de.htwg.se.battleship.util.observer;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import de.htwg.se.battleship.TestAppender;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;
import de.htwg.se.battleship.util.observer.Observable;

public class OberservableTest {
    private boolean ping;
    private boolean pong;
    private TestObserver observer;
    private Observable observable;

    private class TestObserver implements IObserver {

        public TestObserver() {
            ping=false;
            pong=false;
        }

        @Override
        public void update(Event e) {
            ping=true;
        }

        @SuppressWarnings("unused")
        public void update(TestEvent2 e) {
            pong=true;
        }
    }

    private class TestObserverException implements IObserver {

        @Override
        public void update(Event e) {
        }

        @SuppressWarnings("unused")
        public void update(TestEvent1 e) {
            throw new IllegalArgumentException();
        }

    }

    class TestEvent1 implements Event {}
    class TestEvent2 implements Event {}

    private void assertPingPongFalse() {
        assertFalse(ping);
        assertFalse(pong);
    }

    private void assertPingTrue() {
        assertTrue(ping);
        assertFalse(pong);
    }

    private void assertPongTrue() {
        assertFalse(ping);
        assertTrue(pong);
    }

    @Before
    public void setUp() throws Exception {
        observer = new TestObserver();
        observable = new Observable();
        observable.addObserver(observer);
    }

    public void testInitialization() {
        assertPingPongFalse();
    }

    @Test
    public void testNotifyNull() {
        observable.notifyObservers();
        assertPingTrue();
    }

    @Test
    public void testNotifyEvent() {
        observable.notifyObservers(new TestEvent1());
        assertPingTrue();
    }

    @Test
    public void testNotifyTestEvent() {
        observable.notifyObservers(new TestEvent2());
        assertPongTrue();
    }

    @Test
    public void testRemove() {
        observable.removeObserver(observer);
        observable.notifyObservers();
        assertPingPongFalse();
    }

    @Test
    public void testRemoveAll() {
        observable.removeAllObservers();
        observable.notifyObservers();
        assertPingPongFalse();
    }

    @Test
    public void testInvocationTargetException() {
        TestAppender testAppender = new TestAppender();
        Logger.getRootLogger().removeAllAppenders();
        Logger.getRootLogger().addAppender(testAppender);

        observable.addObserver(new TestObserverException());
        observable.notifyObservers(new TestEvent1());

        String log = testAppender.getLog();

        assertTrue(log.contains(TestObserverException.class.getName()));
        assertTrue(log.contains(IllegalArgumentException.class.getName()));
        assertFalse(log.contains("Caused by: "));
        assertFalse(log.contains(InvocationTargetException.class.getName()));
    }

}
