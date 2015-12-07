package de.htwg.se.battleship.model.old;

/**
 * Factory for Model elements
 *
 * @author niwehrle
 */
@Deprecated
public abstract class OLDModelFactory {

   protected static OLDModelFactory instance;

   private static OLDModelFactory getInstance() {
      if (instance == null)
         instance = new DefaultImpl();

      return instance;
   }

   // bind(ModelFabric.class).to(de.htwg.se.battleship.model.old.ModelFabricImpl.class);
   protected abstract ModelFabric _createModelFabric();

   public static ModelFabric createModelFabric() {
      return getInstance()._createModelFabric();
   }


   public static class DefaultImpl extends OLDModelFactory {

      @Override
      protected ModelFabric _createModelFabric() {
         return ModelFabricImpl.getInstance();
      }
   }

}
