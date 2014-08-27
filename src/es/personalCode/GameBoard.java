package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements ActionListener{

	private static final int ARRAYSIZE = 8; 	
	 private final static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	 boolean playersTurn = true;
	 ArrayList<Pair> listOfMoves = new ArrayList<Pair>(); // arraylist of legal moves
	 Piece selectedPiece;
	 int selectedPieceXPosition;
	 int selectedPieceYPosition;
	 BufferedImage img = null;
	 JButton[][] button = new JButton[ARRAYSIZE][ARRAYSIZE];
	 private Image[][] chessPieceImages = new Image[2][6];
	 
	public GameBoard(){
		
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
		

		for (int i = 0; i < 8; i++){  //initialize column (i)
			for (int j = 0; j < 8; j++){ //initialize row (j)
				
				
				button[i][j].putClientProperty("row", Integer.valueOf(Math.abs(7-i)));
				button[i][j].putClientProperty("column", Integer.valueOf(j));
				button[i][j].addActionListener(this);
				frame.add(button[i][j]);
			}
		}

		//adds all of the rows to the frame
		setLayout( new GridLayout(1,1));
		add(frame);
		
		colorBoard();

		//initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
		//All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start 
		//initializes pawns	
		for(int x = 0; x<8; x++){
			gameBoard[x][6] = new Pawn(x, 6, false);
			gameBoard[x][1] = new Pawn(x, 1, true);
			button[6][x].setIcon(new ImageIcon(chessPieceImages[1][5]));
			button[1][x].setIcon(new ImageIcon(chessPieceImages[0][5]));
		}
		//initializes queens
			gameBoard[3][7] = new Queen(3, 7, false);
			gameBoard[3][0] = new Queen(3, 0, true);
			button[7][3].setIcon(new ImageIcon(chessPieceImages[1][1]));
			button[0][3].setIcon(new ImageIcon(chessPieceImages[0][1]));
			
		//initializes rooks
			gameBoard[7][0] = new Rook(7, 0, true);
			gameBoard[0][0] = new Rook(0, 0, true);
			gameBoard[7][7] = new Rook(7, 7, false);
			gameBoard[0][7] = new Rook(0, 7, false);
			button[7][0].setIcon(new ImageIcon(chessPieceImages[1][2]));
			button[7][7].setIcon(new ImageIcon(chessPieceImages[1][2]));
			button[0][0].setIcon(new ImageIcon(chessPieceImages[0][2]));
			button[0][7].setIcon(new ImageIcon(chessPieceImages[0][2]));
		//initializes knights
			gameBoard[6][0] = new Knight(6, 0, true);
			gameBoard[1][0] = new Knight(1, 0, true);
			gameBoard[6][7] = new Knight(6, 7, false);
			gameBoard[1][7] = new Knight(1, 7, false);
			button[7][1].setIcon(new ImageIcon(chessPieceImages[1][3]));
			button[7][6].setIcon(new ImageIcon(chessPieceImages[1][3]));
			button[0][1].setIcon(new ImageIcon(chessPieceImages[0][3]));
			button[0][6].setIcon(new ImageIcon(chessPieceImages[0][3]));
		//initializes bishops
			gameBoard[5][0] = new Bishop(5, 0, true);
			gameBoard[2][0] = new Bishop(2, 0, true);
			gameBoard[5][7] = new Bishop(5, 7, false);
			gameBoard[2][7] = new Bishop(2, 7, false);
			button[7][2].setIcon(new ImageIcon(chessPieceImages[1][4]));
			button[7][5].setIcon(new ImageIcon(chessPieceImages[1][4]));
			button[0][2].setIcon(new ImageIcon(chessPieceImages[0][4]));
			button[0][5].setIcon(new ImageIcon(chessPieceImages[0][4]));
		//initializes kings
			gameBoard[4][7] = new King(4, 7, false);
			gameBoard[4][0] = new King(4, 0, true);
			button[7][4].setIcon(new ImageIcon(chessPieceImages[1][0]));
			button[0][4].setIcon(new ImageIcon(chessPieceImages[0][0]));
			
	}
	
	public void actionPerformed(ActionEvent evt) {
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		int rowPos = (Integer) source.getClientProperty("row");
		int columnPos = (Integer) source.getClientProperty("column");
		
		//logical statement to check if the selected piece is a piece
		if (gameBoard[columnPos][rowPos]!=null){	
			
			try {
				if (gameBoard[columnPos][rowPos].isPlayersPiece() == selectedPiece.isPlayersPiece()){
					selectedPiece.resetMove();
					selectedPiece = null;
				}
			} 
			catch (NullPointerException e){
			}
			buildMoves(source, rowPos, columnPos, playersTurn);

		}
		
		//logical statement that moves a piece (if selected) to another space
		if (selectedPiece!=null){
			executeMoves(source, rowPos, columnPos, playersTurn);
		}
	}
	
	public void executeMoves(JComponent source, int rowPos, int columnPos, boolean turn){
		//if the selected piece is the players piece then treat it as a new selection
		if (gameBoard[columnPos][rowPos]==null){
			
			//if the selected move is in the list of legal moves
			if (source.getBackground()==Color.RED){
			movePiece(selectedPieceXPosition, selectedPieceYPosition, columnPos, rowPos);
			colorBoard();
			selectedPiece.resetMove();
			selectedPiece = null;
			togglePlayersTurn();
			}
		} else if (gameBoard[columnPos][rowPos].isPlayersPiece()!=turn){
			//if the selected move is in the list of legal moves
			if (source.getBackground()==Color.RED){
				movePiece(selectedPieceXPosition, selectedPieceYPosition, columnPos, rowPos);
				colorBoard();
				selectedPiece.resetMove();
				selectedPiece = null;
				togglePlayersTurn();
			}
		}
	}
	
	public void buildMoves(JComponent source, int rowPos, int columnPos, boolean turn){
		//if the selected piece is the players piece then treat it as a new selection
		if (selectedPiece==null && gameBoard[columnPos][rowPos].isPlayersPiece()==turn){
		listOfMoves.clear();
		selectedPiece = gameBoard[columnPos][rowPos];
		ArrayList<Pair[]> list = new ArrayList<Pair[]>();
		
		//if piece is a pawn, check its diagonals for objects
		if (selectedPiece.getPieceIndex()==5){
			int leftTop = 0;
			int leftBottom = 0;
			int rightTop = 0;
			int rightBottom = 0;
			int topOne = 0;
			int topTwo = 0;
			int bottomOne = 0;
			int bottomTwo = 0;
			
			try {
				gameBoard[columnPos-1][rowPos+1].getClass();
				leftTop = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
				leftTop = 0;
				}
				catch (NullPointerException e){	
				leftTop = 0;
				}
			try {
				gameBoard[columnPos-1][rowPos-1].getClass();
				leftBottom = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					leftBottom = 0;
				}
				catch (NullPointerException e){	
					leftBottom = 0;
				}
			try {
				gameBoard[columnPos+1][rowPos+1].getClass();
				rightTop = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					rightTop = 0;
				}
				catch (NullPointerException e){	
					rightTop = 0;
				}
			try {
				gameBoard[columnPos+1][rowPos-1].getClass();
				rightBottom = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					rightBottom = 0;
				}
				catch (NullPointerException e){	
					rightBottom = 0;
				}
			try {
				gameBoard[columnPos][rowPos+1].getClass();
				topOne = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					topOne = 1;
				}
				catch (NullPointerException e){	
					topOne = 0;
				}
			try {
				gameBoard[columnPos][rowPos+2].getClass();
				topTwo = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					topTwo = 1;
				}
				catch (NullPointerException e){	
					topTwo = 0;
				}
			try {
				gameBoard[columnPos][rowPos-1].getClass();
				bottomOne = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					bottomOne = 1;
				}
				catch (NullPointerException e){	
					bottomOne = 0;
				}
			try {
				gameBoard[columnPos][rowPos-2].getClass();
				bottomTwo = 1;
				}
				catch (ArrayIndexOutOfBoundsException e){	
					bottomTwo = 1;
				}
				catch (NullPointerException e){	
					bottomTwo = 0;
				}
		
			
			//Piece leftTop, Piece leftBottom, Piece rightTop, Piece rightBottom, Piece topOne, Piece topTwo, Piece bottomOne, Piece bottomTwo
			selectedPiece.setDiagonals(leftTop, leftBottom, rightTop, rightBottom, 
					topOne, topTwo, bottomOne, bottomTwo);
		}
		list = gameBoard[columnPos][rowPos].moveRange();
		selectedPieceXPosition = gameBoard[columnPos][rowPos].getXValue();
		selectedPieceYPosition = gameBoard[columnPos][rowPos].getYValue();
		colorBoard();
			//takes all moves from the arraylist 'list' and adds the legal moves to the arraylist 'listOfMoves'
			for (int i = 0; i < list.size(); i++) {
					listOfMoves.addAll(isLegalMoves(list.get(i)));
			}
			//highlights the moves that the piece can make
			for (int i = 0; i < listOfMoves.size(); i++) {
					highlightLocation(selectedPieceXPosition, selectedPieceYPosition, listOfMoves.get(i).getFirst(), listOfMoves.get(i).getSecond());
			}
		}
	}
	

	//method that highlights  legal moves on the board
	public void highlightLocation(int currentX , int currentY, int moveX, int moveY){
		int totalY = Math.abs(7 - (currentY + moveY));
		int totalX = currentX + moveX;
		
		try {
		button[totalY][totalX].setBackground(Color.RED);
		}
		catch (ArrayIndexOutOfBoundsException e){	
		}
	}
	//methods that returns all legal moves for a piece
	public ArrayList<Pair> isLegalMoves(Pair[] inList){
		ArrayList<Pair> outList = new ArrayList<Pair>(); 
		
			for (int j = 0; j < selectedPiece.getArraySize();j++){
			try {	
				//integer variables that are the location of the piece
				int xPiece = selectedPiece.getXValue()+inList[j].getFirst();
				int yPiece = selectedPiece.getYValue()+inList[j].getSecond();
				//if the gameboard is null
				if (gameBoard[xPiece][yPiece]!=null){
					if (gameBoard[xPiece][yPiece].isPlayersPiece()!=playersTurn){
						outList.add(inList[j]);
					}
				break;
				}
				outList.add(inList[j]);
			}
			catch (ArrayIndexOutOfBoundsException e){	
			}
			catch (NullPointerException e){	
			}
		}
		
		return outList;
	}
	
	//method that returns all buttons to their original color moves on the board
	public void colorBoard(){
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
	public void movePiece(int currentX , int currentY, int moveX, int moveY){
		int buttonMoveY = Math.abs(7 - moveY);
		int buttonMoveY2 = Math.abs(7 - currentY);
		int intPlayersPiece=0;
		//returns whether it is a players piece or not
		if (selectedPiece.isPlayersPiece()){
			intPlayersPiece = 1;
		}
		
		gameBoard[moveX][moveY] = gameBoard[currentX][currentY];
		gameBoard[moveX][moveY].setXValue(moveX);
		gameBoard[moveX][moveY].setYValue(moveY);
		button[buttonMoveY][moveX].setIcon(new ImageIcon(chessPieceImages[intPlayersPiece][selectedPiece.getPieceIndex()]));
		gameBoard[currentX][currentY] = null;
		button[buttonMoveY2][currentX].setIcon(null);
		
		//if piece is a pawn, set its 'hasMoved' boolean to true so that it cannot move two spaces
		if (selectedPiece.getPieceIndex()==5){
			selectedPiece.setHasMoved();
		}
	}
	
	//method to toggle the variable 'isSelected' bewteen true and false
	public void togglePlayersTurn(){
		if (playersTurn){
			playersTurn = false;
		} else {
		playersTurn = true;
		}
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
