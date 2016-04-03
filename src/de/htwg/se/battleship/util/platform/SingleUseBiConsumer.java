package de.htwg.se.battleship.util.platform;

import java.util.function.BiConsumer;

/**
 * A single use BiConsumer that will only be executed once. If it has already been executed it will throw a {@link de
 * .htwg.se.battleship.util.controller.impl.AlreadyExecutedException}. This must be executed outside of GameThread or
 * {@link de.htwg.se.battleship.util.platform.NotUIThreadException} will be thrown.
 * <p>
 * Use {@link #accept(Object, Object)}
 *
 * @author aullik on 28.12.2015.
 */
public class SingleUseBiConsumer<T, U> extends AbstractSingleUse {

   BiConsumer<T, U> cons;

   /**
    * creates a SingleUseBiConsumer, Thread will be checked
    *
    * @param cons     the BiConsumer that will be executed
    * @param platform the platform the runnable will be executed on
    */
   public SingleUseBiConsumer(final BiConsumer<T, U> cons, ThreadPlatform platform) {
      super(platform);
      this.cons = cons;
   }


   /**
    * Performs this operation on the given argument.
    *
    * @param t the first input argument
    * @param u the second input argument
    * @throws de.htwg.se.battleship.util.platform.AlreadyExecutedException if this has already be executed
    * @throws de.htwg.se.battleship.util.platform.NotUIThreadException     if executed from inside GameThread
    * @see java.util.function.BiConsumer
    */
   public void accept(final T t, U u) throws AlreadyExecutedException, NotUIThreadException {
      runChecked(() -> cons.accept(t, u));
   }

}
