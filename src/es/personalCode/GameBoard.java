package es.personalCode;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

import javax.swing.*;

public class GameBoard extends JPanel implements ActionListener{

	private static final int ARRAYSIZE = 7; 	
	 private final static Piece[] gameBoard = new Piece[ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	
	 JButton[] button = new JButton[2];
	 
	 
	 
	public GameBoard(){
		int row = 0;
		button[0] = new JButton("0");
		button[1] = new JButton("1");
//		//creates the JPanels that will cover each row
		JPanel row0 = new JPanel();
//		JPanel row1 = new JPanel();
//		JPanel row2 = new JPanel();
//		JPanel row3 = new JPanel();
//		JPanel row4 = new JPanel();
//		JPanel row5 = new JPanel();
//		JPanel row6 = new JPanel();
//		JPanel row7 = new JPanel();
//		
//		JButton a0 = new JButton("");
//		JButton a1 = new JButton("p");
//		JButton a2 = new JButton("");
//		JButton a3 = new JButton("");
//		JButton a4 = new JButton("");
//		JButton a5 = new JButton("");
//		JButton a6 = new JButton("");
//		JButton a7 = new JButton("");
	
		
		
//		JPanel p = new JPanel(new GridLayout(8,8));
//		for(int i = 0; i < button.length; i++) {
//		  for(int j = 0; j < button[i].length; j++) {
//		    p.add(button[i][j]);
//		  }
//		}

		
		
//		//sets row0
		row0.setLayout( new GridLayout(2,1));
		button[0].putClientProperty("row", Integer.valueOf(0));
		button[0].addActionListener(this);
		row0.add(button[0]);
		button[1].putClientProperty("row", Integer.valueOf(1));
		button[1].addActionListener(this);
		row0.add(button[1]);
//		a2.addActionListener(this);
//		row0.add(a2);
//		a3.addActionListener(this);
//		row0.add(a3);
//		a4.addActionListener(this);
//		row0.add(a4);
//		a5.addActionListener(this);
//		row0.add(a5);
//		a6.addActionListener(this);
//		row0.add(a6);
//		a7.addActionListener(this);
//		row0.add(a7);
		
		//adds all of the rows to the frame
		setLayout( new GridLayout(1,1));
		add(row0);
		
		
		
		
		//initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
		//All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start 
		//for(int y = 0; y<2; y++){
			gameBoard[0] = new Pawn(0);
			gameBoard[1] = new King(1);
		//	for(int y = 0; y<2; y++){
//			gameBoard[x][0] =  a0;
		//	}
		//	for(int y = 6; y<8; y++){
		//		gameBoard[x][y] =  1;
		//	}
		//}
	}

//public static int getLocation(int xValue, int yValue){
		//need if statement here
		
	//	return gameBoard[xValue][yValue];
	//}
	
	public void setLocation(int xValue, int yValue){
		//need set statement here
		
	}
	
	public void actionPerformed(ActionEvent evt) {
		JComponent source = (JComponent)evt.getSource(); //finds the source of the objects that triggers the event
		
		Integer number = (Integer) source.getClientProperty("row");
		
		System.out.println(number);
		
		
		
	}
	
	
	
	
	
}
