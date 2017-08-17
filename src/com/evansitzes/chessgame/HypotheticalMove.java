package com.evansitzes.chessgame;

public class HypotheticalMove extends ChessGameLogic {

	private static final int ARRAYSIZE = 8; 	
	 private Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
	 private boolean playersTurn;
	 private int kingsXPosition;
	 private int kingsYPosition;
	 
	 HypotheticalMove(Piece[][] gameBoard, boolean playersTurn) {
		 this.gameBoard = gameBoard;
		 this.playersTurn = playersTurn;
	 }


	public void movePiece(int piecePositionX, int piecePositionY, int pieceMoveX, int pieceMoveY){
		gameBoard[pieceMoveX][pieceMoveY] = gameBoard[piecePositionX][piecePositionX];
		gameBoard[piecePositionX][piecePositionX] = null;
	}
	
	public int getKingsXPosition() {
		return kingsXPosition;
	}
	public int getKingsYPosition() {
		return kingsYPosition;
	}
	
	public void opponentKingsPosition() {
		for(int x = 0; x < 8; x++) {
			for(int y = 0; y<8; y++) {
				if (playersTurn){
					if (gameBoard[x][y].getPieceIndex() == 0 && gameBoard[x][y].isPlayersPiece()){
						kingsXPosition = x;
						kingsYPosition = y;
					}
					
				} else {
					if (gameBoard[x][y].getPieceIndex() == 0 && !gameBoard[x][y].isPlayersPiece()){
						kingsXPosition = x;
						kingsYPosition = y;
					}
				}
				
			}
		}
	}
}
