package chess.game.jeu;

import java.awt.Point;



/**
 * Class Case
 */
public class Case {

  //
  // Fields
  //

  private Point coordonnees;
  
  //
  // Constructors
  //
  public Case () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of coordonnees
   * @param newVar the new value of coordonnees
   */
  private void setCoordonnees ( Point newVar ) {
    coordonnees = newVar;
  }

  /**
   * Get the value of coordonnees
   * @return the value of coordonnees
   */
  private Point getCoordonnees ( ) {
    return coordonnees;
  }

  //
  // Other methods
  //

}
