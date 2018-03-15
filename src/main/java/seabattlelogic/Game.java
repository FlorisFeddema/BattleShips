package seabattlelogic;

import seabattlegui.ShipType;

import java.util.Random;

public class Game {

    Player[] players;
    private int playerOnTurn;
    private boolean singlePlayerMode;

    public Game() {
        players = new Player[2];
        //select a random turn
        playerOnTurn = new Random().nextInt(2);
    }

    public int registerPlayer(String name, boolean singlePlayerMode) {
        this.singlePlayerMode = singlePlayerMode;
        if (players[0] == null) {
            players[0] = new Player(name, 0);
            return 0;
        } else {
            if (!players[0].getName().equals(name)) {
                players[1] = new Player(name, 1);
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * Gets the player with the playerNr.
     *
     * @param playerNr 1 or 0, depending on the player
     * @return return the player with the playerNr
     */
    private Player getPlayerByNr(int playerNr) {
        if (playerNr > 1) {
            throw new IllegalArgumentException();
        }
        if (players[0].getPlayerNr() == playerNr) {
            return players[0];
        } else {
            return players[1];
        }
    }

    /**
     * Places a ship on the grid of the player.
     *
     * @param playerNr   nr of the player where the ship should be placed on
     * @param shipType   the type of the ship
     * @param bowX       the min value of the x position
     * @param bowY       the min value of the y position
     * @param horizontal if the ship is placed horizontal
     * @return return if the ship is placed or not
     */
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        Player player = getPlayerByNr(playerNr);

        if (player.isShipPlaced(shipType)) {
            player.removeShip(shipType);
        }
        return player.placeShip(shipType, bowX, bowY, horizontal);
    }


    /**
     * Removes a ship for a player.
     * @param playerNr playerNr for whom the ship will be removed
     * @param posX x position of the ship
     * @param posY y position of the ship
     */
    public boolean removeShip(int playerNr, int posX, int posY) {
        return getPlayerByNr(playerNr).removeShip(posX, posY);
    }

    /**
     * Indicate that a player has placed all ship and is ready to play.
     * @param playerNr playerNr that is ready to play
     * @return if the player is ready or not
     */
    public boolean setStateToReady(int playerNr) {
        boolean succes = getPlayerByNr(playerNr).allShipsPlaced();
        if (singlePlayerMode) {
            players[1] = new Player("AI bot", 1);
            placeShipsRandom(1);
        }

        return succes;
    }

    /**
     * Fires a shot for a player at the specified position.
     * @param playerNr
     * @return true if the fired shot hit a ship
     */
    public boolean fireShot(int playerNr) {
        // TODO - implement Game.fireShot
        throw new UnsupportedOperationException();
    }

    public Grid getPlayerGrid(int playerNr) {
        return getPlayerByNr(playerNr).getGrid();
    }

    /**
     * removes al the ships from the player.
     *
     * @param playerNr player to remove the ships for
     */
    public void removeAllShips(int playerNr) {
        Player player = getPlayerByNr(playerNr);
        for (Ship ship : player.getShips()) {
            ship.removeShip();
        }
    }

    /**
     * places all the ships for the player at pseudorandom positions.
     * It wil just take random positions and keep trying until they fit.
     *
     * @param playerNr player to place the ships for
     */
    public void placeShipsRandom(int playerNr) {
        Player player = getPlayerByNr(playerNr);
        for (Ship ship : player.getShips()) {
            boolean placed = false;
            do {
                Random random = new Random();
                boolean horizontal = random.nextBoolean();
                int x = 0;
                int y = 0;
                if (horizontal) {
                    x = random.nextInt(10 - ship.getLength());
                    y = random.nextInt(10);
                } else {
                    x = random.nextInt(10);
                    y = random.nextInt(10 - ship.getLength());
                }

                placed = placeShip(playerNr, ship.getShipType(), x, y, horizontal);
            } while (!placed);
        }
    }

}