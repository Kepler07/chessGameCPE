package chess.game.jeu;

import java.awt.Point;

public class Echiquier_deplacement_Utils {

    public Echiquier_deplacement_Utils() {
    }

    ;
  
  public boolean estAutorise(Piece p, Echiquier e, Point point) {
        boolean estDeplacable = true;

        //test validité et appartenance au bon joueur
        if (!p.estVivant() || e.getJoueurCourant().getPieceCase(p.getPoint()) != p) {
            estDeplacable = false;
        }

        //test coordonnées valides
        if (point.x < 0 && point.x >= 8 || point.y < 0 || point.y >= 8) {
            estDeplacable = false;
        }

        //test déplacement non-nul
        if (p.point.x - point.x == 0 && p.point.y - point.y == 0) {
            estDeplacable = false;
        }

        //test déplacement propre à la pièce
        if (!p.testDeplacement(point)) {
            estDeplacable = false;
        }

        //test pièce déjà présente sur la case
        if (e.getPieceCase(point) != null) {
            if (e.getPieceCase(point).getCouleur() == p.getCouleur()) {
                estDeplacable = false;
            }
        }
        
        //test de collision (sauf pour cavalier)
        if (p.getType() != Piece_type.cavalier) {
            int dx, dy;
            dx = point.x - p.getPoint().x;
            dy = point.y - p.getPoint().y;

            if (dx > 0) {
                dx--;
            }
            if (dx < 0) {
                dx++;
            }

            if (dy > 0) {
                dy--;
            }
            if (dy < 0) {
                dy++;
            }

            while ((dx != 0 || dy != 0) && estDeplacable) {
                
                if (e.getPieceCase(new Point(p.getPoint().x - dx, p.getPoint().y - dy)) != null) {
                    estDeplacable = false;
                }

                if (dx > 0) {
                    dx--;
                }
                if (dx < 0) {
                    dx++;
                }

                if (dy > 0) {
                    dy--;
                }
                if (dy < 0) {
                    dy++;
                }
            }
        }

        return estDeplacable;
    }
}
