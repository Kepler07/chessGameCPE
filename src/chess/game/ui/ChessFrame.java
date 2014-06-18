package chess.game.ui;

import chess.game.controller.ChessController;
import chess.game.controller.VuePiece;
import chess.game.jeu.Couleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChessFrame extends JFrame implements MouseMotionListener {

    private JLayeredPane layeredPane;
    private JPanel chessBoard;
    private JLabel chessPiece;
    private JLabel displayJoueur;
    private JLabel displayTimer;
    private JLabel displayTimerTour;
    private JButton boutonReset;
    private JMenuBar menuBar;
    private int xAdjustment;
    private int yAdjustment;
    private int sizex = 800;
    private int sizey = 800;
    private int timerSeconde = 0;
    private int timerTour = 0;
    private javax.swing.Timer t;
    private MouseListener controller;

    public ChessFrame(MouseListener c) {

        super();
        this.controller = c;
        Dimension boardSize = new Dimension(sizex, sizey);


        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(150, 800));
        displayJoueur = new JLabel("Aux blancs de jouer");
        displayTimer = new JLabel("<html>Temps de jeu : <br>00 : 00 : 00</html>");
        displayTimerTour = new JLabel("<html>Temps du tour : <br>00 : 00</html>");
        boutonReset = new JButton();
        boutonReset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                ((ChessController) controller).resetPlateau();
            }
        });

        this.menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem save = new JMenuItem("Sauvegarder");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Chess Saves", "chs");
                chooser.setFileFilter(filter);
                chooser.setApproveButtonText("Enregistrer");
                int returnVal = chooser.showOpenDialog(chooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (chooser.getSelectedFile().toString().contains(".chs")) {
                        ((ChessController) controller).save(chooser.getSelectedFile().toString(), timerTour, timerSeconde);
                    } else {
                        ((ChessController) controller).save(chooser.getSelectedFile() + ".chs", timerTour, timerSeconde); //get File selected by user
                    }
                }
            }
        });
        JMenuItem load = new JMenuItem("Charger");
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {

                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Chess Saves", "chs");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(chooser);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    ((ChessController) controller).load(chooser.getSelectedFile());
                }
            }
        });
        JMenuItem exit = new JMenuItem("Quitter");
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        file.add(save);
        file.add(load);
        file.add(exit);
        this.menuBar.add(file);
        setJMenuBar(this.menuBar);


        boutonReset.setText("Reset");
        sideBar.add(displayJoueur);
        sideBar.add(displayTimer);
        sideBar.add(displayTimerTour);
        sideBar.add(boutonReset);
        //  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(layeredPane);
        getContentPane().add(sideBar);
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

        this.t = new javax.swing.Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                timerSeconde++;
                timerTour++;
                affichageTimers();
            }
        });

        this.t.setInitialDelay(1000);
        this.t.start();

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

        coord.x = p.x / (sizex / 8) ;
        coord.y = (sizey - p.y) / (sizey / 8);

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

    public void changeDisplayJoueur() {
        if (this.displayJoueur.getText() == "Aux blancs de jouer") {
            this.displayJoueur.setText("Aux noirs de jouer");
        } else {
            this.displayJoueur.setText("Aux blancs de jouer");
        }
        this.timerTour = 0;
        this.displayTimerTour.setText("<html>Temps du tour : <br>00 : 00</html>");
    }

    public void affichageTimers() {

        int nbMinutes = timerSeconde / 60;
        int nbSecondes = timerSeconde % 60;
        int nbHeures = nbMinutes / 60;

        String displayMinutes, displaySecondes, displayHeures;

        nbMinutes = nbMinutes % 60;

        if (nbMinutes < 10) {
            displayMinutes = "0" + nbMinutes;
        } else {
            displayMinutes = String.valueOf(nbMinutes);
        }
        if (nbHeures < 10) {
            displayHeures = "0" + nbHeures;
        } else {
            displayHeures = String.valueOf(nbHeures);
        }
        if (nbSecondes < 10) {
            displaySecondes = "0" + nbSecondes;
        } else {
            displaySecondes = String.valueOf(nbSecondes);
        }

        int nbMinutesTour = timerTour / 60;
        int nbSecondesTour = timerTour % 60;

        String displayMinutesTour, displaySecondesTour;

        nbMinutesTour = nbMinutesTour % 60;

        if (nbMinutesTour < 10) {
            displayMinutesTour = "0" + nbMinutesTour;
        } else {
            displayMinutesTour = String.valueOf(nbMinutesTour);
        }
        if (nbSecondesTour < 10) {
            displaySecondesTour = "0" + nbSecondesTour;
        } else {
            displaySecondesTour = String.valueOf(nbSecondesTour);
        }

        displayTimer.setText("<html>Temps de jeu : " + "<br>     " + displayHeures + " : " + displayMinutes + " : " + displaySecondes + "</html>");
        displayTimerTour.setText("<html>Temps du tour : " + "<br>     " + displayMinutesTour + " : " + displaySecondesTour + "</html>");

    }

    public void resetAffichage() {
        this.timerSeconde = 0;
        this.timerTour = 0;
        this.displayJoueur.setText("Aux blancs de jouer");
        this.displayTimer.setText("<html>Temps de jeu : <br>00 : 00 : 00</html>");
        this.displayTimerTour.setText("<html>Temps du tour : <br>00 : 00</html>");
        this.t.restart();
    }

    public void loadPartie(int timerSeconde, int timerTour, Couleur couleur) {
        this.timerSeconde = timerSeconde;
        this.timerTour = timerTour;
        this.affichageTimers();
        this.t.restart();

        if (couleur == Couleur.blanc) {
            this.displayJoueur.setText("Aux blancs de jouer");
        } else {
            this.displayJoueur.setText("Aux noirs de jouer");
        }
    }

    public void moveIcon(Point point) {

        this.chessPiece = null;
        Component c = this.chessBoard.findComponentAt(point.x, point.y);

        if (!(c instanceof JPanel)) {

            Point parentLocation = c.getParent().getLocation();
            this.setxAdjustment(parentLocation.x - point.x);
            this.setyAdjustment(parentLocation.y - point.y);
            this.chessPiece = (JLabel) c;
            this.chessPiece.setLocation(point.x + this.getxAdjustment(), point.y + this.getyAdjustment());
            this.chessPiece.setSize(this.chessPiece.getWidth(), this.chessPiece.getHeight());
            this.layeredPane.add(this.chessPiece, JLayeredPane.DRAG_LAYER);
        }
    }

    public void dropIcon(Point point) {

        this.chessPiece.setVisible(false);
        Component c = this.chessBoard.findComponentAt(point.x, point.y);

        if (c instanceof JLabel) {
            Container parent = c.getParent();
            parent.remove(0);
            parent.add(this.chessPiece);
        } else if (c == null)  {          
        } else {
            Container parent = (Container) c;
            parent.add(this.chessPiece);
        }
    }
}
