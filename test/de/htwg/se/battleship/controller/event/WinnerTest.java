package de.htwg.se.battleship.controller.event;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.se.battleship.model.Round;
import de.htwg.se.battleship.model.impl.RoundImpl;

public class WinnerTest {

    @Test
    public void test() {
        Round round = new RoundImpl(null, null);
        Winner winner = new Winner(round);
        assertEquals(round, winner.getRound());
    }
}