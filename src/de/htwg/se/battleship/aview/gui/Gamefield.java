package de.htwg.se.battleship.aview.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.se.battleship.controller.IInitGameController;
import de.htwg.se.battleship.controller.event.IsShot;
import de.htwg.se.battleship.controller.event.SetShip;
import de.htwg.se.battleship.controller.event.SetShipSuccess;
import de.htwg.se.battleship.controller.event.SetShot;
import de.htwg.se.battleship.controller.event.Winner;
import de.htwg.se.battleship.controller.event.WrongCoordinate;
import de.htwg.se.battleship.model.ICell;
import de.htwg.se.battleship.model.IPlayer;
import de.htwg.se.battleship.util.observer.Event;
import de.htwg.se.battleship.util.observer.IObserver;

/**
 * @author aullik
 */

@SuppressWarnings("serial")
public class Gamefield extends JPanel implements MouseListener,
MouseMotionListener, IObserver {

    private static final int FULL_ROTATION_ANGLE = 360;
    private static final int CELL_SCALING = 3;

    private IPlayer             player;
    private Graphics2D          grid;
    private BufferedImage       backup;
    private BufferedImage       background;
    private final Dimension           size;
    private final Integer             side;
    private final Integer             sqrtCells;
    private final Integer             cellsize;
    private Integer             fromBorder;
    private Integer             toBorder;
    private final MainFrame           parent;

    private int[]               isSelected;
    private int[]               shipStart;

    private final IInitGameController initC;
    private boolean             setShips;
    private boolean             mayshoot;
    private boolean             isHuman;

    public Gamefield(int sidelength, int sqrtCells, MainFrame parent,
            IInitGameController initC) {

        this.mayshoot = false;
        this.setShips = false;
        this.isHuman = false;
        this.side = round(sidelength, sqrtCells);
        this.sqrtCells = sqrtCells;
        this.cellsize = side / (sqrtCells + 1);
        this.parent = parent;
        init();

        size = new Dimension(side, side);
        this.setPreferredSize(size);
        grid = initiateGrid();
        this.addMouseListener(this);
        this.initC = initC;
        initC.addObserver(this);

        this.setOpaque(false);

    }

    private void init() {
        this.mayshoot = false;
        this.setShips = false;
        this.isHuman = false;
        this.backup = null;
        this.player = null;
        grid = initiateGrid();

    }

    private int round(int size, int parts) {
        int newSize = size;
        newSize /= (parts + 1);
        newSize *= (parts + 1);
        return newSize;
    }

    private Graphics2D initiateGrid() {
        background = new BufferedImage(side, side, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = background.createGraphics();

        if (backup == null) {
            g.setColor(Color.black);
            g.setStroke(new BasicStroke(2));
            for (int i = 0; i < sqrtCells + 1; i++) {
                fromBorder = cellsize / 2;
                toBorder = side - fromBorder;

                g.drawLine((fromBorder + i * cellsize), fromBorder,
                        (fromBorder + i * cellsize), toBorder - 1);
                g.drawLine(fromBorder, (fromBorder + i * cellsize),
                        toBorder - 1, (fromBorder + i * cellsize));
            }
            backup = background;
            return initiateGrid();
        }
        g.drawImage(backup, 0, 0, null);
        return g;
    }

    protected void setPlayer(IPlayer player) {
        if (this.player == null) {
            this.player = player;
            this.isHuman = player.isHuman();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    public void drawShip(int[] p1, int[] p2) {
        if (p1[0] != p2[0] && p1[1] != p2[1]) {
            return;
        }
        if (p1[0] > p2[0] || p1[1] > p2[1]) {
            drawShip(p2, p1);
            return;
        }
        Graphics2D g = backup.createGraphics();
        g.setColor(Color.GRAY);
        int[] cords1 = getCords(p1);
        int[] cords2 = getCords(p2);

        g.fillOval(cords1[0] - cellsize / CELL_SCALING, cords1[1] - cellsize / CELL_SCALING,
                cords2[0] - cords1[0] + cellsize / CELL_SCALING * 2, cords2[1] - cords1[1]
                        + cellsize / CELL_SCALING * 2);
        grid = initiateGrid();
    }

    public void select(int x, int y, Color color) {
        select(new int[] { x, y }, color);
    }

    public void select(int x, int y) {
        select(new int[] { x, y }, Color.BLUE);
    }

    public void select(int[] idx) {
        select(idx, Color.BLUE);
    }

    public void select(Point p) {
        select(new int[] { p.x, p.y }, Color.BLUE);
    }

    public void select(Point p, Color color) {
        select(new int[] { p.x, p.y }, color);
    }

    public void select(int[] idx, Color color) {
        int[] cords = getCords(idx);
        if (isSelected != null && isSelected[0] == idx[0]
                && isSelected[1] == idx[1]) {
            isSelected = null;
            return;
        }
        grid.setColor(color);
        grid.setStroke(new BasicStroke(2));
        int selectsize = cellsize / CELL_SCALING * 2;
        grid.drawArc(cords[0] - selectsize / 2, cords[1] - selectsize / 2,
                selectsize, selectsize, 0, FULL_ROTATION_ANGLE);
        isSelected = Arrays.copyOf(idx, idx.length);
    }

    public void miss(int[] idx) {
        int[] cords = getCords(idx);

        Graphics2D g = backup.createGraphics();
        g.setColor(Color.green);
        g.setStroke(new BasicStroke(2));
        int selectsize = cellsize / CELL_SCALING * 2;
        g.drawArc(cords[0] - selectsize / 2, cords[1] - selectsize / 2,
                selectsize, selectsize, 0, FULL_ROTATION_ANGLE);
        grid = initiateGrid();

    }

    public void hit(int[] idx) {
        int[] cords = getCords(idx);

        Graphics2D g = backup.createGraphics();
        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2));
        int selectsize = cellsize / CELL_SCALING;
        int x1 = cords[0] - selectsize;
        int x2 = cords[0] + selectsize;
        int y1 = cords[1] - selectsize;
        int y2 = cords[1] + selectsize;
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y1, x1, y2);
        grid = initiateGrid();
    }

    private int[] getCords(int[] idx) {
        int[] cords = new int[2];
        cords[0] = idx[0] * cellsize + fromBorder + cellsize / 2;
        cords[1] = idx[1] * cellsize + fromBorder + cellsize / 2;
        return cords;
    }

    private int[] getidx(Point p) {
        return getidx(p.x, p.y);
    }

    private int[] getidx(int x, int y) {
        int h = this.getSize().height;
        int w = this.getSize().width;
        int ch = h / (sqrtCells + 1);
        int cw = w / (sqrtCells + 1);
        int bh = ch / 2;
        int bw = cw / 2;
        int[] cords = new int[2];
        cords[0] = x - bw;
        cords[1] = y - bh;
        if (cords[0] < 0 || cords[1] < 0) {
            return null;
        }
        cords[0] /= cw;
        cords[1] /= ch;
        if (cords[0] > sqrtCells - 1 || cords[1] > sqrtCells - 1) {
            return null;
        }
        return cords;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), 0,
                0, side, side, null);

    }

    private void setShip(MouseEvent e) {
        int[] idx = getidx(e.getPoint());

        if (idx == null) {
            return;
        }
        if (shipStart == null) {
            grid = initiateGrid();
            isSelected = null;
            select(idx);
            shipStart = isSelected;
        }
        if (idx[0] != shipStart[0] || idx[1] != shipStart[1]) {
            isSelected = null;
            select(idx);

            setShips = false;
            initC.ship(shipStart[0], shipStart[1], idx[0], idx[1]);
            shipStart = null;
            isSelected = null;
        }
        parent.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (setShips) {
            setShip(e);
        }
        if (mayshoot) {

            int[] idx = getidx(e.getPoint());

            if (idx != null) {

                if (isSelected != null && idx[0] == isSelected[0]
                        && idx[1] == isSelected[1]) {
                    initC.shot(idx[0], idx[1]);
                    mayshoot = false;
                } else {
                    grid = initiateGrid();
                    select(idx);
                }

                parent.repaint();

            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public void update(SetShip e) {
        if (e.getPlayer().equals(player) && isHuman) {
            this.setShips = true;
        }

    }

    public void update(SetShipSuccess e) {
        if (e.getPlayer().equals(player) && isHuman) {
            List<ICell> list = e.getShip().getCells();
            ICell cell;
            int[] p1 = null;
            int[] p2 = null;
            int[] comp;
            while (!list.isEmpty()) {
                cell = list.remove(0);
                comp = new int[] { cell.getX(), cell.getY() };
                if (p2 == null) {
                    p2 = comp;
                } else if (comp[0] > p2[0] || comp[1] > p2[1]) {
                    p2 = comp;
                }
                if (p1 == null) {
                    p1 = comp;
                } else if (comp[0] < p1[0] || comp[1] < p1[1]) {
                    p1 = comp;
                }

            }

            drawShip(p1, p2);

        }

    }

    public void update(IsShot e) {
        if (e.getPlayer().equals(player)) {
            ICell cell = e.getCell();
            if (cell.isHit()) {
                hit(new int[] { cell.getX(), cell.getY() });
            } else {
                miss(new int[] { cell.getX(), cell.getY() });
            }
            parent.repaint();
        }
    }

    public void update(SetShot e) {
        if (e.getPlayer().equals(player) && isHuman) {
            this.mayshoot = true;
        }
    }

    public void update(WrongCoordinate e) {
        if (e.getPlayer().equals(player) && isHuman) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Wrong Coordinates", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(Winner e) {
        if (e.getRound() != null) {
            String winner = e.getPlayer().getName();
            JOptionPane.showMessageDialog(this, winner + " has Won!", winner,
                    JOptionPane.INFORMATION_MESSAGE);

        }
        init();
        parent.repaint();
    }

    @Override
    public void update(Event e) {

    }
}
