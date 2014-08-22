package es.personalCode;

import java.util.ArrayList;

public class King extends Piece{
	
	ArrayList<Pair> list = new ArrayList<Pair>();	
	
public King(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		// TODO Auto-generated constructor stub
	}

public static int getMovementAmount(){
		
		return 10;
	}


public ArrayList<Pair> moveRange(){
	Pair option1 = new Pair(0,5);
	Pair option2 = new Pair(0,6);
	list.add(option1);
	list.add(option2);
	
	return list;
}

}
