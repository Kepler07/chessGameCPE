package chess.game.jeu;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {

    private boolean premierDeplacement = true;

    public Pion(Point p, Couleur c) {

        super(p, c);
        this.type = Piece_type.pion;

    }

    public boolean testDeplacement(Point p) {
        boolean estDeplacable = true;

        if (this.point.x != p.x) {
            estDeplacable = false;
        }

        if (this.couleur == Couleur.blanc) {
            if (this.point.y > p.y)
                  {
                estDeplacable = false;
            }
        } else {
            if (this.point.y < p.y)
                  {
                estDeplacable = false;
            }
        }

        if (this.premierDeplacement) {

            if (Math.abs((this.point.y - p.y)) > 2) {
                estDeplacable = false;
            }
        } else {
            if (Math.abs((this.point.y - p.y)) != 1) {
                estDeplacable = false;
            }
        }
        
        if (this.couleur == Couleur.noir)
        {
            if (this.point.y - p.y == -1 && Math.abs(this.point.x - p.x) == 1)
                estDeplacable = true;
        }
        else {
            if (this.point.y - p.y == 1 && Math.abs(this.point.x - p.x) == 1)
                estDeplacable = true;
        }
        

        return estDeplacable;

    }    
}
