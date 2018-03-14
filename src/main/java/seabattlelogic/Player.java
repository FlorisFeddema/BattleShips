package seabattlelogic;

public class Player {

    Ship[] ships;
    Grid grid;
    private String name;
    private boolean donePlacing;
    private int playerNr;

    public String getName() {
        return this.name;
    }

    /**
     * Place a ship at the specified position
     * @param ship ship to be placed
     * @param position position where the ship will be placed
     * @return true if ship has been placed correctly
     */
    public boolean placeShip(Ship ship, Cell position) {
        // TODO - implement Player.placeShip
        throw new UnsupportedOperationException();
    }

    /**
     * Remove a specified ship
     * @param ship ship to be removed
     */
    public void removeShip(Ship ship) {
        // TODO - implement Player.removeShip
        throw new UnsupportedOperationException();
    }

    /**
     * Set a players state to ready
     */
    public void setStateToReady() {
        // TODO - implement Player.setStateToReady
        throw new UnsupportedOperationException();
    }

    /**
     * Fires a shot at the specified position
     * @param cell position where the shot will be fired at
     * @return true if shot was successfully fired
     */
    public boolean fireShot(Cell cell) {
        // TODO - implement Player.fireShot
        throw new UnsupportedOperationException();
    }

    /**
     * Checks if a player lost
     * @return true if the player lost
     */
    public void checkIfLost() {
        // TODO - implement Player.checkIfLost
        throw new UnsupportedOperationException();
    }

}