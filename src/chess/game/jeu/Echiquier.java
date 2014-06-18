package chess.game.jeu;

import chess.game.controller.VuePiece;
import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Echiquier implements Serializable {

    private Jeu j1;
    private Jeu j2;
    private Jeu jeuCourant;
    private Piece[][] tabPieces;

    public Echiquier() {
        this.j1 = new Jeu(Couleur.blanc);
        this.j2 = new Jeu(Couleur.noir);

        this.tabPieces = new Piece[8][8];

        this.jeuCourant = this.j1;

        this.initTabPieces();
    }

    public Jeu getJoueurCourant() {
        return this.jeuCourant;
    }

    public Piece[][] getTabPieces() {
        return this.tabPieces;
    }

    public void initTabPieces() {

        for (Piece p : this.j1.getTabPiece()) {
            this.tabPieces[p.point.x][p.point.y] = p;
        }

        for (Piece p : this.j2.getTabPiece()) {
            this.tabPieces[p.point.x][p.point.y] = p;
        }
    }

    public boolean deplacerPiece(Piece p, Point point) {

        boolean succes = false;

        if (Echiquier_deplacement_Utils.estAutorise(p, this, point)) {
            if (this.getPieceCase(point) != null) {
                if (this.jeuCourant == this.j1) {
                    this.j2.mangerPieceCase(point);
                } else {
                    this.j1.mangerPieceCase(point);
                }
            }
            if (isRoque(p, point)) {
                this.roque(p, point);
            } else {
                this.tabPieces[p.point.x][p.point.y] = null;
                p.setPoint(point);
                this.tabPieces[p.point.x][p.point.y] = p;
                p.unsetPremierDeplacement();
            }


            succes = true;
            if (p.getType() == Piece_type.pion) {
                ((Pion) p).unsetPremierDeplacement();
            }
            this.changerJoueur();
        }
        return succes;
    }

    public Piece getPieceCase(Point p) {
        return this.tabPieces[p.x][p.y];
    }

    public void changerJoueur() {
        if (this.jeuCourant == j1) {
            this.jeuCourant = j2;
        } else {
            this.jeuCourant = j1;
        }
    }

    public VuePiece[][] getVue() {

        VuePiece[][] tabVues = new VuePiece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.tabPieces[i][j] != null && this.tabPieces[i][j].estVivant()) {
                    tabVues[i][j] = new VuePiece(this.tabPieces[i][j].point, this.tabPieces[i][j].type, this.tabPieces[i][j].couleur);
                }
            }
        }

        return tabVues;
    }

    public List<Point> getTabDeplacementsAutorises(Point p) {
        List<Point> tab = new ArrayList<Point>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Echiquier_deplacement_Utils.estAutorise(this.getPieceCase(p), this, new Point(i, j))) {
                    tab.add(new Point(i, j));
                }
            }
        }
        return tab;

    }

    public void deplacementPetitRoque(Piece p)
    {
            int px = p.getPoint().x;
            Piece tour = getPieceCase(new Point(px + 3, p.getPoint().y));
            p.setPoint(new Point(px + 2, p.getPoint().y));
            this.tabPieces[px + 2][p.getPoint().y] = p;
            tour.setPoint(new Point(px + 1, p.getPoint().y));
            this.tabPieces[px + 1][p.getPoint().y] = tour;
            this.tabPieces[px][p.getPoint().y] = null;
            this.tabPieces[px + 3][p.getPoint().y] = null;
            p.unsetPremierDeplacement();
            tour.unsetPremierDeplacement();
        
    }
        public void deplacementGrandRoque(Piece p)
    {
            int px = p.getPoint().x;
            Piece tour = getPieceCase(new Point(px - 4, p.getPoint().y));
            p.setPoint(new Point(px - 2, p.getPoint().y));
            this.tabPieces[px - 2][p.getPoint().y] = p;
            tour.setPoint(new Point(px - 1, p.getPoint().y));
            this.tabPieces[px - 1][p.getPoint().y] = tour;
            this.tabPieces[px][p.getPoint().y] = null;
            this.tabPieces[px - 4][p.getPoint().y] = null;
            p.unsetPremierDeplacement();
            tour.unsetPremierDeplacement();
        
    }
    public void roque(Piece p, Point point) {

        if (point.equals(new Point(6, 0)) || point.equals(new Point(6, 7))) {
            deplacementPetitRoque(p);
        }
        if (point.equals(new Point(2, 0)) || point.equals(new Point(2, 7))) {
            deplacementGrandRoque(p);
        }

    }

    public boolean isRoque(Piece p, Point point) {
        boolean succes = false;
        if (p.getType() == Piece_type.roi && p.isPremierDeplacement()) {
            if (p.getCouleur() == Couleur.blanc) {
                if (point.equals(new Point(6, 0))) {
                    if (getPieceCase(new Point(7, 0)).getType() == Piece_type.tour && getPieceCase(new Point(7, 0)).isPremierDeplacement()) {
                        if (getPieceCase(new Point(6, 0)) == null && getPieceCase(new Point(5, 0)) == null) {
                            succes = true;
                        }
                    }
                }
                if (point.equals(new Point(2, 0))) {
                    if (getPieceCase(new Point(0, 0)).getType() == Piece_type.tour && getPieceCase(new Point(0, 0)).isPremierDeplacement()) {
                        if (getPieceCase(new Point(1, 0)) == null && getPieceCase(new Point(2, 0)) == null && getPieceCase(new Point(3, 0)) == null) {
                            succes = true;
                        }
                    }
                }
            }
            if (p.getCouleur() == Couleur.noir) {
                if (point.equals(new Point(6, 7))) {
                    if (getPieceCase(new Point(7, 7)).getType() == Piece_type.tour && getPieceCase(new Point(7, 7)).isPremierDeplacement()) {
                        if (getPieceCase(new Point(6, 7)) == null && getPieceCase(new Point(5, 7)) == null) {
                            succes = true;
                        }
                    }
                }
                if (point.equals(new Point(2, 7))) {
                    if (getPieceCase(new Point(0, 7)).getType() == Piece_type.tour && getPieceCase(new Point(0, 7)).isPremierDeplacement()) {
                        if (getPieceCase(new Point(1, 7)) == null && getPieceCase(new Point(2, 7)) == null && getPieceCase(new Point(3, 7)) == null) {
                            succes = true;
                        }
                    }
                }
            }
        }
        return succes;
    }
}
