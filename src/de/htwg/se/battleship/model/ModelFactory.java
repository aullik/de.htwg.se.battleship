package de.htwg.se.battleship.model;

import de.htwg.se.battleship.model.impl.ModelFabricImpl;

/**
 * Factory for Model elements
 *
 * @author niwehrle
 */
public class ModelFactory {

   private static ModelFabricImpl instance_ModelFabricImpl;

   /**
    * @return singleton instance of ModelFabricImpl
    */
   public static ModelFabricImpl getModelFabricImpl() {
      if (instance_ModelFabricImpl == null)
         instance_ModelFabricImpl = new ModelFabricImpl();

      return instance_ModelFabricImpl;
   }


   // bind(ModelFabric.class).to(de.htwg.se.battleship.model.impl.ModelFabricImpl.class);

   public static ModelFabric createModelFabric() {
      return getModelFabricImpl();
   }
}
