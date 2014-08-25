package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener{

	private static final int ARRAYSIZE = 8; 	
	 private final static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	 boolean isSelected = false;
	 Piece selectedPiece;
	 int selectedPieceXPosition;
	 int selectedPieceYPosition;
	 
	 JButton[][] button = new JButton[ARRAYSIZE][ARRAYSIZE];
	 
	 
	 
	public GameBoard(){

		for (int i = 0; i < 8; i++){  //initialize column (i)
			for (int j = 0; j < 8; j++){ //initialize row (j)
				button[i][j] = new JButton();
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
		for(int x = 0; x<8; x++){
			gameBoard[x][6] = new Pawn(x, 6, false);
			gameBoard[x][1] = new Pawn(x, 1, true);
			button[6][x].setText("white");
			button[1][x].setText("black");
		}
		
			gameBoard[3][7] = new Queen(3, 6, false);
			gameBoard[3][0] = new Queen(3, 1, true);
			button[7][3].setText("queen");
			button[0][3].setText("queen");
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
		
		gameBoard[moveX][moveY] = gameBoard[currentX][currentY];
		gameBoard[moveX][moveY].setXValue(moveX);
		gameBoard[moveX][moveY].setYValue(moveY);
		button[buttonMoveY][moveX].setText("White");
		gameBoard[currentX][currentY] = null;
		button[buttonMoveY2][currentX].setText("");
	}
	
	
	public void actionPerformed(ActionEvent evt) {
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		int rowPos = (Integer) source.getClientProperty("row");
		int columnPos = (Integer) source.getClientProperty("column");
		
		//logical statement to check if the selected piece is a piece
		if (gameBoard[columnPos][rowPos]!=null){	
			
			selectedPiece = gameBoard[columnPos][rowPos];
			isSelected = toggleIsSelected(isSelected);
			ArrayList<Pair> list = new ArrayList<Pair>();	
			list = gameBoard[columnPos][rowPos].moveRange();
			selectedPieceXPosition = gameBoard[columnPos][rowPos].getXValue();
			selectedPieceYPosition = gameBoard[columnPos][rowPos].getYValue();
			colorBoard();
	
				//highlights the moves that the piece can make
				for (int i = 0; i < list.size(); i++) {
					highlightLocation(selectedPieceXPosition, selectedPieceYPosition, list.get(i).getFirst(), list.get(i).getSecond());
				}
		
		}
		
		//logical statement that moves a piece (if selected) to another space
		if (selectedPiece!=null && gameBoard[columnPos][rowPos]==null){
			
			movePiece(selectedPieceXPosition, selectedPieceYPosition, columnPos, rowPos);
			colorBoard();
			selectedPiece = null;
		}
	}
	
	//method to toggle the variable 'isSelected' bewteen true and false
	public boolean toggleIsSelected(boolean value){
		if (isSelected){
			return false;
		}
		return true;
	}
	
	
	
}
