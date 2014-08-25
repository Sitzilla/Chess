package es.personalCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

	ArrayList<Pair> list = new ArrayList<Pair>();	
	boolean playersPiece = true;
	
	public Pawn(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
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
		Pair option3 = new Pair(-1,1);
		Pair option4 = new Pair(1,1);
		list.add(option1);
		list.add(option2);
		list.add(option3);
		list.add(option4);
		
		return list;
		}
		
		Pair option1 = new Pair(0,-1);
		Pair option2 = new Pair(0,-2);
		Pair option3 = new Pair(-1,-1);
		Pair option4 = new Pair(1,-1);
		list.add(option1);
		list.add(option2);
		list.add(option3);
		list.add(option4);
		return list;
		
	}
	
}
