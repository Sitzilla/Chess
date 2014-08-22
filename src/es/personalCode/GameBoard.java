package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

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
				button[i][j].putClientProperty("row", Integer.valueOf(i));
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
			gameBoard[x][1] = new Pawn(x, 1, false);
			gameBoard[x][6] = new Pawn(x, 6, true);
			button[1][x].setText("black");
			button[6][x].setText("white");
		}		
	}

	public void setLocation(int xValue, int yValue){
		//need set statement here
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		int rowPos = (Integer) source.getClientProperty("row");
		int columnPos = (Integer) source.getClientProperty("column");
		
		
		System.out.println("row: " + rowPos + ", column: " + columnPos);
		
		
		System.out.println(gameBoard[1][1].list);
		
		
		
		
	}
	
	
	
	
	
}
