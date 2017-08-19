package com.evansitzes.chessgame.pieces;

import java.util.ArrayList;

public class Rook extends Piece {
    
    static final int ARRAYSIZE = 7;
    private final ArrayList<Pair[]> list = new ArrayList<Pair[]>();	
    private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
    private boolean hasMoved;

    public Rook(final int xValue, final int yValue, final boolean playersPiece) {
        super(xValue, yValue, playersPiece);
        this.playersPiece = playersPiece;
    }

    @Override
    public int getPieceIndex() {
        return 2;
    }

    @Override
    public int getArraySize() {
        return ARRAYSIZE;
    }

    @Override
    public ArrayList<Pair[]> moveRange() {
        for (int i = 0; i < ARRAYSIZE; i++) {
            arrayOfMoves1[i] = new Pair(0,i + 1);
            arrayOfMoves2[i] = new Pair(i + 1,0);
            arrayOfMoves3[i] = new Pair(0,-i - 1);
            arrayOfMoves4[i] = new Pair(-i - 1,0);
        }

        list.add(arrayOfMoves1);
        list.add(arrayOfMoves2);
        list.add(arrayOfMoves3);
        list.add(arrayOfMoves4);

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
