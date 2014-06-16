package chess.game.jeu;

import java.awt.Point;


public class Echiquier {


    private Jeu j1;
    private Jeu j2;
    private Jeu jeuCourant;
    private Piece[][] tabPieces;

    public Echiquier() {
        this.j1 = new Jeu(Couleur.blanc);
        this.j2 = new Jeu(Couleur.noir);
        
        this.tabPieces = new Piece[8][8];

        this.jeuCourant = this.j1;

        this.initTabPieces();
    }

  

  public Jeu getJoueurCourant() {
        return this.jeuCourant;
    }

    public Piece[][] getTabPieces() {
        return this.tabPieces;
    }

    public void initTabPieces() {
        
        for( Piece p : this.j1.getTabPiece() )
        {
            this.tabPieces[p.point.x][p.point.y] = p;
        }
        
        for( Piece p : this.j2.getTabPiece() )
        {
            this.tabPieces[p.point.x][p.point.y] = p;
        }
    }

    public void deplacerPiece(Piece p, Point point) {
    }

    public Piece getPieceCase(Point p){
        return this.tabPieces[p.x][p.y];
    }
    
    public void changerJoueur() {
        if (this.jeuCourant == j1) {
            this.jeuCourant = j2;
        } else {
            this.jeuCourant = j1;
        }
    }
}
