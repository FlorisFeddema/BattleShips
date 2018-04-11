package seabattlegame;

import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;
import seabattlegui.SquareState;
import seabattlelogic.Cell;
import seabattlelogic.Game;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    Game game;

    public ISeaBattleGUI getApplication() {
        return application;
    }

    ISeaBattleGUI application;

    public SeaBattleGame() {
        game = new Game(this);
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        this.application = application;
        int playerNr = game.registerPlayer(name, singlePlayerMode);
        if (singlePlayerMode) {
            application.setOpponentName(playerNr, game.getPlayerByNr(1 - playerNr).getName());
        }
        return playerNr;
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        game.placeShipsRandom(playerNr);
        refreshGrid(playerNr);
        return true;
    }

    @Override
    public boolean placeShip(int playerNr, ShipType shipType, int bowX, int bowY, boolean horizontal) {
        boolean placed = game.placeShip(playerNr, shipType, bowX, bowY, horizontal);
        if (placed) {
            refreshGrid(playerNr);
        }
        return placed;
    }

    @Override
    public boolean removeShip(int playerNr, int posX, int posY) {
        boolean removed = game.removeShip(playerNr, posX, posY);
        refreshGrid(playerNr);
        return removed;
    }

    @Override
    public boolean removeAllShips(int playerNr) {
        game.removeAllShips(playerNr);
        refreshGrid(playerNr);
        return true;
    }

    @Override
    public boolean notifyWhenReady(int playerNr) {
        return game.setStateToReady(playerNr);
    }

    @Override
    public ShotType fireShotPlayer(int playerNr, int posX, int posY) {
        System.out.println("\n\n<-- New Turn -->\n");
        ShotType shot = game.fireShot(playerNr, posX, posY);
        System.out.println("Result of the player shot: " + shot);
        refreshOppenentGrid(playerNr);
        return shot;
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        ShotType shot = game.fireShotOpponent(playerNr);
        refreshGrid(playerNr);
        System.out.println("Result of the opponent shot:" + shot);
        application.opponentFiresShot(playerNr, shot);
        return ShotType.MISSED;
    }

    @Override
    public boolean startNewGame(int playerNr) {
        game = new Game(this);
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                application.showSquareOpponent(playerNr, i, j, SquareState.WATER);
                application.showSquarePlayer(playerNr, i, j, SquareState.WATER);
            }
        }
        return true;
    }

    private void refreshGrid(int playerNr) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Cell cell = game.getPlayerGrid(playerNr).getCells()[i][j];
                application.showSquarePlayer(playerNr, i, j, cell.getSquareState());
            }
        }
    }

    private void refreshOppenentGrid(int playerNr) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Cell cell = game.getPlayerGrid(1 - playerNr).getCells()[i][j];
                if (cell.getSquareState() != SquareState.SHIP)
                    application.showSquareOpponent(playerNr, i, j, cell.getSquareState());
            }
        }
    }

    public void fireShotMultiplayer(ShotType result, int x, int y) {
        refreshGrid(game.getApplicationPlayer().getPlayerNr());
        System.out.println("Result of the opponent shot:" + result);
        application.opponentFiresShot(game.getApplicationPlayer().getPlayerNr(), result);
    }
}
