package es.personalCode;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

public class ChessGame {

	private static final int ARRAYSIZE = 8; 	
	 private static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10

	//constructor that takes in the 'buttonArray' from the board class
	ChessGame(){
	
			for(int x = 0; x<8; x++){
				gameBoard[x][6] = new Pawn(x, 6, false);
				gameBoard[x][1] = new Pawn(x, 1, true);
			}
			//initializes queens
				gameBoard[3][7] = new Queen(3, 7, false);
				gameBoard[3][0] = new Queen(3, 0, true);
			
			//initializes rooks
				gameBoard[7][0] = new Rook(7, 0, true);
				gameBoard[0][0] = new Rook(0, 0, true);
				gameBoard[7][7] = new Rook(7, 7, false);
				gameBoard[0][7] = new Rook(0, 7, false);
				
			//initializes knights
				gameBoard[6][0] = new Knight(6, 0, true);
				gameBoard[1][0] = new Knight(1, 0, true);
				gameBoard[6][7] = new Knight(6, 7, false);
				gameBoard[1][7] = new Knight(1, 7, false);
				
			//initializes bishops
				gameBoard[5][0] = new Bishop(5, 0, true);
				gameBoard[2][0] = new Bishop(2, 0, true);
				gameBoard[5][7] = new Bishop(5, 7, false);
				gameBoard[2][7] = new Bishop(2, 7, false);
			
			//initializes kings
				gameBoard[4][7] = new King(4, 7, false);
				gameBoard[4][0] = new King(4, 0, true);
		}
	
	
	
	
	
	
	
	
}
