package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.controller.gamemode.GamemodeControllable;
import de.htwg.se.battleship.controller.ingame.IngameController;
import de.htwg.se.battleship.controller.initgame.InitGameController;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * @author aullik on 18.01.2016.
 */
public class GameTUI implements GamemodeControllable {

   private final static String WINNER_MSG = "Congratulations '%s', you won the game!";


   private final ThreadPlatform platform;
   private final TUIView tuiView;
   private final BooleanProperty initFinished;

   private RPlayer player;


   public GameTUI(final ThreadPlatform platform, final TUIView tuiView) {
      this.platform = platform;
      this.tuiView = tuiView;
      this.initFinished = new SimpleBooleanProperty(false);
   }


   @Override
   public void setInitGameController(final InitGameController cont) {
      final InitGameTUI initGameTUI = new InitGameTUI(platform, tuiView);
      cont.registerControllable(initGameTUI);
      initFinished.addListener(obs -> {
         if (initFinished.get())
            initGameTUI.finish();
      });
   }

   @Override
   public void setInGameController(final IngameController cont) {
      final InGameTUI inGameTUI = new InGameTUI(tuiView);
      cont.registerControllable(inGameTUI);
      initFinished.set(true);
   }

   @Override
   public void abortGame() {
      initFinished.set(true);

   }

   @Override
   public void setWinner(RPlayer player) {
      tuiView.printFormat(WINNER_MSG, player.getName());
      initFinished.set(true);
   }
}
