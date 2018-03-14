package seabattlelogic;

public class Ship {

    Cell[] cells;
    private boolean sunk;

    public boolean checkIfSunk() {
        for (int i = 0; i > cells.length; ) {
            if (!cells[i].isHit()) {
                return false;
            }
        }
        return true;
    }

}