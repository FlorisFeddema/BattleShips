package seabattlelogic;

import java.util.Collection;

public class Player {

    Collection<Ship> ships;
    Grid grid;
    private String name;
    private boolean donePlacing;

    public String getName() {
        return this.name;
    }

    /**
     * @param ship
     * @param position
     */
    public boolean placeShip(Ship ship, Cell position) {
        // TODO - implement Player.placeShip
        throw new UnsupportedOperationException();
    }

    /**
     * @param ship
     */
    public void removeShip(Ship ship) {
        // TODO - implement Player.removeShip
        throw new UnsupportedOperationException();
    }

    public void setStateToReady() {
        // TODO - implement Player.setStateToReady
        throw new UnsupportedOperationException();
    }

    /**
     * @param cell
     */
    public boolean fireShot(Cell cell) {
        // TODO - implement Player.fireShot
        throw new UnsupportedOperationException();
    }

    public void checkIfLost() {
        // TODO - implement Player.checkIfLost
        throw new UnsupportedOperationException();
    }

}