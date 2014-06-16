package chess.game.jeu;

import java.awt.Point;



/**
 * Class Tour
 */
public class Tour extends Piece {

  public Tour (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.tour;
      
  }
  
  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() - p.getX() == 0 && this.point.getY() - p.getY() == 0)
          return false;
      if (this.point.getX() != p.getX())
          return false;
      else
          return true;
  }


}
