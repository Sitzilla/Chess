package com.evansitzes.chessgame;

import java.util.ArrayList;


public class Pawn extends Piece{

	final static int ARRAYSIZE = 7;
	private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
	private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
	private boolean playersPiece;
	private boolean hasMoved = false;
	private boolean canMoveLeft = false;
	private boolean canMoveRight = false;
	private boolean canMoveOne = false;
	private boolean canMoveTwo = false;
	
	public Pawn(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		this.playersPiece = playersPiece;
	}

public void setHasMoved(){
	hasMoved = true;
}

public void resetMove(){
	canMoveLeft = false;
	canMoveRight = false;
	canMoveOne = false;
	canMoveTwo = false;
}

public int getPieceIndex(){
	
	return 5;
}
	
public int getArraySize(){
		
		return ARRAYSIZE;
	}
	

public void setDiagonals(int leftTop, int leftBottom, int rightTop, int rightBottom, int frontOne, int frontTwo, int bottomOne, int bottomTwo){
	if (playersPiece){
			if (leftTop!=0){
				canMoveLeft = true;
			}
			if (rightTop!=0){
				canMoveRight = true;
			}
			if (frontOne==0){
				canMoveOne = true;
			}
			if (frontTwo==0){
				canMoveTwo = true;
			}
	} else {
		if (leftBottom!=0){
			canMoveLeft = true;
		}
		if (rightBottom!=0){
			canMoveRight = true;
		}
		if (bottomOne==0){
			canMoveOne = true;
		}
		if (bottomTwo==0){
			canMoveTwo = true;
		}
	}
		
}

	public ArrayList<Pair[]> moveRange(){
		ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
		
		if (playersPiece==true){
		
		
		if (canMoveOne){
			arrayOfMoves1[0] = new Pair(0,1);
			list.add(arrayOfMoves1);
		}
		
		
		if (hasMoved==false && canMoveTwo == true){
			arrayOfMoves2[0] = new Pair(0,2);
			list.add(arrayOfMoves2);
		}
		
		if (canMoveLeft){
			arrayOfMoves3[0] = new Pair(-1,1);
			list.add(arrayOfMoves3);
		}
		if (canMoveRight){
			arrayOfMoves4[0] = new Pair(1,1);
			list.add(arrayOfMoves4);
		}
		
		
		
		return list;
		}
		
		if (canMoveOne){
			arrayOfMoves1[0] = new Pair(0,-1);
			list.add(arrayOfMoves1);
		}
		if (hasMoved==false && canMoveTwo == true){
			arrayOfMoves2[0] = new Pair(0,-2);
			list.add(arrayOfMoves2);
		}
		if (canMoveLeft){
			arrayOfMoves3[0] = new Pair(-1,-1);
			list.add(arrayOfMoves3);
		}
		if (canMoveRight){
			arrayOfMoves4[0] = new Pair(1,-1);
			list.add(arrayOfMoves4);
		}


		return list;
		
	}
	
}
