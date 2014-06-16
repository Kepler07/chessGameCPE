package chess.game.jeu;

import java.awt.Point;


public class Reine extends Piece {

  public Reine (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.reine;
      
  }
  
  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() == p.getX() || this.point.getY() == p.getY() || Math.abs(this.point.getX() - p.getX()) == Math.abs(this.point.getY() - p.getY()))
          return true;
      else
          return false;
  }


}
