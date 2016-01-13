package de.htwg.se.battleship.util.platform;

/**
 * A single use Runnable that will only be executed once. If it has already been executed it will throw a {@link de
 * .htwg.se.battleship.util.controller.impl.AlreadyExecutedException}. This must be executed outside of GameThread or
 * {@link NotUIThreadException} will be thrown, unless the checkThread boolean flag is false.
 *
 * @author aullik on 05.01.2016.
 */
public class SingleUseRunnable extends AbstractSingleUse {

   Runnable run;

   /**
    * creates a SingleUseRunnable, Thread will be checked
    *
    * @param run      the runnable that will be executed
    * @param platform the platform the runnable will be executed on
    */
   public SingleUseRunnable(final Runnable run, ThreadPlatform platform) {
      this(run, platform, true);
   }

   /**
    * creates a SingleUseRunnable
    *
    * @param run         the runnable that will be executed
    * @param platform    the platform the runnable will be executed on
    * @param checkThread whether the thread will be checked or not
    */
   public SingleUseRunnable(final Runnable run, ThreadPlatform platform, boolean checkThread) {
      super(platform, checkThread);
      this.run = run;
   }

   /**
    * Performs this operation.
    *
    * @throws AlreadyExecutedException if this has already be executed
    * @throws NotUIThreadException     if executed from inside GameThread
    * @see java.util.function.Consumer
    */
   public void run() throws AlreadyExecutedException, NotUIThreadException {
      runChecked(run);
   }

}
