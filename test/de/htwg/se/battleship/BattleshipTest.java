package de.htwg.se.battleship;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BattleshipTest {

    @Test
    public void test() {

        Battleship object1 = Battleship.getInstance();
        Battleship object2 = Battleship.getInstance();

        assertEquals(object1, object2);
        Battleship.main(null);

    }

}
