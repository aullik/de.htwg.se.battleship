package de.htwg.se.battleship.util._observer.impl;

import de.htwg.se.battleship.util._observer.Event;
import de.htwg.se.battleship.util._observer.OLDObservable;
import de.htwg.se.battleship.util._observer.OLDObserver;
import org.apache.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Manage OLDObserver and send Event to OLDObserver
 *
 * @author Philipp Daniels <philipp.daniels@gmail.com>
 */
public class ObservableImpl implements OLDObservable {

   private final static String OBSERVER_UPDATE_METHOD = getOBSERVER_UPDATE_METHOD();

   private static String getOBSERVER_UPDATE_METHOD() {
      return OLDObserver.class.getMethods()[0].getName();
   }

   /**
    * List of Observers
    */
   private final List<OLDObserver> subscribers = new ArrayList<OLDObserver>(1);
   private final Logger logger = Logger.getLogger("de.htwg.se.battleship.util.observer");

   @Override
   public void addObserver(OLDObserver s) {
      subscribers.add(s);
   }

   @Override
   public void removeObserver(OLDObserver s) {
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
      for (Iterator<OLDObserver> iter = subscribers.iterator(); iter.hasNext(); ) {
         OLDObserver observer = iter.next();

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

   private void callDefaultUpdateMethod(OLDObserver o, Event e) {
      o.update(e);
   }

   private void executeNotification(OLDObserver o, Event e) {
      try {
         tryNotifyEventSpecificUpdateMethod(o, e);
      } catch (InvocationTargetException ex) {
         logExceptionFromUpdate(ex);
      } catch (Exception ex) {
         callDefaultUpdateMethod(o, e);
      }
   }

   private void tryNotifyEventSpecificUpdateMethod(OLDObserver o, Event e) throws
                                                                           NoSuchMethodException,
                                                                           IllegalAccessException,
                                                                           InvocationTargetException {

      Class<? extends Event> eventClass = e.getClass();
      Class<? extends OLDObserver> observerClass = o.getClass();

      Method update = observerClass.getMethod(OBSERVER_UPDATE_METHOD, eventClass);
      update.invoke(o, e);
   }

   private void logExceptionFromUpdate(InvocationTargetException e) {
      StringWriter sw = new StringWriter();
      PrintWriter pw = new PrintWriter(sw);
      e.getCause().printStackTrace(pw);
      logger.error(sw.toString());
   }
}
