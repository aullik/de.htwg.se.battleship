/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.se.battleship.controller.impl;

import de.htwg.se.battleship.controller.IntController;
import de.htwg.se.battleship.util.observer.Observable;

/**
 * 
 * @author aullik
 */
public class Controller extends Observable implements IntController {

    // private IntController realController;

    public void test() {
        notifyObservers(null);
    }

}
