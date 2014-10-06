package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ActionListener{

	private static final int ARRAYSIZE = 8; 	
//	 private final static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
//	 boolean playersTurn = true;
//	 ArrayList<Pair> listOfMoves = new ArrayList<Pair>(); // arraylist of legal moves
//	 Piece selectedPiece;
//	 int selectedPieceXPosition;
//	 int selectedPieceYPosition;
	 BufferedImage img = null;
	 JButton moveButton = new JButton("New Game");
	 JButton exitButton = new JButton("Exit");
	 JLabel gameStatusLabel = new JLabel("Chess Fun");
	 
	 private final static JLabel message = new JLabel(
	            "White's move");

	 
	 static JButton[][] button = new JButton[ARRAYSIZE][ARRAYSIZE];
	 private static Image[][] chessPieceImages = new Image[2][6];
	 
	//creates an instance of the TicTacToeGame
		ChessGame game = new ChessGame();
	 
	public GameBoard(){
		JToolBar tools = new JToolBar();
		tools.setFloatable(false);
      
        
		createImages();
		
		
		for (int i = 0; i < 8; i++){  //initialize column (i)
			for (int j = 0; j < 8; j++){ //initialize row (j)
				button[i][j] = new JButton();
				// 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB));
                button[i][j].setIcon(icon);
			}
		}
		
		//creates the JPanel that will cover the entire frame
		JPanel frame = new JPanel();
		
		//sets row0
		frame.setLayout( new GridLayout(8,8));
		frame.setPreferredSize(new java.awt.Dimension(600,535));

		
		for (int i = 0; i < 8; i++){  //initialize column (i)
			for (int j = 0; j < 8; j++){ //initialize row (j)
				button[i][j].putClientProperty("row", Integer.valueOf(Math.abs(7-i)));
				button[i][j].putClientProperty("column", Integer.valueOf(j));
				button[i][j].addActionListener(this);
				frame.add(button[i][j]);
			}
		}
		
		//adds all of the rows to the frame
		setLayout( new FlowLayout());
		add(tools, BorderLayout.PAGE_START);
	       tools.add(new JButton("New")); // TODO - add functionality!	
	        tools.add(new JButton("Save")); // TODO - add functionality!
	        tools.add(new JButton("Restore")); // TODO - add functionality!
	        tools.addSeparator();
	        tools.add(new JButton("Resign")); // TODO - add functionality!
	        tools.addSeparator();
	        tools.add(message);
	        
		add(frame);
		
		colorBoard();

		//initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
		//All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start 
		//initializes pawns	
		for(int x = 0; x<8; x++){
			button[6][x].setIcon(new ImageIcon(chessPieceImages[1][5]));
			button[1][x].setIcon(new ImageIcon(chessPieceImages[0][5]));
		}
		//initializes queens
			button[7][3].setIcon(new ImageIcon(chessPieceImages[1][1]));
			button[0][3].setIcon(new ImageIcon(chessPieceImages[0][1]));
			
		//initializes rooks
			button[7][0].setIcon(new ImageIcon(chessPieceImages[1][2]));
			button[7][7].setIcon(new ImageIcon(chessPieceImages[1][2]));
			button[0][0].setIcon(new ImageIcon(chessPieceImages[0][2]));
			button[0][7].setIcon(new ImageIcon(chessPieceImages[0][2]));
		//initializes knights
			button[7][1].setIcon(new ImageIcon(chessPieceImages[1][3]));
			button[7][6].setIcon(new ImageIcon(chessPieceImages[1][3]));
			button[0][1].setIcon(new ImageIcon(chessPieceImages[0][3]));
			button[0][6].setIcon(new ImageIcon(chessPieceImages[0][3]));
		//initializes bishops
			button[7][2].setIcon(new ImageIcon(chessPieceImages[1][4]));
			button[7][5].setIcon(new ImageIcon(chessPieceImages[1][4]));
			button[0][2].setIcon(new ImageIcon(chessPieceImages[0][4]));
			button[0][5].setIcon(new ImageIcon(chessPieceImages[0][4]));
		//initializes kings
			button[7][4].setIcon(new ImageIcon(chessPieceImages[1][0]));
			button[0][4].setIcon(new ImageIcon(chessPieceImages[0][0]));
			
	}
	
	public void actionPerformed(ActionEvent evt) {	
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		int rowPos = (Integer) source.getClientProperty("row");
		int columnPos = (Integer) source.getClientProperty("column");
		
		game.playTheGame(rowPos, columnPos, source);
		
	}
	
	

	//method that highlights  legal moves on the board
	public static void highlightLocation(int currentX , int currentY, int moveX, int moveY){
		int totalY = Math.abs(7 - (currentY + moveY));
		int totalX = currentX + moveX;
		
		try {
		button[totalY][totalX].setBackground(Color.RED);
		}
		catch (ArrayIndexOutOfBoundsException e){	
		}
	}

	
	//method that returns all buttons to their original color moves on the board
	public static void colorBoard(){
		for (int i = 0; i < 8; i++){  
			for (int j = 0; j < 8; j++){ 
				
				if ((i+j)%2 == 0){
				button[i][j].setBackground(Color.WHITE);
				} else {
				button[i][j].setBackground(Color.BLACK);
				}
				
			}
		}
	}
	//method that moves the pieces around the board
	public static void recolorImage(int currentX , int currentY, int moveX, int moveY, Piece selectedPiece){
		int buttonMoveY = Math.abs(7 - moveY);
		int buttonMoveY2 = Math.abs(7 - currentY);
		int intPlayersPiece=0;
		//returns whether it is a players piece or not
		if (selectedPiece.isPlayersPiece()){
			intPlayersPiece = 1;
			message.setText("Black's move");
		} else {
			message.setText("White's move");
		}

		button[buttonMoveY][moveX].setIcon(new ImageIcon(chessPieceImages[intPlayersPiece][selectedPiece.getPieceIndex()]));
		button[buttonMoveY2][currentX].setIcon(null);
	}
	
	  private final void createImages() {
	        try {
	            URL url = new URL("http://i.stack.imgur.com/memI0.png");
	            BufferedImage bi = ImageIO.read(url);
	            for (int ii = 0; ii < 2; ii++) {
	                for (int jj = 0; jj < 6; jj++) {
	                    chessPieceImages[ii][jj] = bi.getSubimage(
	                            jj * 64, ii * 64, 64, 64);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	    }



	
}
