package de.htwg.se.battleship.util.controller.impl;

import de.htwg.se.battleship.util.platform.ThreadPlatform;

import java.util.function.Function;

/**
 * BaseClass for Controller
 * uses a Gamethread to make sure there ain't no concurrency problems
 * Created by aullik on 29.11.2015.
 */
public abstract class AbstractThreadSave {

   protected final ThreadPlatform platform;

   public AbstractThreadSave(final ThreadPlatform platform) {
      this.platform = platform;
   }


   public <T extends AbstractThreadSave> T createThreadSaveController(Function<ThreadPlatform, T> supp) {
      return supp.apply(platform);
   }


   protected final void runPlatform() {
      if (!platform.isPlatformThread())
         throw new IllegalStateException("run must be called from the same Thread that created this instance");
      this.platform.run();
   }

}
