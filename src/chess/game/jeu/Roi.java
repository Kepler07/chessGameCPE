package chess.game.jeu;

import java.awt.Point;

public class Roi extends Piece {

    public Roi(Point p, Couleur c) {

        super(p, c);
        this.type = Piece_type.roi;

    }

    public boolean testDeplacement(Point p) {
        
        boolean estDeplacable = true;

        if (this.point.x - p.x == 0 && this.point.y - p.y == 0) {
            estDeplacable = false;
        }
        if (Math.abs(this.point.x - p.x) >= 2 || Math.abs(this.point.y - p.y) >= 2) {
            estDeplacable = false;
        }

        return estDeplacable;
    }
}
