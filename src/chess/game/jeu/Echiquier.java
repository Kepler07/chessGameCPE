package chess.game.jeu;

import chess.game.controller.VuePiece;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Echiquier {

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

    public void deplacerPiece(Piece p, Point point) {

        if (Echiquier_deplacement_Utils.estAutorise(p, this, point)) {
            if (this.getPieceCase(point) != null) {
                if (this.jeuCourant == this.j1) {
                    this.j2.mangerPieceCase(point);
                } else {
                    this.j1.mangerPieceCase(point);
                }
            }
            this.tabPieces[p.point.x][p.point.y] = null;
            p.setPoint(point);
            this.tabPieces[p.point.x][p.point.y] = p;
            if (p.getType() == Piece_type.pion) {
                ((Pion) p).unsetPremierDeplacement();
            }
            this.changerJoueur();
        }

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
}
