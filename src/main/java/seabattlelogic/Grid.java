package seabattlelogic;

public class Grid {

    Cell[][] cells;

    public Grid(int width, int height) {
        this.cells = new Cell[width][height];
    }
}