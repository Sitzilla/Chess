package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.Pair;
import com.evansitzes.chessgame.pieces.PieceType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessGameLogic {

    private final List<Pair> legalMoves = new ArrayList<Pair>();
//    private int selectedPieceXPosition;
//    private int selectedPieceYPosition;
    private boolean inCheck;

    // Method called whenever the user or computer selects a square.
    // This method also adds the move to either the user's or the computer's array of moves.
    public void playTheGame(final ChessGameState state, final int rowPos, final int columnPos, final JComponent source) {

//        if (state.board.getPiece(columnPos, rowPos) == null) {
//            return false;
//        }

        if (!state.isPieceSelected && (state.board.getPiece(columnPos, rowPos) == null || !state.board.getPiece(columnPos, rowPos).isPlayersPiece())) {
            System.out.println("Player needs to select a piece first.");
            state.isLegalMove = false;
            return;
        }

        // If player has already selected a piece last turn
        if (state.isPieceSelected) {
            final boolean isLegalMove = executeMoves(state, source, rowPos, columnPos);

            if (isLegalMove) {
                state.selectedPiece.resetMove();
                state.isPieceSelected = false;
                state.isLegalMove = true;
                state.isPieceJustMoved = true;
                return;
            }

            return;
        }

        // If the player clicks his own piece treat it as a new selection
        if (state.board.getPiece(columnPos, rowPos).isPlayersPiece()) {
            state.isPieceSelected = true;
            state.selectedPiece = state.board.getPiece(columnPos, rowPos);

            buildMoves(state, rowPos, columnPos);
            state.isLegalMove = true;
            return;
        }


        //logical statement to check if the selected piece is a piece
//        state.board.getPiece(columnPos, rowPos).isPlayersPiece()
//                selectedPiece.resetMove();
//                selectedPiece = null;
        state.isLegalMove = false;
        return;
    }

    private void buildMoves(final ChessGameState state, final int rowPos, final int columnPos) {
        legalMoves.clear();
        final ArrayList<Pair[]> defaultAvailableMoves;

        //if piece is a pawn, check its diagonals for objects
        // WTF is this??
        if (state.selectedPiece.getPieceType() == PieceType.PAWN) {
            setPawnStructure(state, columnPos, rowPos);
        }

        defaultAvailableMoves = state.board.getPiece(columnPos, rowPos).moveRange();

        //takes all moves from the arraylist 'defaultAvailableMoves' and adds the legal moves to the arraylist 'legalMoves'
        for (int i = 0; i < defaultAvailableMoves.size(); i++) {
                legalMoves.addAll(parseLegalMovesInOneDirection(state, defaultAvailableMoves.get(i)));
        }
        //highlights the moves that the piece can make
        state.legalMoves = legalMoves;
    }

    //methods that returns all legal moves for a piece
    private ArrayList<Pair> parseLegalMovesInOneDirection(final ChessGameState state, final Pair[] defaultMovesInOneDirection) {
        final ArrayList<Pair> squaresThatCanBeMovedTo = new ArrayList<>();

//		if (!inCheck) { //if the king is not in check standard rules apply
            for (int i = 0; i < state.selectedPiece.getArraySize(); i++) {
                final HypotheticalMove testMove = new HypotheticalMove(state.board.getDeepCloneOfCurrentState(), true);

                try {
                    //integer variables that are the location of the piece
                    final int xPiece = state.selectedPiece.getXValue() + defaultMovesInOneDirection[i].getFirst();
                    final int yPiece = state.selectedPiece.getYValue() + defaultMovesInOneDirection[i].getSecond();
                    //if the state.board is null

                    if (state.board.getPiece(xPiece, yPiece) != null) {
                        //if the piece that can be taken is a king, change the "check" boolean
//                        if (state.board.getPiece(xPiece, yPiece).getPieceType() == 0) {
//                            state.board.getPiece(xPiece, yPiece).setHasMoved();
//                            inCheck = true;
//                        }

                        if (state.board.getPiece(xPiece, yPiece).isPlayersPiece()) {
                            return squaresThatCanBeMovedTo;
                        }

                        testMove.movePiece(state, state.selectedPiece.getXValue(), state.selectedPiece.getYValue(), xPiece, yPiece);
                        testMove.opponentKingsPosition();

//                        if (!spotIsInThreat(state, testMove.getKingsXPosition(), testMove.getKingsYPosition())) {
//                            squaresThatCanBeMovedTo.add(defaultMovesInOneDirection[i]);
//                        }

                        squaresThatCanBeMovedTo.add(defaultMovesInOneDirection[i]);
                        break;
                    }
                    squaresThatCanBeMovedTo.add(defaultMovesInOneDirection[i]);
                }
                catch (final ArrayIndexOutOfBoundsException | NullPointerException e) {
                    return squaresThatCanBeMovedTo;
                }
            }

        return squaresThatCanBeMovedTo;

//		} else { //if the king IS in check then special rules apply in terms of 'Legal Moves'
//			for (int j = 0; j < selectedPiece.getArraySize();j++) {
//				HypotheticalMove testMove = new HypotheticalMove(state.board, playersTurn);
//				try {	
//					//integer variables that are the location of the piece
//					int xPiece = selectedPiece.getXValue()+inList[j].getFirst();
//					int yPiece = selectedPiece.getYValue()+inList[j].getSecond();
//					
//					
//					//if the state.board is null
//					if (state.board[xPiece][yPiece]!=null) {
//						if (state.board[xPiece][yPiece].isPlayersPiece()!=playersTurn) {
//							//if the piece that can be taken is a king, change the "check" boolean
//							if (state.board[xPiece][yPiece].getPieceType()==0) {
//								state.board[xPiece][yPiece].setHasMoved();
//								inCheck = true;
//							} 
//							
//							testMove.movePiece(selectedPiece.getXValue(), selectedPiece.getYValue(), xPiece, yPiece);
//							testMove.opponentKingsPosition();
//							
//							if (!spotIsInThreat(testMove.getKingsXPosition(), testMove.getKingsYPosition())) {
//								outList.add(inList[j]);
//							}
//							
//							
//						}
//					break;
//					}
//					outList.add(inList[j]);
//				}
//				catch (ArrayIndexOutOfBoundsException e) {	
//				}
//				catch (NullPointerException e) {	
//				}
//			}
//			
//			
//			
//		return outList;
//		}
    }

    // Checks if the king is in check
    private boolean checkTheKing(final ChessGameState state, final int columnPos, final int rowPos) {
        buildMoves(state, rowPos, columnPos);
        if(inCheck) {
            return true;
        }
        return false;
    }

    // Execute the moves passed in, returns true if the move executes successfully
    private boolean executeMoves(final ChessGameState state, final JComponent source, final int rowPos, final int columnPos) {
    //if the selected piece is the players piece then treat it as a new selection
    if (state.board.getPiece(columnPos, rowPos) == null || !state.board.getPiece(columnPos, rowPos).isPlayersPiece()) {

        //if the selected move is in the list of legal moves
        if (source.getBackground() == Color.RED) {

            state.previousXPosition = state.selectedPiece.getXValue();
            state.previousYPosition = state.selectedPiece.getYValue();

            movePiece(state, state.selectedPiece.getXValue(), state.selectedPiece.getYValue(), columnPos, rowPos);

                //TODO check the king logic
    //        if(checkTheKing(state, columnPos, rowPos)) {
    //            inCheck = false;
    //            JOptionPane.showMessageDialog(null, "Check!!");
    //        }

                // TODO modify state
    //		state.boardView.colorBoard();
            return true;
        }
    }

    return false;
}
    //method that moves the pieces around the board
    public void movePiece(final ChessGameState state, final int piecePositionX, final int piecePositionY, final int pieceMoveX, final int pieceMoveY) {
//        state.board.getPiece(piecePositionX, piecePositionY) = state.board[piecePositionX][piecePositionY];
        state.board.getPiece(piecePositionX, piecePositionY).setXValue(pieceMoveX);
        state.board.getPiece(piecePositionX, piecePositionY).setYValue(pieceMoveY);

        state.board.board[state.previousXPosition][state.previousYPosition] = null;
        state.board.board[state.selectedPiece.getXValue()][state.selectedPiece.getYValue()] = state.selectedPiece;
//        state.board.getPiece(piecePositionX, piecePositionY) = null;


        //if piece is a pawn, set its 'hasMoved' boolean to true so that it cannot move two spaces
        if (state.selectedPiece.getPieceType() == PieceType.PAWN) {
            state.selectedPiece.setHasMoved();
        }
    }

    //method that takes a location on the board as an input and returns a true if that area is under "threat" of being taken
    private boolean spotIsInThreat(final ChessGameState state, final int rowPos, final int columnPos) {
        for(int x = 0; x<8; x++) {
            for(int y = 0; y<8; y++) {
//                if (playersTurn) {
                    if(state.board.getPiece(x, y) != null && !state.board.getPiece(x, y).isPlayersPiece()) {
                        buildMoves(state, x, y);
                        //highlights the moves that the piece can make
                        for (int i = 0; i < legalMoves.size(); i++) {
                            if (legalMoves.get(i).getFirst()==rowPos && legalMoves.get(i).getSecond()==columnPos) {
                                return true;
                            }
                        }

                    }
//                } else {
//                    if(state.board[x][y] != null && state.board[x][y].isPlayersPiece()) {
//                        buildMoves(state, x, y, playersTurn);
//                        //highlights the moves that the piece can make
//                        for (int i = 0; i < legalMoves.size(); i++) {
//                            if (legalMoves.get(i).getFirst()==rowPos && legalMoves.get(i).getSecond()==columnPos) {
//                                return true;
//                            }
//                        }
//                    }
//
//                }
            }
        }



        return false;
    }

    private void setPawnStructure(final ChessGameState state, final int columnPos, final int rowPos) {
        int leftTop = 0;
        final int leftBottom = 0;
        final int rightTop = 0;
        final int rightBottom = 0;
        final int topOne = 0;
        final int topTwo = 0;
        final int bottomOne = 0;
        final int bottomTwo = 0;

        System.out.println("WTF is this?");

        if(state.board.getPiece(columnPos - 1, rowPos + 1) != null) {
            leftTop = 1;
        } else {
            leftTop = 0;
        }

//        try {
//            state.board[columnPos-1][rowPos-1].getClass();
//            leftBottom = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                leftBottom = 0;
//            }
//            catch (final NullPointerException e) {
//                leftBottom = 0;
//            }
//        try {
//            state.board[columnPos+1][rowPos+1].getClass();
//            rightTop = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                rightTop = 0;
//            }
//            catch (final NullPointerException e) {
//                rightTop = 0;
//            }
//        try {
//            state.board[columnPos+1][rowPos-1].getClass();
//            rightBottom = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                rightBottom = 0;
//            }
//            catch (final NullPointerException e) {
//                rightBottom = 0;
//            }
//        try {
//            state.board[columnPos][rowPos+1].getClass();
//            topOne = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                topOne = 1;
//            }
//            catch (final NullPointerException e) {
//                topOne = 0;
//            }
//        try {
//            state.board[columnPos][rowPos+2].getClass();
//            topTwo = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                topTwo = 1;
//            }
//            catch (final NullPointerException e) {
//                topTwo = 0;
//            }
//        try {
//            state.board[columnPos][rowPos-1].getClass();
//            bottomOne = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                bottomOne = 1;
//            }
//            catch (final NullPointerException e) {
//                bottomOne = 0;
//            }
//        try {
//            state.board[columnPos][rowPos-2].getClass();
//            bottomTwo = 1;
//            }
//            catch (final ArrayIndexOutOfBoundsException e) {
//                bottomTwo = 1;
//            }
//            catch (final NullPointerException e) {
//                bottomTwo = 0;
//            }


        //Piece leftTop, Piece leftBottom, Piece rightTop, Piece rightBottom, Piece topOne, Piece topTwo, Piece bottomOne, Piece bottomTwo
        state.selectedPiece.setDiagonals(leftTop, leftBottom, rightTop, rightBottom,
                topOne, topTwo, bottomOne, bottomTwo);
    }
}
