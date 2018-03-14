package seabattlelogic;

import seabattlegui.SquareState;

public class Cell {
    private SquareState squareState;

    public Cell(SquareState squareState) {
        this.squareState = squareState;
    }

    public SquareState getSquareState() {
        return squareState;
    }

    public void setSquareState(SquareState squareState) {
        this.squareState = squareState;
    }
}