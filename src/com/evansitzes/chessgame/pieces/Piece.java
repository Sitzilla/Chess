package com.evansitzes.chessgame.pieces;

import java.util.ArrayList;

public abstract class Piece {

    private int xLocation;
    private int yLocation;

    protected boolean playersPiece;

    public Piece(final int xValue, final int yValue, final boolean playersPiece) {
        this.xLocation = xValue;
        this.playersPiece = playersPiece;
        this.yLocation = yValue;
    }

    public boolean isPlayersPiece() {
        return playersPiece;
    }

    public int getYValue() {
        return yLocation;
    }
    public int getXValue() {
        return xLocation;
    }
    public void setYValue(final int value) {
        yLocation = value;
    }
    public void setXValue(final int value) {
        xLocation = value;
    }

    public abstract ArrayList<Pair[]> moveRange();
    public abstract PieceType getPieceType();
    public abstract int getArraySize();
    public abstract void setHasMoved();
    public abstract void setDiagonals(int leftTop, int leftBottom, int rightTop, int rightBottom, int frontOne, int frontTwo, int bottomOne, int bottomTwo);
    public abstract void resetMove();
}
