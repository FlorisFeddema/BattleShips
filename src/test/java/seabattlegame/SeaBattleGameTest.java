package seabattlegame;

import org.junit.Before;
import org.junit.Test;
import seabattlegui.ISeaBattleGUI;
import seabattlegui.SeaBattleApplication;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import static org.junit.Assert.*;

public class SeaBattleGameTest {

    SeaBattleGame game;

    @Before
    public void setUp(){
        game = new SeaBattleGame();
    }

    @Test
    public void singleplayerRegisterPlayerTest() {
        ISeaBattleGUI ui = new SeaBattleApplication();
        assertEquals("Player not added", 0, game.registerPlayer("Dat boi", ui, true));
    }

    @Test
    public void singleplayerRegisterMultiplePlayersTest() {
        ISeaBattleGUI ui = new SeaBattleApplication();
        assertEquals("Player not added", 0, game.registerPlayer("Dat boi", ui, true));
        assertEquals("Player invalidly added",-1, game.registerPlayer("Frans Bauer", ui, true));
    }

    @Test
    public void multiplayerRegisterPlayerTest() {
        ISeaBattleGUI ui = new SeaBattleApplication();
        assertEquals("First player not only player", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", 1, game.registerPlayer("Kaas", ui, false));
        assertEquals("Third player invalidly added", -1, game.registerPlayer("Hans", ui, false));
    }

    @Test
    public void multiplayerRegisterDuplicatePlayerTest() {
        ISeaBattleGUI ui = new SeaBattleApplication();
        assertEquals("First player not only player", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player invalidly added", -1, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", 1, game.registerPlayer("Troep", ui, false));
    }

    @Test
    public void multiplayerRegisterSingleplayerPlayerTest() {
        ISeaBattleGUI ui = new SeaBattleApplication();
        assertEquals("First player not added", 0, game.registerPlayer("Henk", ui, false));
        assertEquals("Second player not added", -1, game.registerPlayer("Henk", ui, false));
        assertEquals("Singleplayer player added to multiplayer game", -1, game.registerPlayer("Troep", ui, true));
    }

    @Test
    public void placeShipsAutomatically() {

    }

    @Test
    public void placeShip() {
    }

    @Test
    public void removeShip() {
    }

    @Test
    public void removeAllShips() {
    }

    @Test
    public void notifyWhenReady() {
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