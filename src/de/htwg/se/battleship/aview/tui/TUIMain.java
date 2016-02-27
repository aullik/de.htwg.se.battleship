package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.command.impl.NewSharedScreenGame;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.controller.ControllerFactory;
import de.htwg.se.battleship.controller.GameStateControllable;
import de.htwg.se.battleship.controller.GameStateController;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import de.htwg.se.battleship.util.singleton.SingletonSupplier;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

/**
 * @author aullik on 18.01.2016.
 */
public class TUIMain implements GameStateControllable {

   private final static String NAME = "TUI";

   private final static SingletonSupplier<TUIMain> INSTANCE = new SingletonSupplier<>(TUIMain::new);

   public static TUIMain getInstance() {
      return INSTANCE.get();
   }

   private final GameStateController gsController;
   private final ThreadPlatform platform;
   private final TUIView tuiView;

   private GamemodeControllable currentGamemodeControllable;


   private TUIMain() {
      this.gsController = ControllerFactory.getController();
      gsController.registerControllable(this);
      platform = new ThreadPlatform();
      tuiView = new TUIView(platform);
      tuiView.addCommand(new NewSharedScreenGame(gsController));
      platform.run();
   }

   @Override
   public void startNewSharedScreenGame(final Consumer<GamemodeControllable> consumer) {
      if (currentGamemodeControllable != null)
         throw new IllegalStateException("Game Already Started");

      this.currentGamemodeControllable = new GameTUI(platform, tuiView);
      consumer.accept(currentGamemodeControllable);

   }

   @Override
   public void startNewGame(final Consumer<GamemodeControllable> consumer) {
      //FIXME
      throw new NotImplementedException();
   }

   @Override
   public String getName() {
      return NAME;
   }

}
