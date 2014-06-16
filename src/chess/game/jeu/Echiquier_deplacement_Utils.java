package chess.game.jeu;

import java.awt.Point;

public class Echiquier_deplacement_Utils {

  public Echiquier_deplacement_Utils () { };
  
  public boolean estAutorise( Piece p, Echiquier e, Point point )
  {
      boolean estDeplacable = true;
      if (!p.estVivant() || e.getJoueurCourant().getPieceCase(p.getPoint()) != p)
          estDeplacable = false;
      
      if (point.x < 0 && point.x >= 8 || point.y < 0 || point.y >= 8)
          estDeplacable = false;
      
      if (p.point.x - point.x == 0 && p.point.y - point.y == 0) 
            estDeplacable = false;
      
              
     return estDeplacable;
  }


}
