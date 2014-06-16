package chess.game.jeu;

import java.awt.Point;


public class Pion extends Piece {
    
    private boolean premierDeplacement = true;

  public Pion (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.pion;
      
  }

  public boolean testDeplacement( Point p )
  {
      if (this.point.getX() != p.getX())
          return false;
      
      if (this.point.getX() - p.getX() == 0 && this.point.getY() - p.getY() == 0)
          return false;
      
      if(this.premierDeplacement){
                    
          if(Math.abs((this.point.getY() - p.getY())) <= 2)
              return true;
          else
              return false;
      }
      else {
          if(Math.abs((this.point.getY() - p.getY())) == 1 )
              return true;
          else
              return false;
      }
      
  }


}
