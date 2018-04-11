package seabattlelogic;

import org.junit.Before;
import org.junit.Test;
import seabattlegame.SeaBattleGame;
import seabattlegui.ShipType;

import static org.junit.Assert.*;

public class GameTest {

    Game game;

    @Before
    public void setUp() {
        game = new Game(new SeaBattleGame());
    }

    @Test
    public void registerPlayerCorrect() {
        int value = game.registerPlayer("Hans", true);
        assertEquals(value, 0);
    }

    @Test
    public void registerPlayerDuplicate() {
        game.registerPlayer("Hans", true);
        int value = game.registerPlayer("Hans", true);
        assertEquals(value, -1);
    }

    @Test
    public void getPlayerByNr() {
        int value = game.registerPlayer("Hans", true);
        Player player = game.getPlayerByNr(value);
        assertEquals(player.getPlayerNr(), value);
    }

    @Test
    public void placeShipCorrect() {
        int value = game.registerPlayer("Hans", true);
        boolean bool = game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        assertTrue(bool);
    }

    @Test
    public void placeShipOverLap() {
        int value = game.registerPlayer("Hans", true);
        game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        boolean bool = game.placeShip(value, ShipType.BATTLESHIP, 1, 1, true);
        assertFalse(bool);
    }

    @Test
    public void placeShipSame() {
        int value = game.registerPlayer("Hans", true);
        game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        boolean bool = game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        assertTrue(bool);
    }

    @Test
    public void placeShipOutOfBounds() {
        int value = game.registerPlayer("Hans", true);
        boolean bool = game.placeShip(value, ShipType.AIRCRAFTCARRIER, 8, 8, false);
        assertFalse(bool);
    }

    @Test
    public void removeShipCorrect() {
        int value = game.registerPlayer("Hans", true);
        game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        boolean bool = game.removeShip(0, 1, 1);
        assertTrue(bool);
    }

    @Test
    public void removeShipIncorrect() {
        int value = game.registerPlayer("Hans", true);
        game.placeShip(value, ShipType.MINESWEEPER, 1, 1, true);
        boolean bool = game.removeShip(0, 5, 1);
        assertTrue(bool);
    }

    @Test
    public void setStateToReady() {
    }

    @Test
    public void fireShot() {
    }

    @Test
    public void fireShotOpponent() {
    }

    @Test
    public void getPlayerGrid() {
    }

    @Test
    public void removeAllShips() {
    }

    @Test
    public void placeShipsRandom() {
    }

    @Test
    public void setOpponent() {
    }

    @Test
    public void setGameReady() {
    }

    @Test
    public void setOpponentReady() {
    }

    @Test
    public void shotFiredMultiplayer() {
    }
}