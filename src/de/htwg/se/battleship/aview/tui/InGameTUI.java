package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.gridpPainter.DoubleGridPainter;
import de.htwg.se.battleship.aview.tui.gridpPainter.GridPainter;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.controller.ingame.IngameControllable;
import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.REnemyCell;
import de.htwg.se.battleship.model.read.REnemyGrid;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.platform.AlreadyExecutedException;
import de.htwg.se.battleship.util.platform.NotUIThreadException;
import de.htwg.se.battleship.util.platform.SingleUseConsumer;

/**
 * @author aullik on 18.01.2016.
 */
public class InGameTUI implements IngameControllable {

   private final static String SHOOT_CELL = "Your Grid Player %s: \t\t\t\tEnemy grid: \n%s\n\n Please select a Cell" +
         " to shoot. Expected Input has the format 'x,y' where x and y are coordinates. For example '3,5'.\n Shoot " +
         "cell:";

   private final TUIView tuiView;
   private RPlayer player;
   private GridPainter gridPainter;


   public InGameTUI(final TUIView tuiView) {
      this.tuiView = tuiView;
   }


   @Override
   public void initIngameControllable(final RPlayer player, final REnemyGrid grid) {
      this.player = player;
      this.gridPainter = new DoubleGridPainter(player.getGrid(), grid);
   }

   @Override
   public void shoot(final SingleUseConsumer<REnemyCell> cons) {
      validatePlayerSet();
      tuiView.setActionOnlyJob((inp) -> validateInput(inp, cons),
            String.format(SHOOT_CELL, player.getName(), gridPainter.paintGrid()), cons::isExecuted);
   }

   private void validatePlayerSet() {
      if (gridPainter == null)
         throw new IllegalStateException("initIngameControllable() has not been called!");
   }


   private boolean validateInput(String inp, final SingleUseConsumer<REnemyCell> cons) {
      if (!inp.matches("\\d+,\\d+$"))
         return false;

      final String[] split = inp.split(",");
      final int x = Integer.parseInt(split[0]);
      final int y = Integer.parseInt(split[1]);

      final RCell cell = player.getGrid().getCell(x, y);
      if (cell == null)
         return false;

      try {
         cons.accept(cell);
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return true;
      }

      return true;
   }

}
