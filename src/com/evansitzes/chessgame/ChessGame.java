package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.Piece;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;


@SuppressWarnings("serial")
public class ChessGame extends JPanel implements ActionListener {

    private static final int ARRAYSIZE = 8;
//	 private final static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
//	 boolean playersTurn = true;
//	 ArrayList<Pair> listOfMoves = new ArrayList<Pair>(); // arraylist of legal moves
//	 Piece selectedPiece;
//	 int selectedPieceXPosition;
//	 int selectedPieceYPosition;
    private final ChessGameLogic game = new ChessGameLogic();
    private final ChessGameState state = new ChessGameState();
    private final GameBoard board = new GameBoard();
    private final JButton[][] buttons = new JButton[ARRAYSIZE][ARRAYSIZE];
    private final Image[][] chessPieceImages = new Image[2][6];
    private final JLabel turnStatus = new JLabel("White's move");
    private final BufferedImage img = null;
    private final JButton moveButton = new JButton("New Game");
    private final JButton exitButton = new JButton("Exit");
    private final JLabel gameStatusLabel = new JLabel("Chess Fun");

    public ChessGame(){
        final JToolBar tools = new JToolBar();
        final JPanel frame = new JPanel();

        state.board = new GameBoard();

        //sets row0
        frame.setLayout( new GridLayout(8,8));
        frame.setPreferredSize(new java.awt.Dimension(600,535));
        tools.setFloatable(false);
        
        createImages();
        createButtons(frame);
        initializebuttons();
        colorBoard();

        //adds all of the rows to the frame
        setLayout( new FlowLayout());
        add(tools, BorderLayout.PAGE_START);
        tools.add(new JButton("New")); // TODO add functionality!
        tools.add(new JButton("Save")); // TODO add functionality!
        tools.add(new JButton("Restore")); // TODO add functionality!
        tools.addSeparator();
        tools.add(new JButton("Resign")); // TODO add functionality!
        tools.addSeparator();
        tools.add(turnStatus);
        add(frame);

    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        final JComponent source = (JComponent) evt.getSource(); //finds the source of the objects that triggers the event
        final int rowPos = (Integer) source.getClientProperty("row");
        final int columnPos = (Integer) source.getClientProperty("column");

        game.playTheGame(state, rowPos, columnPos, source);

        if (state.isLegalMove) {
            if (state.isPieceSelected) {
                for (int i = 0; i < state.legalMoves.size(); i++) {
                    highlightLocation(state.selectedPiece.getXValue(), state.selectedPiece.getYValue(), state.legalMoves.get(i).getFirst(), state.legalMoves.get(i).getSecond());
                }
            }
        }
    }


    //method that highlights legal moves on the board
    public void highlightLocation(final int currentX, final int currentY, final int moveX, final int moveY) {
        final int totalY = Math.abs(7 - (currentY + moveY));
        final int totalX = currentX + moveX;

        try {
            buttons[totalY][totalX].setBackground(Color.RED);
            buttons[totalY][totalX].setContentAreaFilled(false);
            buttons[totalY][totalX].setOpaque(true);
        }
        catch (final ArrayIndexOutOfBoundsException e) {
        }
    }

    //method that moves the pieces around the board
    public void recolorImage(final int currentX , final int currentY, final int moveX, final int moveY, final Piece selectedPiece) {
        final int buttonMoveY = Math.abs(7 - moveY);
        final int buttonMoveY2 = Math.abs(7 - currentY);
        int intPlayersPiece=0;
        //returns whether it is a players piece or not
        if (selectedPiece.isPlayersPiece()){
            intPlayersPiece = 1;
            turnStatus.setText("Black's move");
        } else {
            turnStatus.setText("White's move");
        }

        buttons[buttonMoveY][moveX].setIcon(new ImageIcon(chessPieceImages[intPlayersPiece][selectedPiece.getPieceIndex()]));
        buttons[buttonMoveY2][currentX].setIcon(null);
    }

    private final void createImages() {
        try {
            final URL url = new URL("http://i.stack.imgur.com/memI0.png");
            final BufferedImage bi = ImageIO.read(url);
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    chessPieceImages[i][j] = bi.getSubimage(
                            j * 64, i * 64, 64, 64);
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createButtons(final JPanel frame) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j] = new JButton();
                // 'fill this in' using a transparent icon.
//                final ImageIcon icon = new ImageIcon(
//                        new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB));
//                buttons[i][j].setIcon(icon);
            }
        }

        for (int i = 0; i < 8; i++){  //initialize column (i)
            for (int j = 0; j < 8; j++){ //initialize row (j)
                buttons[i][j].putClientProperty("row", Math.abs(7 - i));
                buttons[i][j].putClientProperty("column", j);
                buttons[i][j].addActionListener(this);
                frame.add(buttons[i][j]);
            }
        }
    }


    //method that returns all buttons to their original color moves on the board
    private void colorBoard() {
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){

                if ((i + j) % 2 == 0){
                    buttons[i][j].setBackground(Color.WHITE);
                } else {
                    buttons[i][j].setBackground(Color.BLACK);
                }

                buttons[i][j].setContentAreaFilled(false);
                buttons[i][j].setOpaque(true);
            }
        }
    }

    private void initializebuttons() {
        //initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
        //All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start
        //initializes pawns
        for(int x = 0; x < 8; x++){
            buttons[6][x].setIcon(new ImageIcon(chessPieceImages[1][5]));
            buttons[1][x].setIcon(new ImageIcon(chessPieceImages[0][5]));
        }
        //initializes queens
        buttons[7][3].setIcon(new ImageIcon(chessPieceImages[1][1]));
        buttons[0][3].setIcon(new ImageIcon(chessPieceImages[0][1]));

        //initializes rooks
        buttons[7][0].setIcon(new ImageIcon(chessPieceImages[1][2]));
        buttons[7][7].setIcon(new ImageIcon(chessPieceImages[1][2]));
        buttons[0][0].setIcon(new ImageIcon(chessPieceImages[0][2]));
        buttons[0][7].setIcon(new ImageIcon(chessPieceImages[0][2]));
        //initializes knights
        buttons[7][1].setIcon(new ImageIcon(chessPieceImages[1][3]));
        buttons[7][6].setIcon(new ImageIcon(chessPieceImages[1][3]));
        buttons[0][1].setIcon(new ImageIcon(chessPieceImages[0][3]));
        buttons[0][6].setIcon(new ImageIcon(chessPieceImages[0][3]));
        //initializes bishops
        buttons[7][2].setIcon(new ImageIcon(chessPieceImages[1][4]));
        buttons[7][5].setIcon(new ImageIcon(chessPieceImages[1][4]));
        buttons[0][2].setIcon(new ImageIcon(chessPieceImages[0][4]));
        buttons[0][5].setIcon(new ImageIcon(chessPieceImages[0][4]));
        //initializes kings
        buttons[7][4].setIcon(new ImageIcon(chessPieceImages[1][0]));
        buttons[0][4].setIcon(new ImageIcon(chessPieceImages[0][0]));
    }
}
