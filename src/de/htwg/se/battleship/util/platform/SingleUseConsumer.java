package de.htwg.se.battleship.util.platform;

import java.util.function.Consumer;

/**
 * A single use Consumer that will only be executed once. If it has already been executed it will throw a {@link de
 * .htwg.se.battleship.util.controller.impl.AlreadyExecutedException}. This must be executed outside of GameThread or
 * {@link NotUIThreadException} will be thrown.
 * <p>
 * Use {@link #accept(Object)}
 *
 * @author aullik on 28.12.2015.
 */
public class SingleUseConsumer<T> extends AbstractSingleUse {

   Consumer<T> cons;

   /**
    * creates a SingleUseConsumer, Thread will be checked
    *
    * @param cons     the consumer that will be executed
    * @param platform the platform the runnable will be executed on
    */
   public SingleUseConsumer(final Consumer<T> cons, ThreadPlatform platform) {
      super(platform);
      this.cons = cons;
   }


   /**
    * Performs this operation on the given argument.
    *
    * @param t argument
    * @throws AlreadyExecutedException if this has already be executed
    * @throws NotUIThreadException     if executed from inside GameThread
    * @see java.util.function.Consumer
    */
   public void accept(final T t) throws AlreadyExecutedException, NotUIThreadException {
      runChecked(() -> cons.accept(t));
   }

}
