package es.personalCode;

import java.util.ArrayList;

public abstract class Piece{

//	ArrayList<Pair> list = new ArrayList<Pair>();	
	private int xLocation;
	private int yLocation;
	protected static boolean playersPiece;
	
	public Piece(int xValue, int yValue, boolean players){
		this.xLocation = xValue;
		this.playersPiece = players;
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
	
	
	public  abstract ArrayList<Pair> moveRange();
	
	
	
}
