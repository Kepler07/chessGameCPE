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

        if ((this.point.y == p.y || this.point.x != p.x) && (this.point.x == p.x || this.point.y != p.y)) {
            estDeplacable = false;
        }

        return estDeplacable;
    }
}
