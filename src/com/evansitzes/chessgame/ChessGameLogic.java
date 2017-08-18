package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.Pair;
import com.evansitzes.chessgame.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessGameLogic {

    private static final int ARRAYSIZE = 8;
    private static Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10

//    final private ChessGame chessGameREAL;
    private Piece selectedPiece;
    private int selectedPieceXPosition;
    private int selectedPieceYPosition;
    private boolean playersTurn = true;
    private boolean inCheck = false;
    private ArrayList<Pair> listOfMoves = new ArrayList<Pair>(); // arraylist of legal moves

    //constructor that takes in the 'buttonArray' from the board class
    ChessGameLogic() {
//        this.chessGameREAL = new ChessGame();
    }

    //method called whenever the user or computer selects a square.  returns 'true' or 'false' depending on whether the selected move was legal.
    //This method also adds the move to either the user's or the computer's array of moves.
    public boolean playTheGame(ChessGameState state, int rowPos, int columnPos, JComponent source) {

        //logical statement to check if the selected piece is a piece
        if (gameBoard[columnPos][rowPos] != null) {
            try {
                if (gameBoard[columnPos][rowPos].isPlayersPiece() == selectedPiece.isPlayersPiece()){
                    selectedPiece.resetMove();
                    selectedPiece = null;
                }
            }
            catch (NullPointerException e) {
            }
            buildMoves(state, rowPos, columnPos, playersTurn);

        }

        //logical statement that moves a piece (if selected) to another space
        if (selectedPiece != null) {
            executeMoves(state, source, rowPos, columnPos, playersTurn);
        }


        return false;
    }

    private void buildMoves(ChessGameState state, int rowPos, int columnPos, boolean turn){
        //if the selected piece is the players piece then treat it as a new selection
        if (selectedPiece == null && gameBoard[columnPos][rowPos].isPlayersPiece() == turn) {
            listOfMoves.clear();
            selectedPiece = gameBoard[columnPos][rowPos];
            ArrayList<Pair[]> list = new ArrayList<Pair[]>();

            //if piece is a pawn, check its diagonals for objects
            if (selectedPiece.getPieceIndex() == 5){
                setPawnStructure(columnPos, rowPos);
            }

            list = gameBoard[columnPos][rowPos].moveRange();
            selectedPieceXPosition = gameBoard[columnPos][rowPos].getXValue();
            selectedPieceYPosition = gameBoard[columnPos][rowPos].getYValue();

            //TODO color board state
//            gameBoardView.colorBoard();

            //takes all moves from the arraylist 'list' and adds the legal moves to the arraylist 'listOfMoves'
            for (int i = 0; i < list.size(); i++) {
                    listOfMoves.addAll(isLegalMoves(state, list.get(i)));
            }
            //highlights the moves that the piece can make
            for (int i = 0; i < listOfMoves.size(); i++) {
//                TODO highlight moves state
//                gameBoardView.highlightLocation(selectedPieceXPosition, selectedPieceYPosition, listOfMoves.get(i).getFirst(), listOfMoves.get(i).getSecond());
            }
        }
    }

    //methods that returns all legal moves for a piece
    private ArrayList<Pair> isLegalMoves(ChessGameState state, Pair[] inList){
        ArrayList<Pair> outList = new ArrayList<Pair>();

//		if (!inCheck){ //if the king is not in check standard rules apply
            for (int j = 0; j < selectedPiece.getArraySize();j++){
                HypotheticalMove testMove = new HypotheticalMove(gameBoard, playersTurn);
            try {
                //integer variables that are the location of the piece
                int xPiece = selectedPiece.getXValue()+inList[j].getFirst();
                int yPiece = selectedPiece.getYValue()+inList[j].getSecond();
                //if the gameboard is null
                if (gameBoard[xPiece][yPiece]!=null){
                    if (gameBoard[xPiece][yPiece].isPlayersPiece()!=playersTurn){
                        //if the piece that can be taken is a king, change the "check" boolean
                        if (gameBoard[xPiece][yPiece].getPieceIndex()==0){
                            gameBoard[xPiece][yPiece].setHasMoved();
                            inCheck = true;
                        }
                        testMove.movePiece(selectedPiece.getXValue(), selectedPiece.getYValue(), xPiece, yPiece);
                        testMove.opponentKingsPosition();

                        if (!spotIsInThreat(state, testMove.getKingsXPosition(), testMove.getKingsYPosition())){
                            outList.add(inList[j]);
                        }

                    }
                break;
                }
                outList.add(inList[j]);
            }
            catch (ArrayIndexOutOfBoundsException e){
            }
            catch (NullPointerException e){
            }
        }

        return outList;

//		} else { //if the king IS in check then special rules apply in terms of 'Legal Moves'
//			for (int j = 0; j < selectedPiece.getArraySize();j++){
//				HypotheticalMove testMove = new HypotheticalMove(gameBoard, playersTurn);
//				try {	
//					//integer variables that are the location of the piece
//					int xPiece = selectedPiece.getXValue()+inList[j].getFirst();
//					int yPiece = selectedPiece.getYValue()+inList[j].getSecond();
//					
//					
//					//if the gameboard is null
//					if (gameBoard[xPiece][yPiece]!=null){
//						if (gameBoard[xPiece][yPiece].isPlayersPiece()!=playersTurn){
//							//if the piece that can be taken is a king, change the "check" boolean
//							if (gameBoard[xPiece][yPiece].getPieceIndex()==0){
//								gameBoard[xPiece][yPiece].setHasMoved();
//								inCheck = true;
//							} 
//							
//							testMove.movePiece(selectedPiece.getXValue(), selectedPiece.getYValue(), xPiece, yPiece);
//							testMove.opponentKingsPosition();
//							
//							if (!spotIsInThreat(testMove.getKingsXPosition(), testMove.getKingsYPosition())){
//								outList.add(inList[j]);
//							}
//							
//							
//						}
//					break;
//					}
//					outList.add(inList[j]);
//				}
//				catch (ArrayIndexOutOfBoundsException e){	
//				}
//				catch (NullPointerException e){	
//				}
//			}
//			
//			
//			
//		return outList;
//		}
    }

    //Checks if the king is in check
    private boolean checkTheKing(ChessGameState state, int columnPos, int rowPos){
        buildMoves(state, rowPos, columnPos, playersTurn);
        if(inCheck){
            return true;
        }
        return false;
    }

    private void executeMoves(ChessGameState state, JComponent source, int rowPos, int columnPos, boolean turn){
    //if the selected piece is the players piece then treat it as a new selection
    if (gameBoard[columnPos][rowPos] == null){

        //if the selected move is in the list of legal moves
        if (source.getBackground() == Color.RED || gameBoard[columnPos][rowPos].isPlayersPiece()!=turn){
        movePiece(selectedPieceXPosition, selectedPieceYPosition, columnPos, rowPos);
            // TODO modify state
//            gameBoardView.recolorImage(selectedPieceXPosition, selectedPieceYPosition, columnPos, rowPos, selectedPiece);
            selectedPiece.resetMove();
            selectedPiece = null;
            if(checkTheKing(state, columnPos, rowPos)){
                inCheck = false;
                JOptionPane.showMessageDialog(null, "Check!!");
            }

            // TODO modify state
//		gameBoardView.colorBoard();
        selectedPiece.resetMove();
        selectedPiece = null;
        togglePlayersTurn();
        }
    }
}
    //method that moves the pieces around the board
    public void movePiece(int currentX , int currentY, int moveX, int moveY){
        gameBoard[moveX][moveY] = gameBoard[currentX][currentY];
        gameBoard[moveX][moveY].setXValue(moveX);
        gameBoard[moveX][moveY].setYValue(moveY);

        gameBoard[currentX][currentY] = null;


        //if piece is a pawn, set its 'hasMoved' boolean to true so that it cannot move two spaces
        if (selectedPiece.getPieceIndex()==5){
            selectedPiece.setHasMoved();
        }
    }

    //method that takes a location on the board as an input and returns a true if that area is under "threat" of being taken
    private boolean spotIsInThreat(ChessGameState state, int rowPos, int columnPos){
        for(int x = 0; x<8; x++){
            for(int y = 0; y<8; y++){
                if (playersTurn){
                    if(gameBoard[x][y]!=null&&gameBoard[x][y].isPlayersPiece()==false){
                        buildMoves(state, x, y, playersTurn);
                        //highlights the moves that the piece can make
                        for (int i = 0; i < listOfMoves.size(); i++) {
                            if (listOfMoves.get(i).getFirst()==rowPos && listOfMoves.get(i).getSecond()==columnPos){
                                return true;
                            }
                        }

                    }
                } else {
                    if(gameBoard[x][y]!=null&&gameBoard[x][y].isPlayersPiece()==true){
                        buildMoves(state, x, y, playersTurn);
                        //highlights the moves that the piece can make
                        for (int i = 0; i < listOfMoves.size(); i++) {
                            if (listOfMoves.get(i).getFirst()==rowPos && listOfMoves.get(i).getSecond()==columnPos){
                                return true;
                            }
                        }
                    }

                }
            }
        }



        return false;
    }


    //method to toggle the variable 'isSelected' bewteen true and false
    public void togglePlayersTurn(){
        if (playersTurn){
            playersTurn = false;
        } else {
        playersTurn = true;
        }
    }

      public void setPawnStructure(int columnPos, int rowPos){
          int leftTop = 0;
            int leftBottom = 0;
            int rightTop = 0;
            int rightBottom = 0;
            int topOne = 0;
            int topTwo = 0;
            int bottomOne = 0;
            int bottomTwo = 0;

            try {
                gameBoard[columnPos-1][rowPos+1].getClass();
                leftTop = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                leftTop = 0;
                }
                catch (NullPointerException e){
                leftTop = 0;
                }
            try {
                gameBoard[columnPos-1][rowPos-1].getClass();
                leftBottom = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    leftBottom = 0;
                }
                catch (NullPointerException e){
                    leftBottom = 0;
                }
            try {
                gameBoard[columnPos+1][rowPos+1].getClass();
                rightTop = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    rightTop = 0;
                }
                catch (NullPointerException e){
                    rightTop = 0;
                }
            try {
                gameBoard[columnPos+1][rowPos-1].getClass();
                rightBottom = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    rightBottom = 0;
                }
                catch (NullPointerException e){
                    rightBottom = 0;
                }
            try {
                gameBoard[columnPos][rowPos+1].getClass();
                topOne = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    topOne = 1;
                }
                catch (NullPointerException e){
                    topOne = 0;
                }
            try {
                gameBoard[columnPos][rowPos+2].getClass();
                topTwo = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    topTwo = 1;
                }
                catch (NullPointerException e){
                    topTwo = 0;
                }
            try {
                gameBoard[columnPos][rowPos-1].getClass();
                bottomOne = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    bottomOne = 1;
                }
                catch (NullPointerException e){
                    bottomOne = 0;
                }
            try {
                gameBoard[columnPos][rowPos-2].getClass();
                bottomTwo = 1;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    bottomTwo = 1;
                }
                catch (NullPointerException e){
                    bottomTwo = 0;
                }


            //Piece leftTop, Piece leftBottom, Piece rightTop, Piece rightBottom, Piece topOne, Piece topTwo, Piece bottomOne, Piece bottomTwo
            selectedPiece.setDiagonals(leftTop, leftBottom, rightTop, rightBottom,
                    topOne, topTwo, bottomOne, bottomTwo);
      }
}
