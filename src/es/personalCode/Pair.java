package es.personalCode;

//class that represents a combination of a String and an associated double.  These are meant to represent a pairing of file name and file size.
public class Pair {
	private int first;
	private int second;
	
	//initializes the 'Pair' object with a String 'first' value and a double 'second' value
	public Pair(int xValue, int yValue){
		this.first = xValue;
		this.second = yValue;
	}
	
	//returns the first value in the pair
	public int getFirst(){
		return first;
	}
	
	//returns the second value in the pair
	public int getSecond(){
		return second;
	}
	
	//adds a passed in value to the 'first' value in the object
	public void addFirst(int number){
		first = number;
	}
	
	//adds a passed in value to the 'second' value in the object
	public void addSecond(int number){
		second = number;
	}
	
	
}
