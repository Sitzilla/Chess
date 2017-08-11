package com.evansitzes.chessgame;

import java.util.ArrayList;

public abstract class Piece{

//	ArrayList<Pair> list = new ArrayList<Pair>();	
	private int xLocation;
	private int yLocation;
	private boolean playersPiece;
	
	public Piece(int xValue, int yValue, boolean playersPiece){
		this.xLocation = xValue;
		this.playersPiece = playersPiece;
		this.yLocation = yValue;
		 
		
	}
	
	public boolean isPlayersPiece(){
		return playersPiece;
	}
	
	public int getYValue(){
		return yLocation;
	}
	public int getXValue(){
		return xLocation;
	}
	public void setYValue(int value){
		yLocation = value;
	}
	public void setXValue(int value){
		xLocation = value;
	}
	
	
	public  abstract ArrayList<Pair[]> moveRange();
	public  abstract int getArraySize();
	public abstract int getPieceIndex();
	public abstract void setHasMoved();
	public abstract void setDiagonals(int leftTop, int leftBottom, int rightTop, int rightBottom, int frontOne, int frontTwo, int bottomOne, int bottomTwo);
	public abstract void resetMove();
}
