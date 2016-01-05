package de.htwg.se.battleship.util.platform;

/**
 * @author aullik on 05.01.2016.
 */
public class AbstractSingleUse {

   protected final ThreadPlatform platform;
   private final boolean checkThread;
   volatile boolean executed;

   /**
    * @param platform    The GamePlatform
    * @param checkThread whether This can be executed from inside GameThread or not
    */
   public AbstractSingleUse(ThreadPlatform platform, final boolean checkThread) {
      this.platform = platform;
      this.checkThread = checkThread;
   }

   /**
    * same as {@code AbstractSingleUse}(platform, true)
    *
    * @param platform The GamePlatform
    */
   public AbstractSingleUse(final ThreadPlatform platform) {
      this(platform, true);
   }


   /**
    * @return whether this has already be executed or not.
    */
   public boolean isExecuted() {
      return executed;
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

   protected void runChecked(Runnable runnable) throws NotUIThreadException, AlreadyExecutedException {
      if (checkThread)
         checkThread();
      checkExecuted();

      platform.runLater(runnable);
   }
}
