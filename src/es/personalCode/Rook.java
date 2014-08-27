package es.personalCode;

import java.util.ArrayList;

public class Rook extends Piece{
	
	final static int ARRAYSIZE = 7;
	ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
	private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
	private boolean playersPiece;
	
public Rook(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
	}

public int getPieceIndex(){
	
	return 2;
}
	
public int getArraySize(){
		
		return ARRAYSIZE;
	}


public ArrayList<Pair[]> moveRange(){
	
	
	for (int i = 0; i < ARRAYSIZE; i++){
		arrayOfMoves1[i] = new Pair(0,i+1);
		arrayOfMoves2[i] = new Pair(i+1,0);
		arrayOfMoves3[i] = new Pair(0,-i-1);
		arrayOfMoves4[i] = new Pair(-i-1,0);
	}

	list.add(arrayOfMoves1);
	list.add(arrayOfMoves2);
	list.add(arrayOfMoves3);
	list.add(arrayOfMoves4);

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
