package com.evansitzes.chessgame.pieces;

import java.util.ArrayList;

public class King extends Piece {

    private static final int ARRAYSIZE = 7;
    private final ArrayList<Pair[]> list = new ArrayList<>();
    private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves5 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves6 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves7 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves8 = new Pair[ARRAYSIZE];
    private final PieceType pieceType = PieceType.KING;
    private boolean hasMoved;

    public King(final int xValue, final int yValue, final boolean playersPiece) {
        super(xValue, yValue, playersPiece);
        this.playersPiece = playersPiece;
    }

    @Override
    public PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public int getArraySize() {
        return ARRAYSIZE;
    }

    @Override
    public ArrayList<Pair[]> moveRange() {
        list.clear();

        arrayOfMoves1[0] = new Pair(1,0);
        arrayOfMoves2[0] = new Pair(1,1);
        arrayOfMoves3[0] = new Pair(1,-1);
        arrayOfMoves4[0] = new Pair(0,1);
        arrayOfMoves5[0] = new Pair(0,-1);
        arrayOfMoves6[0] = new Pair(-1,0);
        arrayOfMoves7[0] = new Pair(-1,1);
        arrayOfMoves8[0] = new Pair(-1,-1);

        list.add(arrayOfMoves1);
        list.add(arrayOfMoves2);
        list.add(arrayOfMoves3);
        list.add(arrayOfMoves4);
        list.add(arrayOfMoves5);
        list.add(arrayOfMoves6);
        list.add(arrayOfMoves7);
        list.add(arrayOfMoves8);

        return list;
    }

    @Override
    public void setHasMoved() {
        hasMoved = true;
    }

    @Override
    public void setDiagonals(final int leftTop, final int leftBottom, final int rightTop, final int rightBottom, final int frontOne, final int frontTwo, final int bottomOne, final int bottomTwo) {
        // TODO Auto-generated method stub
    }

    @Override
    public void resetMove() {
        // TODO Auto-generated method stub
    }
}
