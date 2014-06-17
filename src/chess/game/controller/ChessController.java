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
import java.util.ArrayList;
import java.util.List;
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


        this.fenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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

        if (this.echiquier.getPieceCase(((ChessFrame) this.fenetre).getCoord(me.getPoint())).getCouleur() == this.echiquier.getJoueurCourant().getCouleurJeu()) {
            pInit = ((ChessFrame) this.fenetre).getCoord(me.getPoint());
            deplacementsAutorises(pInit);

            ((ChessFrame) this.fenetre).setChessPiece(null);
            Component c = ((ChessFrame) this.fenetre).getChessBoard().findComponentAt(me.getX(), me.getY());

            if (!(c instanceof JPanel)) {

                Point parentLocation = c.getParent().getLocation();
                ((ChessFrame) this.fenetre).setxAdjustment(parentLocation.x - me.getX());
                ((ChessFrame) this.fenetre).setyAdjustment(parentLocation.y - me.getY());
                ((ChessFrame) this.fenetre).setChessPiece((JLabel) c);
                ((ChessFrame) this.fenetre).getChessPiece().setLocation(me.getX() + ((ChessFrame) this.fenetre).getxAdjustment(), me.getY() + ((ChessFrame) this.fenetre).getyAdjustment());
                ((ChessFrame) this.fenetre).getChessPiece().setSize(((ChessFrame) this.fenetre).getChessPiece().getWidth(), ((ChessFrame) this.fenetre).getChessPiece().getHeight());
                ((ChessFrame) this.fenetre).getLayeredPane().add(((ChessFrame) this.fenetre).getChessPiece(), JLayeredPane.DRAG_LAYER);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        Piece p = echiquier.getPieceCase(this.pInit);
        if (p != null) {
            this.echiquier.deplacerPiece(p, ((ChessFrame) this.fenetre).getCoord(me.getPoint()));
           
        }

        if (((ChessFrame) this.fenetre).getChessPiece() != null) {


            ((ChessFrame) this.fenetre).getChessPiece().setVisible(false);
            Component c = ((ChessFrame) this.fenetre).getChessBoard().findComponentAt(me.getX(), me.getY());

            if (c instanceof JLabel) {
                Container parent = c.getParent();
                parent.remove(0);
                parent.add(((ChessFrame) this.fenetre).getChessPiece());
            } else {
                Container parent = (Container) c;
                parent.add(((ChessFrame) this.fenetre).getChessPiece());
            }
        }
        
        ((ChessFrame) this.fenetre).afficherEchiquier(this.echiquier.getVue());
        ((ChessFrame) this.fenetre).restoreBackGround();
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }
    
    public void deplacementsAutorises(Point p)
    {
        List<Point> tab = new ArrayList<Point>();
        tab = this.echiquier.getTabDeplacementsAutorises(p);
        ((ChessFrame)this.fenetre).setDeplacementAutorises(tab);
    }
}
