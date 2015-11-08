package de.htwg.se.battleship.util.singleton;

import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.function.Supplier;

/**
 * Created by aullik on 08.11.2015.
 */
public class SingletonInjector {

   @FunctionalInterface
   private interface Injector {

      boolean inject(SingletonSupplier supp);
   }

   public static void resetValue(Object o) {
      resetValue(o.getClass());
   }

   public static void resetValue(Class<?> c) {
      injectSingleton_rek((Object) null, c);
   }


   public static <T> void injectSingletonSupplier(Supplier<T> supp, Class<T> clazz) {
      injectSingleton_rek(singletonSupplier -> injectSupplier(supp, singletonSupplier), clazz);
   }

   private static void handleNotFound() {
      Assert.fail("Could not Inject instance, no Singleton");
   }

   public static <T> void injectSingleton(T instance) {
      final Class<?> aClass = instance.getClass().getSuperclass();
      injectSingleton_rek(instance, aClass);
   }


   private static <T> void injectSingleton_rek(T instance, Class<?> type) {
      injectSingleton_rek(supp -> injectInstance(instance, supp), type);
   }

   private static void injectSingleton_rek(Injector injector, Class<?> type) {
      final boolean found = _injectSingleton_rek(injector, type);
      if (!found)
         handleNotFound();
   }

   private static boolean _injectSingleton_rek(Injector injector, Class<?> type) {
      if (type.equals(Object.class))
         return false;

      if (_injectSingleton(injector, type))
         return true;

      return _injectSingleton_rek(injector, type.getSuperclass());

   }


   public static <S, T extends S> void injectSingleton(T instance, Class<S> singletonClass) {
      final boolean found = _injectSingleton(supp -> injectInstance(instance, supp), singletonClass);
      if (!found)
         handleNotFound();
   }


   private static <S> boolean _injectSingleton(Injector injector, Class<S> singletonClass) {

      boolean ret = false;

      for (Field f : singletonClass.getDeclaredFields()) {
         final Class<?> type = f.getType();
         if (type.isAssignableFrom(SingletonSupplier.class)) {
            f.setAccessible(true);
            try {
               ret |= injector.inject((SingletonSupplier<?>) f.get(null));

            } catch (IllegalAccessException e) {
               Assert.fail("could not get value");
            }
         }
      }
      return ret;
   }


   private static boolean injectSupplier(Supplier supp, SingletonSupplier singletonSupplier) {
      return inject("supp", supp, singletonSupplier);
   }

   private static boolean injectInstance(Object instance, SingletonSupplier<?> supp) {
      return inject("value", instance, supp);
   }

   private static boolean inject(String fieldname, Object value, SingletonSupplier<?> supp) {
      final Field f;
      try {
         f = supp.getClass().getDeclaredField(fieldname);
      } catch (NoSuchFieldException e) {
         Assert.fail("Fieldname in SingletonSupplier changed");
         return false;
      }
      f.setAccessible(true);
      try {
         f.set(supp, value);
      } catch (IllegalAccessException e) {
         Assert.fail("could not inject value");
         return false;
      }
      return true;
   }

}
