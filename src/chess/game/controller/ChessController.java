/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.game.controller;

import chess.game.jeu.Echiquier;
import chess.game.jeu.Piece;
import chess.game.ui.ChessFrame;
import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ChessController implements MouseListener {

    private Echiquier echiquier;
    private JFrame fenetre;
    private Point pInit;

    public ChessController() {

        this.echiquier = new Echiquier();
        this.fenetre = new ChessFrame(this);

        this.pInit = new Point();


        this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.fenetre.pack();
        this.fenetre.setResizable(true);
        this.fenetre.setLocationRelativeTo(null);
        this.fenetre.setVisible(true);

        ((ChessFrame) this.fenetre).afficherEchiquier(this.echiquier.getVue());

    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {

        if (((ChessFrame) this.fenetre).getChessBoard().contains(me.getPoint())) {
            if (this.echiquier.getPieceCase(((ChessFrame) this.fenetre).getCoord(me.getPoint())).getCouleur() == this.echiquier.getJoueurCourant().getCouleurJeu()) {
                pInit = ((ChessFrame) this.fenetre).getCoord(me.getPoint());
                deplacementsAutorises(pInit);
                ((ChessFrame) this.fenetre).moveIcon(me.getPoint());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (((ChessFrame) this.fenetre).getChessBoard().contains(me.getPoint())) {
            Piece p = echiquier.getPieceCase(this.pInit);
            if (p != null) {
                if (this.echiquier.deplacerPiece(p, ((ChessFrame) this.fenetre).getCoord(me.getPoint()))) {
                    ((ChessFrame) this.fenetre).changeDisplayJoueur();
                }

            }

            if (((ChessFrame) this.fenetre).getChessPiece() != null) {

                  ((ChessFrame)this.fenetre).dropIcon(me.getPoint());
            }

            ((ChessFrame) this.fenetre).afficherEchiquier(this.echiquier.getVue());
            ((ChessFrame) this.fenetre).restoreBackGround();

        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void deplacementsAutorises(Point p) {
        List<Point> tab = new ArrayList<Point>();
        tab = this.echiquier.getTabDeplacementsAutorises(p);
        ((ChessFrame) this.fenetre).setDeplacementAutorises(tab);
    }

    public void resetPlateau() {
        this.echiquier = new Echiquier();
        ((ChessFrame) this.fenetre).resetAffichage();
        ((ChessFrame) this.fenetre).afficherEchiquier(this.echiquier.getVue());
    }

    public void save(String s, int timerTour, int timerSeconde ) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(s));
            Partie p = new Partie(this.echiquier, timerSeconde, timerTour);
            oos.writeObject(p);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void load(File selectedFile) {
        try{

            ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(selectedFile)) ;
            Partie p  = (Partie)ois.readObject() ;
            this.echiquier = p.getE();
            ((ChessFrame)this.fenetre).afficherEchiquier(this.echiquier.getVue());
            ((ChessFrame)this.fenetre).loadPartie(p.getTimerSeconde(), p.getTimerTour(), p.getE().getJoueurCourant().getCouleurJeu());
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
