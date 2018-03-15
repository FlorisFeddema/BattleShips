package seabattlelogic;

import seabattlegui.ShipType;
import seabattlegui.SquareState;

import java.util.ArrayList;

public class Ship {

    ArrayList<Cell> cells;
    private boolean sunk;
    private ShipType shipType;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.cells = new ArrayList<>();
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
        for (int i = 0; i > cells.size(); ) {
            if (cells.get(i).getSquareState() == SquareState.SHIP) {
                return this.sunk = false;
            }
        }
        for (int i = 0; i > cells.size(); ) {
            if (cells.get(i).getSquareState() == SquareState.SHIP) {
                return this.sunk = false;
            }
        }
        return this.sunk = true;
    }

    public boolean placeShip(ArrayList<Cell> cells) {
        if (isPlaced()) {
            return false;
        }

        for (Cell cell : cells) {
            if (cell.getSquareState() == SquareState.SHIP) {
                return false;
            }
        }
        for (Cell cell : cells) {
            cell.setSquareState(SquareState.SHIP);
        }
        this.cells = cells;
        return true;
    }

    public boolean isOnCell(Cell cell) {
        return cells.contains(cell);
    }

    public boolean isPlaced(){
        return cells.size() != 0;
    }

    public void setCells(ArrayList<Cell> cells){
        this.cells = cells;
    }

    public void removeShip() {
        for (Cell cell : cells) {
            cell.setSquareState(SquareState.WATER);
        }
        setCells(new ArrayList<>());
    }

    public int getLength(){
        switch(shipType){
            default:
                return 0;
            case MINESWEEPER:
                return 2;
            case SUBMARINE:
                return 3;
            case CRUISER:
                return 3;
            case BATTLESHIP:
                return 4;
            case AIRCRAFTCARRIER:
                return 5;
        }
    }
}
