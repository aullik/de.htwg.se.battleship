package de.htwg.se.battleship.model.impl;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author aullik on 04.01.2016.
 */
public abstract class AbstractObservable implements Observable {

   private final List<InvalidationListener> listeners;


   public AbstractObservable() {
      this.listeners = new LinkedList<>();
   }

   @Override
   public void addListener(final InvalidationListener listener) {
      listeners.add(listener);
   }

   @Override
   public void removeListener(final InvalidationListener listener) {
      listeners.remove(listener);
   }

   protected void invalidated() {
      listeners.forEach(i -> i.invalidated(this));
   }
}
