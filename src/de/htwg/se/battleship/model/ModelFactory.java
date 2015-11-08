package de.htwg.se.battleship.model;

import de.htwg.se.battleship.model.impl.ModelFabricImpl;

/**
 * Factory for Model elements
 *
 * @author niwehrle
 */
public abstract class ModelFactory {

   protected static ModelFactory instance;

   private static ModelFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();

      return instance;
   }

   // bind(ModelFabric.class).to(de.htwg.se.battleship.model.impl.ModelFabricImpl.class);
   protected abstract ModelFabric _createModelFabric();

   public static ModelFabric createModelFabric() {
      return getInstance()._createModelFabric();
   }


   public static class DefaultImpl extends ModelFactory {

      @Override
      protected ModelFabric _createModelFabric() {
         return ModelFabricImpl.getInstance();
      }
   }

}
