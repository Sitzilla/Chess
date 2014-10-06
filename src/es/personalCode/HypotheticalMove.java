package es.personalCode;

public class HypotheticalMove extends ChessGame{

	private static final int ARRAYSIZE = 8; 	
	 private static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	
	
	 HypotheticalMove(Piece[][] gameBoard){
		 this.gameBoard = gameBoard;
		 
	 }


	public void movePiece(int piecePositionX, int piecePositionY, int pieceMoveX, int pieceMoveY){
		gameBoard[pieceMoveX][pieceMoveY] = gameBoard[piecePositionX][piecePositionX];
		gameBoard[piecePositionX][piecePositionX] = null;
	}
	
	
	
	
	
}
