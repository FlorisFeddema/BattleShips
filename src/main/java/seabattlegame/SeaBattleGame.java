/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattlegame;

import seabattlegui.ISeaBattleGUI;
import seabattlegui.ShipType;
import seabattlegui.ShotType;
import seabattlelogic.Cell;
import seabattlelogic.Game;

import java.util.Random;

/**
 * The Sea Battle game. To be implemented.
 * @author Nico Kuijpers
 */
public class SeaBattleGame implements ISeaBattleGame {

    Game game;
    ISeaBattleGUI application;

    public SeaBattleGame() {
        game = new Game();
    }

    @Override
    public int registerPlayer(String name, ISeaBattleGUI application, boolean singlePlayerMode) {
        this.application = application;
        int playerNr = game.registerPlayer(name, singlePlayerMode);
        return playerNr;
    }

    @Override
    public boolean placeShipsAutomatically(int playerNr) {
        Random random = new Random();
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
        throw new UnsupportedOperationException("Method fireShotPlayer() not implemented.");
    }

    @Override
    public ShotType fireShotOpponent(int playerNr) {
        throw new UnsupportedOperationException("Method fireShotOpponent() not implemented.");
    }

    @Override
    public boolean startNewGame(int playerNr) {
        throw new UnsupportedOperationException("Method startNewGame() not implemented.");
    }

    private void refreshGrid(int playerNr) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                Cell cell = game.getPlayerGrid(playerNr).getCells()[i][j];
                application.showSquarePlayer(playerNr, i, j, cell.getSquareState());
            }
        }
    }
}
