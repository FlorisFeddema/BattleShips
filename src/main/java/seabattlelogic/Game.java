package seabattlelogic;

import seabattlegui.ShipType;

public class Game {

    Player[] players;
    private Player playerOnTurn;

    public Game() {

    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        Player player;
        if (players[0].getPlayerNr() == playerNr) {
            player = players[0];
        } else {
            player = players[1];
        }

        if (player.isShipPlaced(shipType)) {
            player.removeShip(shipType);
        }
        return player.placeShip(shipType, bowX, bowY, horizontal);
    }

    /**
     * Rotate the selected ship for a player
     * @param player player for whom the ship will be rotated
     * @param ship ship that will be rotated
     */
    public void rotateShip(Player player, Ship ship) {
        // TODO - implement Game.rotateShip
        throw new UnsupportedOperationException();
    }

    /**
     * Removes a ship for a player
     * @param player player for whom the ship will be removed
     * @param ship ship that will be removed
     */
    public void removeShip(Player player, Ship ship) {
        // TODO - implement Game.removeShip
        throw new UnsupportedOperationException();
    }

    /**
     * Indicate that a player has placed all ship and is ready to play
     * @param player player that is ready to play
     */
    public void setStateToReady(Player player) {
        // TODO - implement Game.setStateToReady
        throw new UnsupportedOperationException();
    }

    /**
     * Fires a shot for a player at the specified position
     * @param player
     * @param cell
     * @return true if the fired shot hit a ship
     */
    public boolean fireShot(Player player, Cell cell) {
        // TODO - implement Game.fireShot
        throw new UnsupportedOperationException();
    }

}