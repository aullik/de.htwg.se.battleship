package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.impl.RoundImpl;

public class SetShotTest {

    @Test
    public void test() {
        Round round = new RoundImpl(null, null);
        SetShot setShot = new SetShot(round);
        assertEquals(round, setShot.getRound());
    }
}