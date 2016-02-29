package de.htwg.se.battleship.aview.tui;

import de.htwg.se.battleship.aview.tui.gridpPainter.MyGridPainter;
import de.htwg.se.battleship.aview.tui.view.TUIView;
import de.htwg.se.battleship.controller.initgame.InitGameControllable;
import de.htwg.se.battleship.model.impl.ShipSize2;
import de.htwg.se.battleship.model.impl.ShipSize3;
import de.htwg.se.battleship.model.impl.ShipSize4;
import de.htwg.se.battleship.model.impl.ShipSize5;
import de.htwg.se.battleship.model.read.RCell;
import de.htwg.se.battleship.model.read.RGrid;
import de.htwg.se.battleship.model.read.RPlayer;
import de.htwg.se.battleship.util.platform.AlreadyExecutedException;
import de.htwg.se.battleship.util.platform.NotUIThreadException;
import de.htwg.se.battleship.util.platform.SingleUseConsumer;
import de.htwg.se.battleship.util.platform.ThreadPlatform;
import javafx.beans.InvalidationListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntSupplier;

/**
 * @author aullik on 18.01.2016.
 */
public class InitGameTUI implements InitGameControllable {

   private final static String SET_PLAYER = "Please state your name:";
   private final static String DECIDE_SHIP_SIZE = "Player %s :\nPossible ship sizes: %s \nPlease Choose one.";
   private final static String SET_SHIP = "\n%s\nPlayer %s setting ship of size %s:\n " +
         "Expected input has the format 'x,yD' where x and y are coordinates and D is a direction like 'U'p, 'D'own, " +
         "'L'eft, 'R'ight. For example '3,5R'.\nShip position:";


   private final ThreadPlatform platform;
   private final TUIView tuiView;
   private final ArrayList<ListNode> list;
   private final AtomicInteger decideShipSizeCounter;
   private InvalidationListener gridListener;
   private RPlayer player;
   private MyGridPainter gridPainter;


   public InitGameTUI(final ThreadPlatform platform, final TUIView tuiView) {
      this.platform = platform;
      this.tuiView = tuiView;
      this.list = new ArrayList<>();
      decideShipSizeCounter = new AtomicInteger();
   }


   private static class ListNode {

      private final int size;
      private final String stringSize;
      private final Runnable run;
      private final BooleanSupplier executed;

      private ListNode(int size, Runnable run, BooleanSupplier executed) {
         this.size = size;
         this.stringSize = Integer.toString(size);
         this.run = run;
         this.executed = executed;
      }

      private String size() {
         return stringSize;
      }
   }


   @Override
   public void setPlayer(final RPlayer player) {
      platform.runOnPlatform(() -> _setPlayer(player));
   }

   private void _setPlayer(final RPlayer player) {

      if (this.player != null)
         return;

      this.player = player;
      this.gridPainter = new MyGridPainter(player.getGrid());
      tuiView.print("Welcome: " + player.getName());
      gridListener = grid -> tuiView.print(gridPainter.paintGrid());
      player.getGrid().addListener(gridListener);
   }

   @Override
   public void setPlayerName(final SingleUseConsumer<String> nameSetter) {
      tuiView.setActionOnlyJob(name -> checkAndSetName(nameSetter, name), SET_PLAYER, () -> this.player != null);
   }

   private boolean checkAndSetName(final SingleUseConsumer<String> nameSetter, String name) {
      if (nameSetter.isExecuted())
         return true;
      try {
         nameSetter.accept(name);
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return false;
      }
      return true;
   }

   private void iterateShipJobs(Consumer<ListNode> cons) {
      iterateShipJobsTillTrue(node -> {
         cons.accept(node);
         return false;
      });

   }

   private ListNode iterateShipJobsTillTrue(Function<ListNode, Boolean> func) {
      final Iterator<ListNode> iter = list.iterator();
      while (iter.hasNext()) {
         final ListNode node = iter.next();
         if (node.executed.getAsBoolean())
            iter.remove();
         else if (func.apply(node))
            return node;
      }
      return null;
   }

   private void addShipSetterToList(int size, Consumer<List<RCell>> shipSetter, SingleUseConsumer check) {
      platform.runOnPlatform(() -> _addShipSetterToList(size, shipSetter, check));
   }

   private void _addShipSetterToList(int size, Consumer<List<RCell>> shipSetter, SingleUseConsumer check) {
      final BooleanSupplier isExecuted = check::isExecuted;

      list.add(new ListNode(size, () -> setShipJob(size, shipSetter, isExecuted), isExecuted));
      if (list.size() == 1)
         runDecideShipSize();
   }

   private void runDecideShipSize() {
      platform.runOnPlatform(this::_runDecideShipSize);
   }

   private void _runDecideShipSize() {
      try {
         //sleeping as there will be multiple ship setters added and it looks better to print them all at once
         Thread.sleep(50);
      } catch (InterruptedException e) {
         //ignore
      }
      platform.runLater(this::decideShipSize);
   }

   private void decideShipSize() {
      list.sort((n1, n2) -> Integer.compare(n1.size, n2.size));
      StringJoiner sizes = new StringJoiner(", ");
      iterateShipJobs(n -> sizes.add(n.size()));

      final int current = decideShipSizeCounter.incrementAndGet();

      //do after iterate as iterate cleans list
      if (list.size() == 0)
         return;

      tuiView.setActionOnlyJob(this::checkShipSize, String.format(DECIDE_SHIP_SIZE, player.getName(),
            sizes.toString()), () -> current < decideShipSizeCounter.get());
   }

