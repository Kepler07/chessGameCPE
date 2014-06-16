
package chess.game.controller;

import chess.game.jeu.Couleur;
import chess.game.jeu.Jeu;
import java.awt.Point;


public class ChessGame {

    public static void main(String[] args) {

        Jeu j1 = new Jeu(Couleur.blanc);
        
        System.out.println(j1);
        
        System.out.println("Test Pion");
        System.out.println(j1.getPieceCase(new Point(0,1)).testDeplacement(new Point(0,2)));
        System.out.println(j1.getPieceCase(new Point(0,1)).testDeplacement(new Point(0,3)));
        System.out.println(j1.getPieceCase(new Point(0,1)).testDeplacement(new Point(0,4)));
        
        System.out.println("Test Cavalier");
        System.out.println(j1.getPieceCase(new Point(1,0)).testDeplacement(new Point(0,2)));
        System.out.println(j1.getPieceCase(new Point(1,0)).testDeplacement(new Point(2,2)));
        System.out.println(j1.getPieceCase(new Point(1,0)).testDeplacement(new Point(3,1)));
        System.out.println(j1.getPieceCase(new Point(1,0)).testDeplacement(new Point(1,2)));
        
        System.out.println("Test Tour");
        System.out.println(j1.getPieceCase(new Point(0,0)).testDeplacement(new Point(0,2)));
        System.out.println(j1.getPieceCase(new Point(0,0)).testDeplacement(new Point(0,6)));
        System.out.println(j1.getPieceCase(new Point(0,0)).testDeplacement(new Point(1,0)));
        System.out.println(j1.getPieceCase(new Point(0,0)).testDeplacement(new Point(1,1)));
        
        System.out.println("Test Fou");
        System.out.println(j1.getPieceCase(new Point(2,0)).testDeplacement(new Point(4,2)));
        System.out.println(j1.getPieceCase(new Point(2,0)).testDeplacement(new Point(0,2)));
        System.out.println(j1.getPieceCase(new Point(2,0)).testDeplacement(new Point(2,1)));
        
        System.out.println("Test Reine");
        System.out.println(j1.getPieceCase(new Point(3,0)).testDeplacement(new Point(5,2)));
        System.out.println(j1.getPieceCase(new Point(3,0)).testDeplacement(new Point(3,2)));
        System.out.println(j1.getPieceCase(new Point(3,0)).testDeplacement(new Point(4,2)));
        
        System.out.println("Test Roi");
        System.out.println(j1.getPieceCase(new Point(4,0)).testDeplacement(new Point(5,1)));
        System.out.println(j1.getPieceCase(new Point(4,0)).testDeplacement(new Point(4,1)));
        System.out.println(j1.getPieceCase(new Point(4,0)).testDeplacement(new Point(6,1)));
        
        System.out.println("nbVivants = " + j1.nbPiecesValides());
        j1.mangerPieceCase(new Point(1,0));
        System.out.println("nbVivants = " + j1.nbPiecesValides());
        
  }
    
}
