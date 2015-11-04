package de.htwg.se.battleship.util.singleton;

import java.util.function.Supplier;

/**
 * Created by aullik on 04.11.2015.
 */
public class SingletonSupplier<T> implements Supplier<T> {

   private final Supplier<T> supp;

   private T value = null;

   public SingletonSupplier(Supplier<T> supp) {
      this.supp = supp;
   }

   @Override
   public T get() {
      if (value == null)
         value = supp.get();

      return value;
   }
}
