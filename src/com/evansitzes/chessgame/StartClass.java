package com.evansitzes.chessgame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StartClass {

public static void main(String[] args) {
		
		JFrame window = new JFrame("Chess");
		GameBoard content = new GameBoard();
		window.setContentPane(content);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(false);
		window.pack();
		window.setLocation(100,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
