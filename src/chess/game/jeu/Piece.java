package chess.game.jeu;

import java.awt.Point;



/**
 * Class Piece
 */
abstract public class Piece {

  protected Point point;
  protected Couleur couleur;
  protected Piece_type type;
  protected boolean vivant;
  

  public Piece (Point p, Couleur c) { 
      
      this.point = p;
      this.couleur = c;
      this.vivant = true;
  
  };
  
  public void setPoint(Point p) {
      this.point = p;
  }
 
  
  public Point getPoint ( ) {
    return point;
  }

  public Couleur getCouleur ( ) {
    return couleur;
  }


  public Piece_type getType ( ) {
    return type;
  }

  public void setVivant ( boolean b ) {
    this.vivant = b;
  }


  public boolean estVivant ( ) {
    return vivant;
  }

  public boolean testCase( Point p )
  {
      return true;
  }

  public String toString() {
      
      return "Position = " + this.point.toString() + ", type = " + this.type.toString() + ", couleur = " + this.couleur.toString() + ", vivant = " + this.vivant + "\n";
  }

  abstract public boolean testDeplacement( Point p );


}
