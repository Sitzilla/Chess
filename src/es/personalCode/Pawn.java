package es.personalCode;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

	ArrayList<Pair> list = new ArrayList<Pair>();	

	public Pawn(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		// TODO Auto-generated constructor stub
	}

	public int getMovementAmount(){
		
		return 2;
	}
	
	public ArrayList<Pair> moveRange(){
		Pair option1 = new Pair(0,1);
		Pair option2 = new Pair(0,2);
		list.add(option1);
		list.add(option2);
		
		return list;
	}
	
}
