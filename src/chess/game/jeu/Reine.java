package chess.game.jeu;

import java.awt.Point;


public class Reine extends Piece {

  public Reine (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.reine;
      
  }
  
  public boolean testDeplacement( Point p )
  {
      boolean estDeplacable = true;
      
      if (this.point.x - p.x == 0 && this.point.y - p.y == 0)
          estDeplacable = false;
      if (this.point.x != p.x && this.point.y != p.y && Math.abs(this.point.x - p.x) != Math.abs(this.point.y - p.y))
          estDeplacable = false;
      
      return estDeplacable;
  }


}
