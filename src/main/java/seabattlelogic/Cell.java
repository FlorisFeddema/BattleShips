package seabattlelogic;

public class Cell {

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    private boolean hit;

    public boolean isContainsShip() {
        return containsShip;
    }

    public void setContainsShip(boolean containsShip) {
        this.containsShip = containsShip;
    }

    private boolean containsShip;

}