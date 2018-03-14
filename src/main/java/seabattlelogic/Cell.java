package seabattlelogic;

public class Cell {

    /**
     * Gets the hit property of the cell
     * @return true if cell is hit
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Sets the hit property of the cell
     * @param hit
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    private boolean hit;

    /**
     * Gets the containsShip property
     * @return true if cell contains a ship
     */
    public boolean isContainsShip() {
        return containsShip;
    }

    /**
     * Set the containsShip property
     * @param containsShip
     */
    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    private boolean containsShip;

}