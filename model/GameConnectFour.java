package model;

import java.util.ArrayList;
import views.View;

public class GameConnectFour implements Game {

	private Board gameBoard;

	private ArrayList<View> gameObservers; // Observers list

	private Chip activePlayer;

	private boolean isGameEnded;

	private boolean isTie;

	public GameConnectFour() {
		gameBoard = new Board(7, 6);
		gameObservers = new ArrayList<View>();

	}

	// ---------- MAIN GAME FUNCTIONS ------------//

	/*
	 * default game values
	 */

	@Override
	public void start() {
		this.isGameEnded = false;
		this.isTie = false;
		this.activePlayer = Chip.PLAYER1;
		this.gameBoard.resetBoard();
		this.notifyObservers();
	}

	/*
	 * throws exception on column full adds a column to the board and verifies if
	 * there is already a winner, of the board is full
	 */

	@Override
	public void addChip(int col) throws BoardException {

		if (this.gameBoard.columnFull(col)) {
			throw new BoardException("Column is Full. Try another one");
		}

		this.gameBoard.addChip(col, this.activePlayer);

		if (this.gameBoard.existsWinner(this.activePlayer)) {
			this.isGameEnded = true;
		}

		notifyObservers();

		if (this.gameBoard.boardFull() && !this.gameBoard.existsWinner(this.activePlayer)) {
			this.isTie = true;
		}

	}

	/*
	 * swaps active player
	 */

	@Override
	public void switchPlayer() {
		if (activePlayer == Chip.PLAYER1) {
			activePlayer = Chip.PLAYER2;
		} else if (activePlayer == Chip.PLAYER2) {
			activePlayer = Chip.PLAYER1;
		}
	}

	/*
	 * the four functions bellow return conditions for the controller
	 */

	@Override
	public boolean isTie() {
		return isTie;
	}

	@Override
	public boolean isGameEnded() {
		return isGameEnded;
	}

	@Override
	public Chip getWinner() {
		return this.activePlayer;
	}

	@Override
	public Board getBoard() {
		return this.gameBoard;
	}

	// -------OBSERVER PATTERN FUNCTIONS-----------///

	@Override
	public void notifyObservers() {
		for (View obs : this.gameObservers) {
			obs.update(getBoard());
		}
	}

	@Override
	public void addObserver(View obs) {
		// TODO Auto-generated method stub
		this.gameObservers.add(obs);
	}

	@Override
	public void removeObserver(View obs) {
		// TODO Auto-generated method stub
		this.gameObservers.remove(obs);
	}

}
