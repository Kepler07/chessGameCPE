package chess.game.jeu;

import java.awt.Point;


public class Cavalier extends Piece {

  public Cavalier (Point p, Couleur c) { 
  
      super(p,c);
      this.type = Piece_type.cavalier;
      
  }

  public boolean testDeplacement( Point p )
  {
      return true;
  }


}
