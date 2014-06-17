package chess.game.ui;

import chess.game.controller.ChessController;
import chess.game.controller.VuePiece;
import chess.game.jeu.Couleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class ChessFrame extends JFrame implements MouseMotionListener {

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int sizex = 800;
    private int sizey = 800;

    public ChessFrame(ChessController c) {

        super();
        Dimension boardSize = new Dimension(sizex, sizey);

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(c);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane 

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            square.setBorder(new javax.swing.border.BevelBorder(BevelBorder.RAISED));
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

    }

    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void afficherEchiquier(VuePiece[][] tabPieces) {

        ImageIcon image = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (tabPieces[i][j] != null) {

                    if (tabPieces[i][j].couleur == Couleur.noir) {

                        switch (tabPieces[i][j].type) {
                            case cavalier:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/cavalierNoirS.png"));
                                break;
                            case fou:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/fouNoirS.png"));
                                break;
                            case pion:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/pionNoirS.png"));
                                break;
                            case reine:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/reineNoireS.png"));
                                break;
                            case roi:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/roiNoirS.png"));
                                break;
                            case tour:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/tourNoireS.png"));
                                break;
                        }

                    } else {

                        switch (tabPieces[i][j].type) {
                            case cavalier:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/cavalierBlancS.png"));
                                break;
                            case fou:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/fouBlancS.png"));
                                break;
                            case pion:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/pionBlancS.png"));
                                break;
                            case reine:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/reineBlancS.png"));
                                break;
                            case roi:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/roiBlancS.png"));
                                break;
                            case tour:
                                image = new ImageIcon(this.getClass().getResource("/chess/game/ui/resources/tourBlancS.png"));
                                break;
                        }

                    }
                    JLabel piece = new JLabel(image);
                    JPanel panel = (JPanel) chessBoard.getComponent(56 + i - j * 8);
                    panel.removeAll();
                    panel.add(piece);

                } else {
                    ((JPanel) chessBoard.getComponent(56 + i - j * 8)).removeAll();
                }
            }
        }
        this.setVisible(true);
        this.repaint();

    }

    public void setDeplacementAutorises(List<Point> tab) {
        for (Point p : tab) {
            JPanel panel = (JPanel) chessBoard.getComponent(56 + p.x - p.y * 8);
            panel.setBackground(Color.green);      
        }
        this.setVisible(true);
        this.repaint();
    }

    public Point getCoord(Point p) {

        Point coord = new Point();

        coord.x = p.x / 100;
        coord.y = (800 - p.y) / 100;

        return coord;

    }

    public JLabel getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(JLabel chessPiece) {
        this.chessPiece = chessPiece;
    }

    public JPanel getChessBoard() {
        return chessBoard;
    }

    public int getxAdjustment() {
        return xAdjustment;
    }

    public void setxAdjustment(int xAdjustment) {
        this.xAdjustment = xAdjustment;
    }

    public int getyAdjustment() {
        return yAdjustment;
    }

    public void setyAdjustment(int yAdjustment) {
        this.yAdjustment = yAdjustment;
    }

    public JLayeredPane getLayeredPane() {
        return this.layeredPane;
    }

    public void setLayeredPane(JLayeredPane l) {
        this.layeredPane = l;
    }

    public void restoreBackGround() {

        for (int i = 0; i < 64; i++) {
            JPanel panel = (JPanel) chessBoard.getComponent(i);
            int row = (i / 8) % 2;
            if (row == 0) {
                panel.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                panel.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

    }
}
