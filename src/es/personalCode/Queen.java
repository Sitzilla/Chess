package es.personalCode;

import java.util.ArrayList;

public class Queen extends Piece{
	
	ArrayList<Pair> list = new ArrayList<Pair>();	
	
public Queen(int xValue, int yValue, boolean playersPiece) {
		super(xValue, yValue, playersPiece);
		// TODO Auto-generated constructor stub
	}

public static int getMovementAmount(){
		
		return 10;
	}


public ArrayList<Pair> moveRange(){
	Pair option1 = new Pair(0,1);
	Pair option2 = new Pair(0,2);
	Pair option3 = new Pair(0,3);
	Pair option4 = new Pair(0,4);
	Pair option5 = new Pair(0,5);
	Pair option6 = new Pair(0,6);
	Pair option7 = new Pair(0,7);
	Pair option8 = new Pair(0,-1);
	Pair option9 = new Pair(0,-2);
	Pair option10 = new Pair(0,-3);
	Pair option11 = new Pair(0,-4);
	Pair option12 = new Pair(0,-5);
	Pair option13 = new Pair(0,-6);
	Pair option14 = new Pair(0,-7);
	Pair option15 = new Pair(1,0);
	Pair option16 = new Pair(2,0);
	Pair option17 = new Pair(3,0);
	Pair option18 = new Pair(4,0);
	Pair option19 = new Pair(5,0);
	Pair option20 = new Pair(6,0);
	Pair option21 = new Pair(7,0);
	Pair option22 = new Pair(-1,0);
	Pair option23 = new Pair(-2,0);
	Pair option24 = new Pair(-3,0);
	Pair option25 = new Pair(-4,0);
	Pair option26 = new Pair(-5,0);
	Pair option27 = new Pair(-6,0);
	Pair option28 = new Pair(-7,0);
	Pair option29 = new Pair(-1,-1);
	Pair option30 = new Pair(-1,1);
	Pair option31 = new Pair(1,-1);
	Pair option32 = new Pair(-1,-1);
	list.add(option1);
	list.add(option2);
	list.add(option3);
	list.add(option4);
	list.add(option5);
	list.add(option6);
	list.add(option7);
	list.add(option8);
	list.add(option9);
	list.add(option10);
	list.add(option11);
	list.add(option12);
	list.add(option13);
	list.add(option14);
	list.add(option15);
	list.add(option16);
	list.add(option17);
	list.add(option18);
	list.add(option19);
	list.add(option20);
	list.add(option21);
	list.add(option22);
	list.add(option23);
	list.add(option24);
	list.add(option25);
	list.add(option26);
	list.add(option27);
	list.add(option28);
	list.add(option29);
	list.add(option30);
	list.add(option31);
	list.add(option32);
	return list;
}

}
