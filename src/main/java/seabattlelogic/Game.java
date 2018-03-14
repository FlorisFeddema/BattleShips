package seabattlelogic;

import seabattlegui.ShipType;

import java.util.ArrayList;


public class Game {

    Player[] players;
    private Player playerOnTurn;

    public Game (Player player1){
        players[0] = player1;
    }

    public Game (Player player1, Player player2){
        players[0] = player1;
        players[1] = player2;
    }

    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        throw new UnsupportedOperationException("Method placeShip() not implemented.");
    }

    /**
     * Move a ship for a player to the specified position
     * @param player player for whom the ship will be moved
     * @param ship ship that will be moved
     * @param position cell at which the ship will be moved
     * @return true if ship is successfully moved
     */
    public boolean moveShip(Player player, Ship ship, Cell position) {
        // TODO - implement Game.moveShip
        throw new UnsupportedOperationException();
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