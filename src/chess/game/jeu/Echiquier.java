package chess.game.jeu;



/**
 * Class Echiquier
 */
public class Echiquier {

  //
  // Fields
  //

  private Jeu j;
  private Joueur joueurCourant;
  private Case tabCases;
  
  //
  // Constructors
  //
  public Echiquier () { };
  
  //
  // Methods
  //


  //
  // Accessor methods
  //

  /**
   * Set the value of j
   * @param newVar the new value of j
   */
  private void setJ ( Jeu newVar ) {
    j = newVar;
  }

  /**
   * Get the value of j
   * @return the value of j
   */
  private Jeu getJ ( ) {
    return j;
  }

  /**
   * Set the value of joueurCourant
   * @param newVar the new value of joueurCourant
   */
  private void setJoueurCourant ( Joueur newVar ) {
    joueurCourant = newVar;
  }

  /**
   * Get the value of joueurCourant
   * @return the value of joueurCourant
   */
  private Joueur getJoueurCourant ( ) {
    return joueurCourant;
  }

  /**
   * Set the value of tabCases
   * @param newVar the new value of tabCases
   */
  private void setTabCases ( Case newVar ) {
    tabCases = newVar;
  }

  /**
   * Get the value of tabCases
   * @return the value of tabCases
   */
  private Case getTabCases ( ) {
    return tabCases;
  }

  //
  // Other methods
  //

  /**
   */
  public void Echiquier(  )
  {
  }


  /**
   */
  public void initCases(  )
  {
  }


  /**
   * @return       Piece
   * @param        c
   */
  public Piece getPieceCase( Case c )
  {
  }


  /**
   * @param        p
   * @param        c
   */
  public void deplacerPiece( Piece p, Case c )
  {
  }


  /**
   */
  public void changerJoueur(  )
  {
  }


}
