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
      this.initJeu();
      
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
          
          this.tabPiece.add(new Tour(new Point(0,7), this.couleurJeu));
          this.tabPiece.add(new Tour(new Point(7,7), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(1,7), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(6,7), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(2,7), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(5,7), this.couleurJeu));
          this.tabPiece.add(new Reine(new Point(3,7), this.couleurJeu));
          this.tabPiece.add(new Roi(new Point(4,7), this.couleurJeu));
          
          for (int i=0; i<8; i++) {
              this.tabPiece.add(new Pion(new Point(i,6), this.couleurJeu));
          }
         
      }
      
      else {
          
          this.tabPiece.add(new Tour(new Point(0,0), this.couleurJeu));
          this.tabPiece.add(new Tour(new Point(7,0), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(1,0), this.couleurJeu));
          this.tabPiece.add(new Cavalier(new Point(6,0), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(2,0), this.couleurJeu));
          this.tabPiece.add(new Fou(new Point(5,0), this.couleurJeu));
          this.tabPiece.add(new Reine(new Point(3,0), this.couleurJeu));
          this.tabPiece.add(new Roi(new Point(4,0), this.couleurJeu));
          
          for (int i=0; i<8; i++) {
              this.tabPiece.add(new Pion(new Point(i,1), this.couleurJeu));
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
          if (p.getPoint().equals(point))
              return p;
      }
      return null;
  }


  public void mangerPieceCase( Point point )
  {
      for(Piece p : this.tabPiece)
      {
          if (p.getPoint().equals(point))
              p.setVivant(false);
      }
      
  }
  
    @Override
  public String toString() {
      
        return tabPiece.toString();
                
  }


}
