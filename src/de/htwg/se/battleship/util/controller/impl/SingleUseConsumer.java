package de.htwg.se.battleship.util.controller.impl;

import java.util.function.Consumer;

/**
 * A single use Consumer that will only be executed once. If it has already been executed it will throw a {@link de
 * .htwg.se.battleship.util.controller.impl.AlreadyExecutedException}. This must be executed outside of GameThread or
 * {@link de.htwg.se.battleship.util.controller.impl.NotUIThreadException} will be thrown.
 * <p>
 * Use {@link #accept(Object)}
 *
 * @author aullik on 28.12.2015.
 */
public class SingleUseConsumer<T> {

   volatile boolean executed;
   Consumer<T> cons;
   private final GamePlatform platform;

   public SingleUseConsumer(final Consumer<T> cons, GamePlatform platform) {
      this.cons = cons;
      this.platform = platform;
      this.executed = false;
   }

   /**
    * @return whether this has already be executed or not.
    */
   public boolean isExecuted() {
      return executed;
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
      checkThread();

      checkExecuted();
      platform.runLater(() -> cons.accept(t));
   }

   private void checkThread() throws NotUIThreadException {
      if (platform.isGameThread()) {
         throw new NotUIThreadException();
      }
   }

   private synchronized void checkExecuted() throws AlreadyExecutedException {
      if (executed)
         throw new AlreadyExecutedException();


      executed = true;
   }

}
