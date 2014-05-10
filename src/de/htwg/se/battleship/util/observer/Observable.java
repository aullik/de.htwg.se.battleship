package de.htwg.se.battleship.util.observer;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Manage IObserver and send Event to IObserver
 * 
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class Observable implements IObservable {

    private final Logger logger = Logger.getLogger("de.htwg.se.battleship.util.observer");

    /**
     * List of Observers
     */
    private final List<IObserver> subscribers = new ArrayList<IObserver>(1);

    @Override
    public void addObserver(IObserver s) {
        subscribers.add(s);
    }

    @Override
    public void removeObserver(IObserver s) {
        subscribers.remove(s);
    }

    @Override
    public void removeAllObservers() {
        subscribers.clear();
    }

    @Override
    public void notifyObservers() {
        notifyObservers(null);
    }

    @Override
    public void notifyObservers(Event e) {
        iterateOberserver(e);
    }

    private void iterateOberserver(Event e) {
        for (Iterator<IObserver> iter = subscribers.iterator(); iter.hasNext();) {
            IObserver observer = iter.next();

            if (isEmptyEvent(e)) {
                callDefaultUpdateMethod(observer, e);
                continue;
            }

            executeNotification(observer, e);
        }
    }

    private boolean isEmptyEvent(Event e) {
        return (e == null);
    }

    private void callDefaultUpdateMethod(IObserver o, Event e) {
        o.update(e);
    }

    private void executeNotification(IObserver o, Event e) {
        try {
            tryNotifyEventSpecificUpdateMethod(o, e);
        } catch (InvocationTargetException ex) {
            logExceptionFromUpdate(ex);
        } catch (Exception ex) {
            callDefaultUpdateMethod(o, e);
        }
    }

    private void tryNotifyEventSpecificUpdateMethod(IObserver o, Event e) throws
    NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        Class<? extends Event> eventClass        = e.getClass();
        Class<? extends IObserver> observerClass = o.getClass();

        Method update = observerClass.getMethod("update", eventClass);
        update.invoke(o, e);
    }

    private void logExceptionFromUpdate(InvocationTargetException e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.getCause().printStackTrace(pw);
        logger.error(sw.toString());
    }
}
