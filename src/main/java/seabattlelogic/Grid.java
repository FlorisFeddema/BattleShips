package seabattlelogic;

public class Grid {


    private Cell[][] cells;

    public Grid(int width, int height) {
        this.cells = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}