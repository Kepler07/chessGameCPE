package chess.game.jeu;

import java.awt.Point;


public class Roi extends Piece {

  public Roi (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.roi;
      
  }

  public boolean testDeplacement( Point p )
  {
      if (Math.abs(this.point.getX() - p.getX()) == 1 || Math.abs(this.point.getY() - p.getY()) == 1)
          return true;
      else
          return false;
  }


}
