package de.htwg.se.battleship.aview.playui;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Factory for PlayUI Elements
 *
 * @author niwehrle
 */
public abstract class PlayUIFactory_GitBugFix {

   protected static PlayUIFactory_GitBugFix instance;

   private static PlayUIFactory_GitBugFix getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }


   protected abstract Void _createPlayUIMainClass();

   public static Void createPlayUIMainClass() {
      return getInstance()._createPlayUIMainClass();
   }


   public static class DefaultImpl extends PlayUIFactory_GitBugFix {


      @Override
      protected Void _createPlayUIMainClass() {
         // TODO KBR
         throw new NotImplementedException();
      }
   }

}


