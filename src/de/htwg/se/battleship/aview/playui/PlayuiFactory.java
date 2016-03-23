package de.htwg.se.battleship.aview.playui;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Factory for PlayUI Elements
 *
 * @author niwehrle
 */
public abstract class PlayuiFactory {

   protected static PlayuiFactory instance;

   private static PlayuiFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();
      return instance;
   }


   protected abstract Void _createPlayUIMainClass();

   public static Void createPlayUIMainClass() {
      return getInstance()._createPlayUIMainClass();
   }


   public static class DefaultImpl extends PlayuiFactory {


      @Override
      protected Void _createPlayUIMainClass() {
         // TODO KBR
         throw new NotImplementedException();
      }
   }

}


