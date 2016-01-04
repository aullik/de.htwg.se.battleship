package de.htwg.se.battleship.util.controller.impl;

import java.util.function.Function;

/**
 * BaseClass for Controller
 * uses a Gamethread to make sure there ain't no concurrency problems
 * Created by aullik on 29.11.2015.
 */
public abstract class AbstractThreadSave {

   protected final GamePlatform platform;

   public AbstractThreadSave(final GamePlatform platform) {
      this.platform = platform;
   }


   public void close() {
      this.platform.close();
   }


   public <T extends AbstractThreadSave> T createThreadSaveController(Function<GamePlatform, T> supp) {
      return supp.apply(platform);
   }


   protected final void runPlatform() {
      if (!platform.isGameThread())
         throw new IllegalStateException("run must be called from the same Thread that created this instance");
      this.platform.run();
   }

}
