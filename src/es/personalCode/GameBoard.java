package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener{

	private static final int ARRAYSIZE = 8; 	
	 private final static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	
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
		

		//initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
		//All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start 
		for(int x = 0; x<8; x++){
			gameBoard[x][6] = new Pawn(x, 6, false);
			gameBoard[x][1] = new Pawn(x, 1, true);
			button[6][x].setText("white");
			button[1][x].setText("black");
		}		
	}

	//method that highlights  legal moves on the board
	public void highlightLocation(int currentX , int currentY, int moveX, int moveY){
		//need to have error handling to check in bounds here
//		int totalX = Math.abs(8 - (currentX + moveX));
		int totalY = Math.abs(7 - (currentY + moveY));
		int totalX = currentX + moveX;
//		int totalY = currentY + moveY;
		
		
		button[totalY][totalX].setBackground(Color.RED);
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		int rowPos = (Integer) source.getClientProperty("row");
		int columnPos = (Integer) source.getClientProperty("column");
		ArrayList<Pair> list = new ArrayList<Pair>();	
		list = gameBoard[columnPos][rowPos].moveRange();
		System.out.println("row: " + rowPos + ", column: " + columnPos);
		
		
		System.out.println(gameBoard[columnPos][rowPos].getXValue());
		
		highlightLocation(gameBoard[columnPos][rowPos].getXValue(), gameBoard[columnPos][rowPos].getYValue(), list.get(1).getFirst(), list.get(1).getSecond());
	

		
		
	}
	
	
	
	
	
}
