package chess.game.jeu;

import java.awt.Point;

public class Cavalier extends Piece {

    public Cavalier(Point p, Couleur c) {

        super(p, c);
        this.type = Piece_type.cavalier;

    }

    public boolean testDeplacement(Point p) {
        boolean estDeplacable = true;

        if (this.point.x - p.x == 0 && this.point.y - p.y == 0) {
            estDeplacable = false;
        }

        if (Math.abs(this.point.x - p.x) == 1) {
            if (Math.abs(this.point.y - p.y) != 2) {
                estDeplacable = false;
            }

        } else if (Math.abs(this.point.x - p.x) == 2) {
            if (Math.abs(this.point.y - p.y) != 1) {
                estDeplacable = false;
            }
        } else {
            estDeplacable = false;
        }

        return estDeplacable;
    }
}
