package chess.game.jeu;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class Jeu {

  private List<Piece> tabPiece;
  private Couleur couleurJeu;
  
  public Jeu (Couleur c) { 
      
      tabPiece = new ArrayList<Piece>();
      couleurJeu = c;
      
  };

  private List<Piece> getTabPiece ( ) {
    return tabPiece;
  }
  
  private Couleur getCouleurJeu ( ) {
    return couleurJeu;
  }

  public void initJeu()
  {
      if (couleurJeu == Couleur.noir) {
          
          this.tabPiece.add(new Tour(new Point(0,0), this.couleurJeu));
          this.tabPiece.add(new Tour(new Point(0,7), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(0,1), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(0,6), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(0,2), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(0,5), this.couleurJeu));
          this.tabPiece.add(new Reine(new Point(0,4), this.couleurJeu));
          this.tabPiece.add(new Roi(new Point(0,5), this.couleurJeu));
          
          for (int i=0; i<8; i++) {
              this.tabPiece.add(new Pion(new Point(1,i), this.couleurJeu));
          }
         
      }
      
      else {
          
          this.tabPiece.add(new Tour(new Point(7,0), this.couleurJeu));
          this.tabPiece.add(new Tour(new Point(7,7), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(7,1), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(7,6), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(7,2), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(7,5), this.couleurJeu));
          this.tabPiece.add(new Reine(new Point(7,4), this.couleurJeu));
          this.tabPiece.add(new Roi(new Point(7,5), this.couleurJeu));
          
          for (int i=0; i<8; i++) {
              this.tabPiece.add(new Pion(new Point(6,i), this.couleurJeu));
          }
          
          
      }
      
      
  }

  public boolean testPieces(  )
  {
      boolean valide = true;
      for(Piece p : this.tabPiece)
      {
          if (p == null)
              valide = false;
      }
      return valide;
  }


  public int nbPiecesValides(  )
  {
      int nbValide = 0;
      for(Piece p : this.tabPiece)
      {
           if (p.estVivant())
               nbValide ++;
      }
      return nbValide;
  }


  public Piece getPieceCase( Point point )
  {
      for(Piece p : this.tabPiece)
      {
          if (p.getPoint() == point)
              return p;
      }
      return null;
  }


  public void mangerPieceCase( Point point )
  {
      for(Piece p : this.tabPiece)
      {
          if (p.getPoint() == point)
              p.setVivant(false);
      }
      
  }


}
