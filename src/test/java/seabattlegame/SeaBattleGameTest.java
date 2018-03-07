package seabattlegame;

import org.junit.Before;
import org.junit.Test;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.SeaBattleApplication;
import seabattlegui.ShipType;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import static org.junit.Assert.*;

public class SeaBattleGameTest {

    ISeaBattleGame game;
    ISeaBattleGUI ui;

    @Before
    public void setUp(){
        game = new SeaBattleGame();
        ui = new SeaBattleApplication();
    }

    @Test
    public void singleplayerRegisterPlayerTest() {
        assertEquals("Player not added", 0, game.registerPlayer("Dat boi", ui, true));
    }

    @Test
    public void singleplayerRegisterMultiplePlayersTest() {
        assertEquals("Player not added", 0, game.registerPlayer("Dat boi", ui, true));
        assertEquals("Player invalidly added",-1, game.registerPlayer("Frans Bauer", ui, true));
    }

    @Test
    public void multiplayerRegisterPlayerTest() {
        assertEquals("First player not only player", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", 1, game.registerPlayer("Kaas", ui, false));
        assertEquals("Third player invalidly added", -1, game.registerPlayer("Hans", ui, false));
    }

    @Test
    public void multiplayerRegisterDuplicatePlayerTest() {
        assertEquals("First player not only player", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player invalidly added", -1, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", 1, game.registerPlayer("Troep", ui, false));
    }

    @Test
    public void multiplayerRegisterSingleplayerPlayerTest() {
        assertEquals("First player not added", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", -1, game.registerPlayer("Henk", ui, false));
        assertEquals("Singleplayer player added to multiplayer game", -1, game.registerPlayer("Troep", ui, true));
    }

    @Test
    public void placeShipsAutomaticallyTest() {
        game.registerPlayer("Henk", ui, true);
        assertEquals("Ships not placed correctly", true, game.placeShipsAutomatically(0));
    }

    @Test
    public void placeShipTest() {
        game.registerPlayer("Poep", ui, true);
        assertEquals("Ship not placed",true, game.placeShip(0, ShipType.MINESWEEPER, 0, 0, true));
    }

    @Test
    public void placeShipOutOfGridTest() {
        game.registerPlayer("Poep", ui, true);
        assertEquals("Ship placed invalidly",false, game.placeShip(0, ShipType.MINESWEEPER, 10, 10, true));
    }

    @Test
    public void removeShipTest() {
        game.registerPlayer("Steen", ui, true);
        game.placeShip(0, ShipType.MINESWEEPER, 0, 0, false);
        assertEquals("Ship not removed",true, game.removeShip(0, 0, 0));
    }

    @Test
    public void removeShipAtEmptyCellTest() {
        game.registerPlayer("Florp", ui, true);
        game.placeShip(0, ShipType.MINESWEEPER, 5, 5, true);
        assertEquals("",false, game.removeShip(0,0,0));
    }

    @Test
    public void removeAllShipsTest() {
        game.registerPlayer("Kak", ui, true);
        game.placeShipsAutomatically(0);
        assertEquals("Not all ships removed",true, game.removeAllShips(0));
    }

    @Test
    public void notifyWhenReadyTest() {
        game.registerPlayer("Senip", ui, true);
        game.placeShipsAutomatically(0);
        assertEquals("All ships placed but cant set state to ready",true, game.notifyWhenReady(0));
    }

    @Test
    public void notifyWhenReadyNotAllShipsPlacedTest() {
        game.registerPlayer("Senip", ui, true);
        game.placeShip(0, ShipType.MINESWEEPER, 0, 0, true);
        game.placeShip(0, ShipType.CRUISER, 2, 3, false);
        assertEquals("State set to ready but not all ships placed",false, game.notifyWhenReady(0));
    }

    @Test
    public void fireShotPlayer() {
    }

    @Test
    public void fireShotOpponent() {
    }

    @Test
    public void startNewGame() {
    }
}