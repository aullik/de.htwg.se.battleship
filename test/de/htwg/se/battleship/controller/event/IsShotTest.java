package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.Cell;
import de.htwg.se.battleship.model.impl.CellImpl;

public class IsShotTest {

    @Test
    public void test() {
        Cell cell = new CellImpl(0, 0, null);
        IsShot isShot = new IsShot(null, cell);
        assertEquals(cell, isShot.getCell());
    }

}
