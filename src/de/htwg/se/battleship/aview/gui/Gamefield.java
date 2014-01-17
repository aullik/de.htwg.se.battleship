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

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Gamefield extends JPanel implements MouseListener,
        MouseMotionListener {

    private Graphics2D    grid;
    private BufferedImage backup;
    private BufferedImage background;
    private Dimension     size;
    private Integer       side;
    private Integer       sqrtCells;
    private Integer       cellsize;
    private Integer       fromBorder;
    private Integer       toBorder;
    private MainFrame     parent;
    private boolean       selectable;
    private int[]         isSelected;
    private boolean       placeShip;

    public Gamefield(int sidelength, int sqrtCells, MainFrame parent) {
        super();

        this.side = round(sidelength, sqrtCells);
        this.sqrtCells = sqrtCells;
        this.cellsize = side / (sqrtCells + 1);
        this.parent = parent;
        this.selectable = false;
        this.placeShip = false;
        size = new Dimension(side, side);
        this.setPreferredSize(size);
        grid = initiateGrid();
        this.addMouseListener(this);

        this.setOpaque(false);

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

    public void selectable(boolean b) {
        this.selectable = b;
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
        grid.setColor(Color.GRAY);
        grid.setStroke(new BasicStroke(2));
        int[] cords1 = getCords(p1);
        int[] cords2 = getCords(p2);

        grid.fillOval(cords1[0] - cellsize / 3, cords1[1] - cellsize / 3,
                cords2[0] - cellsize / 3, cords2[1] - cellsize / 3);
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
        int selectsize = cellsize / 3 * 2;
        grid.drawArc(cords[0] - selectsize / 2, cords[1] - selectsize / 2,
                selectsize, selectsize, 0, 360);
        isSelected = idx;

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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!selectable) {
            return;
        }
        int[] idx = getidx(e.getPoint());

        if (idx != null) {
            grid = initiateGrid();

            isSelected = null;
            select(idx);

            parent.repaint();

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
}
