package es.personalCode;

import java.util.ArrayList;

public class Queen extends Piece{
	
	final static int ARRAYSIZE = 7;
	ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
	private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
	private boolean playersPiece;
	
public Queen(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
	}

public int getArraySize(){
		
	return ARRAYSIZE;
	}


public ArrayList<Pair[]> moveRange(){
	
//	for (int i = -7; i < 8; i++){
//		for (int j = -7; j < 8; j++){
//			list.add(new Pair(i,j));
//		}	
//	}
	
	for (int i = 0; i < ARRAYSIZE; i++){
		arrayOfMoves1[i] = new Pair(0,i+1);
	}
	for (int i = 0; i < ARRAYSIZE; i++){
		arrayOfMoves2[i] = new Pair(i+1,0);
	}
	for (int i = 0; i < ARRAYSIZE; i++){
		arrayOfMoves3[i] = new Pair(0,-i-1);
	}
	for (int i = 0; i < ARRAYSIZE; i++){
		arrayOfMoves4[i] = new Pair(-i-1,0);
	}
	
	
	list.add(arrayOfMoves1);
	list.add(arrayOfMoves2);
	list.add(arrayOfMoves3);
	list.add(arrayOfMoves4);

	return list;
}

}
