package chess.game.jeu;

import java.awt.Point;



/**
 * Class Case
 */
public class Case {

  private Point coordonnees;
  

  public Case (Point p) { 
      this.coordonnees = p;
  };

  private Point getCoordonnees ( ) {
    return coordonnees;
  }
  

}
