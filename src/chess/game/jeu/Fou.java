package chess.game.jeu;

import java.awt.Point;


public class Fou extends Piece {

  public Fou (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.fou;
      
  }

  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() - p.getX() == 0 && this.point.getY() - p.getY() == 0)
          return false;
      if(Math.abs(this.point.getX() - p.getX()) == Math.abs(this.point.getY() - p.getY()))
          return true;
      else 
          return false;
  }
}

