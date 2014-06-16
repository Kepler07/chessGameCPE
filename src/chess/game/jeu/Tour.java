package chess.game.jeu;

import java.awt.Point;

/**
 * Class Tour
 */
public class Tour extends Piece {

    public Tour(Point p, Couleur c) {

        super(p, c);
        this.type = Piece_type.tour;

    }

    public boolean testDeplacement(Point p) {
        boolean estDeplacable = true;

        if (this.point.x - p.x == 0 && this.point.y - p.y == 0) {
            estDeplacable = false;
        }

        if (this.point.y == p.y) {
            estDeplacable = false;
        }

        return estDeplacable;
    }
}
