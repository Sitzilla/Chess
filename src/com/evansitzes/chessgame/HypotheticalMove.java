package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.Piece;

public class HypotheticalMove extends ChessGameLogic {

    private static final int ARRAYSIZE = 8;
     private Piece[][] gameBoard = new Piece[ARRAYSIZE][ARRAYSIZE]; //instantiates an array of 'Pair' objects of size 10
     private boolean playersTurn;
     private int kingsXPosition;
     private int kingsYPosition;

     HypotheticalMove(final Piece[][] gameBoard, final boolean playersTurn) {
         this.gameBoard = gameBoard;
         this.playersTurn = playersTurn;
     }


    public void movePiece(final ChessGameState state, final int piecePositionX, final int piecePositionY, final int pieceMoveX, final int pieceMoveY) {
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
            for(int y = 0; y < 8; y++) {

                if (gameBoard[x][y] == null) {
                    continue;
                }

                if (playersTurn) {
                    if (gameBoard[x][y].getPieceIndex() == 0 && gameBoard[x][y].isPlayersPiece()) {
                        kingsXPosition = x;
                        kingsYPosition = y;
                    }

                } else {
                    if (gameBoard[x][y].getPieceIndex() == 0 && !gameBoard[x][y].isPlayersPiece()) {
                        kingsXPosition = x;
                        kingsYPosition = y;
                    }
                }

            }
        }
    }
}
