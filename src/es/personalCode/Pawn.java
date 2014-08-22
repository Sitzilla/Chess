package es.personalCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

	ArrayList<Pair> list = new ArrayList<Pair>();	

	public Pawn(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		// TODO Auto-generated constructor stub
	}

//	Collection<Square> getPossibleMoves(){
//		
//		
//	}
	
	
	public static int getMovementAmount(){
		
		return 2;
	}
	
	public ArrayList<Pair> moveRange(){
		
		if (playersPiece==true){
		
		Pair option1 = new Pair(0,1);
		Pair option2 = new Pair(0,2);
		Pair option3 = new Pair(0,3);
		list.add(option1);
		list.add(option2);
		list.add(option3);
		
		return list;
		}
		
		Pair option1 = new Pair(0,-1);
		Pair option2 = new Pair(0,-2);
		Pair option3 = new Pair(0,-3);
		list.add(option1);
		list.add(option2);
		list.add(option3);
		
		return list;
		
	}
	
}
