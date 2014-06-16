package chess.game.jeu;

import java.awt.Point;


public class Cavalier extends Piece {

  public Cavalier (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.cavalier;
      
  }

  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() - p.getX() == 0 && this.point.getY() - p.getY() == 0)
          return false;
      
      if (Math.abs(this.point.getX() - p.getX()) == 1)
      {
          if(Math.abs(this.point.getY() - p.getY()) == 2)
            return true;
          else 
            return false;
          
      }
      else if (Math.abs(this.point.getX() - p.getX()) == 2)
      {
          if(Math.abs(this.point.getY() - p.getY()) == 1)
            return true;
          else 
            return false;
      }
      else
          return false;
  }


}
