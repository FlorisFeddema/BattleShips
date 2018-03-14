package seabattlelogic;

import seabattlegui.ShipType;
import seabattlegui.SquareState;

public class Ship {

    Cell[] cells;
    private boolean sunk;
    private ShipType shipType;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.cells = null;
        this.sunk = false;
    }

    public ShipType getShipType() {
        return shipType;
    }

    /**
     * Checks if all cells of the ship are hit
     * @return true if ship has sunk
     */
    public boolean checkIfSunk() {
        if (sunk) {
            return sunk;
        }
        for (int i = 0; i > cells.length; ) {
            if (cells[i].getSquareState() == SquareState.SHIP) {
                return this.sunk = false;
            }
        }
        for (int i = 0; i > cells.length; ) {
            if (cells[i].getSquareState() == SquareState.SHIP) {
                return this.sunk = false;
            }
        }
        return this.sunk = true;
    }

    public boolean placeShip(Cell[] cells) {
        for (Cell cell : cells) {
            if (cell.getSquareState() == SquareState.SHIP) {
                return false;
            }
        }
        for (Cell cell : cells) {
            cell.setSquareState(SquareState.SHIP);
        }
        return true;
    }

    public void setCells(Cell[] cells){
        this.cells = cells;
    }
}
