package es.personalCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

	final static int ARRAYSIZE = 7;
	ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
	private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
	private boolean playersPiece;
	
	public Pawn(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
	}

public int getPieceIndex(){
	
	return 5;
}
	
public int getArraySize(){
		
		return ARRAYSIZE;
	}
	
	public ArrayList<Pair[]> moveRange(){
		
		if (playersPiece==true){
		
		
			
		arrayOfMoves1[0] = new Pair(0,1);
		arrayOfMoves2[0] = new Pair(0,2);
		arrayOfMoves3[0] = new Pair(-1,1);
		arrayOfMoves4[0] = new Pair(1,1);
		list.add(arrayOfMoves1);
		list.add(arrayOfMoves2);
		list.add(arrayOfMoves3);
		list.add(arrayOfMoves4);
		
		return list;
		}
		
		
		arrayOfMoves1[0] = new Pair(0,-1);
		arrayOfMoves2[0] = new Pair(0,-2);
		arrayOfMoves3[0] = new Pair(1,-1);
		arrayOfMoves4[0] = new Pair(-1,-1);
		list.add(arrayOfMoves1);
		list.add(arrayOfMoves2);
		list.add(arrayOfMoves3);
		list.add(arrayOfMoves4);
		return list;
		
	}
	
}
