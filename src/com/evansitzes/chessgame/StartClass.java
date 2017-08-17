package com.evansitzes.chessgame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class StartClass {

public static void main(String[] args) {
		
		JFrame window = new JFrame("Chess");
		ChessGame game = new ChessGame();
		window.setContentPane(game);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(false);
		window.pack();
		window.setLocation(100,100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

}
