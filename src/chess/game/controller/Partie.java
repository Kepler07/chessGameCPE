/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.controller;

import chess.game.jeu.Echiquier;
import java.io.Serializable;

public class Partie implements Serializable {

    private Echiquier e;
    private int timerSeconde, timerTour;

    public Partie(Echiquier e, int timerSeconde, int timerTour) {
        
        this.e = e;
        this.timerSeconde = timerSeconde;
        this.timerTour = timerTour;
       
    }

    public Echiquier getE() {
        return e;
    }

    public int getTimerSeconde() {
        return timerSeconde;
    }

    public int getTimerTour() {
        return timerTour;
    }
    
    
}