   private boolean checkShipSize(final String size) {
      final ListNode node = iterateShipJobsTillTrue(n -> n.size().equals(size));

      if (node == null)
         runDecideShipSize();
      else
         platform.runLater(node.run);

      return true;
   }

   private void setShipJob(int size, Consumer<List<RCell>> setter, final BooleanSupplier jobExecuted) {
      tuiView.setActionOnlyJob(inp -> checkShipFormat(size, inp, list -> executeShipSetter(setter, list)),
            String.format(SET_SHIP, gridPainter.paintGrid(), player.getName(), Integer.toString(size)), jobExecuted);
   }

   private void executeShipSetter(Consumer<List<RCell>> cons, List<RCell> list) {
      platform.runOnPlatform(() -> _executeShipSetter(cons, list));
   }

   private void _executeShipSetter(Consumer<List<RCell>> cons, List<RCell> list) {

      cons.accept(list);
      runDecideShipSize();
   }

   private boolean checkShipFormat(final int should, final String inp, Consumer<List<RCell>> setter) {
      if (!inp.matches("\\d+,\\d+[UDLR]$"))
         return false;

      //all following is check by regex
      int last = inp.length();
      int sub = last - 1;
      final char direction = inp.substring(sub, last).toCharArray()[0];
      final String[] split = inp.substring(0, sub).split(",");
      final AtomicInteger x = new AtomicInteger(Integer.parseInt(split[0]));
      final AtomicInteger y = new AtomicInteger(Integer.parseInt(split[1]));
      //all above is checked by regex;
      final int gridSize = player.getGrid().getSize();

      if (x.get() >= gridSize || y.get() >= gridSize)
         return false;

      final List<RCell> cellList = createCellList(should, direction, x, y);
      if (cellList == null)
         return false;


      setter.accept(cellList);
      return true;
   }

   private List<RCell> createCellList(int size, char direction, AtomicInteger x, AtomicInteger y) {
      final IntSupplier xSupp;
      final IntSupplier ySupp;
      // default: cannot happen, checked by regex
      switch (direction) {
         case 'U':
            y.incrementAndGet();
            xSupp = x::get;
            ySupp = y::decrementAndGet;
            break;
         case 'D':
            y.decrementAndGet();
            xSupp = x::get;
            ySupp = y::incrementAndGet;
            break;
         case 'L':
            x.incrementAndGet();
            xSupp = x::decrementAndGet;
            ySupp = y::get;
            break;
         case 'R':
            x.decrementAndGet();
            xSupp = x::incrementAndGet;
            ySupp = y::get;
            break;
         default:
            return null;
      }
      return createCellList(size, xSupp, ySupp);
   }

   private List<RCell> createCellList(int size, final IntSupplier xSupp, final IntSupplier ySupp) {
      final List<RCell> ret = new ArrayList<>(size);
      final RGrid grid = player.getGrid();
      for (int i = 0; i < size; i++) {
         final RCell cell = grid.getCell(xSupp.getAsInt(), ySupp.getAsInt());
         if (cell == null)
            return null;

         ret.add(cell);
      }
      return ret;
   }

   @Override

   public void set5SizeShip(final SingleUseConsumer<ShipSize5> shipSetter) {
      addShipSetterToList(5, (cells) -> set5SizeShipJob(cells, shipSetter), shipSetter);
   }

   private void set5SizeShipJob(List<RCell> cells, final SingleUseConsumer<ShipSize5> shipSetter) {
      try {
         shipSetter.accept(new ShipSize5(cells));
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return;
      }
   }

   @Override
   public void set4SizeShip(final SingleUseConsumer<ShipSize4> shipSetter) {
      addShipSetterToList(4, (cells) -> set4SizeShipJob(cells, shipSetter), shipSetter);

   }

   private void set4SizeShipJob(List<RCell> cells, final SingleUseConsumer<ShipSize4> shipSetter) {
      try {
         shipSetter.accept(new ShipSize4(cells));
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return;
      }
   }

   @Override
   public void set3SizeShip(final SingleUseConsumer<ShipSize3> shipSetter) {
      addShipSetterToList(3, (cells) -> set3SizeShipJob(cells, shipSetter), shipSetter);

   }

   private void set3SizeShipJob(List<RCell> cells, final SingleUseConsumer<ShipSize3> shipSetter) {
      try {
         shipSetter.accept(new ShipSize3(cells));
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return;
      }
   }

   @Override
   public void set2SizeShip(final SingleUseConsumer<ShipSize2> shipSetter) {
      addShipSetterToList(2, (cells) -> set2SizeShipJob(cells, shipSetter), shipSetter);

   }

   private void set2SizeShipJob(List<RCell> cells, final SingleUseConsumer<ShipSize2> shipSetter) {
      try {
         shipSetter.accept(new ShipSize2(cells));
      } catch (AlreadyExecutedException | NotUIThreadException e) {
         return;
      }
   }

   public void finish() {
      player.getGrid().removeListener(gridListener);
      list.clear();
   }
}
