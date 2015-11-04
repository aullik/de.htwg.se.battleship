package de.htwg.se.battleship.model;

import de.htwg.se.battleship.model.impl.ModelFabricImpl;

/**
 * Factory for Model elements
 *
 * @author niwehrle
 */
public class ModelFactory {


   // bind(ModelFabric.class).to(de.htwg.se.battleship.model.impl.ModelFabricImpl.class);

   public static ModelFabric createModelFabric() {
      return ModelFabricImpl.getInstance();
   }
}
