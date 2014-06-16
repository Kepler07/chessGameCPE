package chess.game.jeu;

import java.awt.Point;


public class Roi extends Piece {

  public Roi (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.roi;
      
  }

  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() - p.getX() == 0 && this.point.getY() - p.getY() == 0)
          return false;
      if (Math.abs(this.point.getX() - p.getX()) < 2 && Math.abs(this.point.getY() - p.getY()) < 2)
          return true;
      else
          return false;
  }


}
