package es.personalCode;

import java.awt.Dimension;

import javax.swing.JFrame;

import es.personalCode.GameBoard;

public class Game {

	public static void main(String[] args) {
		
		JFrame window = new JFrame("Chess");
		GameBoard content = new GameBoard();
		window.setContentPane(content);
		Dimension d = new Dimension(600,600);
		window.setPreferredSize(d);
		window.setResizable(true);
		window.pack();
		window.setLocation(150,150);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

	}
}
