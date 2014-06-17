/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.controller;

import chess.game.jeu.Couleur;
import chess.game.jeu.Piece_type;
import java.awt.Point;

/**
 *
 * @author bastien.gounon
 */
public class VuePiece {
    
    public Point point;
    public Piece_type type;
    public Couleur couleur;

    public VuePiece(Point point, Piece_type type, Couleur couleur) {
        this.point = point;
        this.type = type;
        this.couleur = couleur;
    }
    
    
    
}
