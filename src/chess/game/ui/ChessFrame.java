package chess.game.ui;

import chess.game.controller.EventManager;
import chess.game.controller.VuePiece;
import chess.game.jeu.Couleur;
import chess.game.jeu.Piece;
import chess.game.jeu.Piece_type;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

public class ChessFrame extends JFrame {

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int sizex = 800;
    private int sizey = 800;

    public ChessFrame(EventManager e) {
        
        super();
        Dimension boardSize = new Dimension(sizex, sizey);

        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(e);
        layeredPane.addMouseMotionListener(e);

        //Add a chess board to the Layered Pane 

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

    }

    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JPanel) {
            return;
        }

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    //Move the chess piece around
    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    //Drop the chess piece back onto the chess board
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(chessPiece);
        } else {
            Container parent = (Container) c;
            parent.add(chessPiece);
        }

        chessPiece.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
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

                    }
                    else {

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
                    JPanel panel = (JPanel) chessBoard.getComponent(56+i-j*8);
                    panel.add(piece);

                }
            }
        }
        this.setVisible(true);
        this.repaint();

    }
}
