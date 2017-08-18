package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.*;

/**
 * Created by evan on 8/15/17.
 */
public class GameBoard {

    private static final int ARRAYSIZE = 8;

    private final Piece[][] board = new Piece[ARRAYSIZE][ARRAYSIZE];

    public GameBoard() {

        // Initializes the gameboard array with base values.  The array could be thought of as an (x, y) coordinate plane.
        // All values for an array of type 'int' are defaulted to 0, and these loops put values of '1' where piece objects will start
        // initializes pawns
        for(int x = 0; x < 8; x++) {
            board[x][6] = new Pawn(x, 6, false);
            board[x][1] = new Pawn(x, 1, true);

        }
        //initializes queens
        board[3][7] = new Queen(3, 7, false);
        board[3][0] = new Queen(3, 0, true);


        //initializes rooks
        board[7][0] = new Rook(7, 0, true);
        board[0][0] = new Rook(0, 0, true);
        board[7][7] = new Rook(7, 7, false);
        board[0][7] = new Rook(0, 7, false);


        //initializes knights
        board[6][0] = new Knight(6, 0, true);
        board[1][0] = new Knight(1, 0, true);
        board[6][7] = new Knight(6, 7, false);
        board[1][7] = new Knight(1, 7, false);


        //initializes bishops
        board[5][0] = new Bishop(5, 0, true);
        board[2][0] = new Bishop(2, 0, true);
        board[5][7] = new Bishop(5, 7, false);
        board[2][7] = new Bishop(2, 7, false);


        //initializes kings
        board[4][7] = new King(4, 7, false);
        board[4][0] = new King(4, 0, true);

    }
}
