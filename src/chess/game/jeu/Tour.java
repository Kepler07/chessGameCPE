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
      if (this.point.getY() != p.getY())
          return false;
      else
          return true;
  }


}
