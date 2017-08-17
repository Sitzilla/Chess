package com.evansitzes.chessgame;

import java.util.ArrayList;

public class Knight extends Piece{
	
	final static int ARRAYSIZE = 1;
	ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
	private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves5 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves6 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves7 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves8 = new Pair[ARRAYSIZE];
	private boolean playersPiece;
	
public Knight(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
	}

public int getPieceIndex(){
	
	return 3;
}

public int getArraySize(){
		
		return ARRAYSIZE;
	}


public ArrayList<Pair[]> moveRange(){
	
	
	
		arrayOfMoves1[0] = new Pair(1,2);
		arrayOfMoves2[0] = new Pair(1,-2);
		arrayOfMoves3[0] = new Pair(2,1);
		arrayOfMoves4[0] = new Pair(2,-1);
		arrayOfMoves5[0] = new Pair(-1,2);
		arrayOfMoves6[0] = new Pair(-1,-2);
		arrayOfMoves7[0] = new Pair(-2,1);
		arrayOfMoves8[0] = new Pair(-2,-1);
	

	list.add(arrayOfMoves1);
	list.add(arrayOfMoves2);
	list.add(arrayOfMoves3);
	list.add(arrayOfMoves4);
	list.add(arrayOfMoves5);
	list.add(arrayOfMoves6);
	list.add(arrayOfMoves7);
	list.add(arrayOfMoves8);

	return list;
}

@Override
public void setHasMoved() {
	// TODO Auto-generated method stub
	
}

@Override
public void setDiagonals(int leftTop, int leftBottom, int rightTop, int rightBottom, int frontOne, int frontTwo, int bottomOne, int bottomTwo) {
	// TODO Auto-generated method stub
	
}

@Override
public void resetMove() {
	// TODO Auto-generated method stub
	
}

}