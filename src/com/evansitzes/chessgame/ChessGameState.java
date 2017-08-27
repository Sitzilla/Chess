package com.evansitzes.chessgame;

import com.evansitzes.chessgame.pieces.Pair;
import com.evansitzes.chessgame.pieces.Piece;

import java.util.List;

/**
 * Created by evan on 8/16/17.
 */
public class ChessGameState {

    public List<Pair> legalMoves;
    public GameBoard board;
    public Piece selectedPiece;
    public boolean isPieceSelected = false;
    public boolean isLegalMove = false;
}
