package es.personalCode;

import java.util.ArrayList;

public class Piece{

	ArrayList<Pair> list = new ArrayList<Pair>();	
	private int xLocation;
	private int yLocation;
	protected static boolean playersPiece;
	
	public Piece(int xValue, int yValue, boolean players){
		this.xLocation = xValue;
		this.playersPiece = players;
		this.yLocation = yValue;
		
		
	}
	
	public int getYValue(){
		return xLocation;
	}
	public int getXValue(){
		return yLocation;
	}
	
	public ArrayList<Pair> moveRange(){
		
		
		
		return list;
		
		
	}
	
}
