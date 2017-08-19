package com.evansitzes.chessgame.pieces;

import java.util.ArrayList;


public class Pawn extends Piece{

    private static final int ARRAYSIZE = 7;
    private final Pair[] arrayOfMoves1 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves2 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves3 = new Pair[ARRAYSIZE];
    private final Pair[] arrayOfMoves4 = new Pair[ARRAYSIZE];
    private boolean hasMoved;
    private boolean canMoveLeft;
    private boolean canMoveRight;
    private boolean canMoveOne;
    private boolean canMoveTwo;

    public Pawn(final int xValue, final int yValue, final boolean playersPiece) {
        super(xValue, yValue, playersPiece);
        this.playersPiece = playersPiece;
    }

    @Override
    public void setHasMoved() {
        hasMoved = true;
    }

    @Override
    public void resetMove() {
        canMoveLeft = false;
        canMoveRight = false;
        canMoveOne = false;
        canMoveTwo = false;
    }

    @Override
    public int getPieceIndex() {
        return 5;
    }

    @Override
    public int getArraySize() {
        return ARRAYSIZE;
    }

    @Override
    public void setDiagonals(final int leftTop, final int leftBottom, final int rightTop, final int rightBottom, final int frontOne, final int frontTwo, final int bottomOne, final int bottomTwo) {
        if (playersPiece) {
            if (leftTop != 0) {
                canMoveLeft = true;
            }

            if (rightTop != 0) {
                canMoveRight = true;
            }

            if (frontOne == 0) {
                canMoveOne = true;
            }

            if (frontTwo == 0) {
                canMoveTwo = true;
            }

        } else {
            if (leftBottom != 0) {
                canMoveLeft = true;
            }

            if (rightBottom != 0) {
                canMoveRight = true;
            }

            if (bottomOne == 0) {
                canMoveOne = true;
            }

            if (bottomTwo == 0) {
                canMoveTwo = true;
            }
        }
    }

    @Override
    public ArrayList<Pair[]> moveRange() {
        final ArrayList<Pair[]> list = new ArrayList<>();

        if (playersPiece) {
            if (canMoveOne) {
                arrayOfMoves1[0] = new Pair(0,1);
                list.add(arrayOfMoves1);
            }

            if (!hasMoved && canMoveTwo) {
                arrayOfMoves2[0] = new Pair(0, 2);
                list.add(arrayOfMoves2);
            }

            if (canMoveLeft) {
                arrayOfMoves3[0] = new Pair(-1, 1);
                list.add(arrayOfMoves3);
            }

            if (canMoveRight) {
                arrayOfMoves4[0] = new Pair(1, 1);
                list.add(arrayOfMoves4);
            }

            return list;
        }

        if (canMoveOne) {
            arrayOfMoves1[0] = new Pair(0, -1);
            list.add(arrayOfMoves1);
        }

        if (!hasMoved && canMoveTwo) {
            arrayOfMoves2[0] = new Pair(0, -2);
            list.add(arrayOfMoves2);
        }

        if (canMoveLeft) {
            arrayOfMoves3[0] = new Pair(-1, -1);
            list.add(arrayOfMoves3);
        }

        if (canMoveRight) {
            arrayOfMoves4[0] = new Pair(1, -1);
            list.add(arrayOfMoves4);
        }

        return list;
    }
}
