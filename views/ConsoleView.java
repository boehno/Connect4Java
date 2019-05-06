package views;

import model.*;

public class ConsoleView implements View {

	private Game game; // model for the view
	private Board board; // used to handle UI
	private Slot[][] slots; /// <----------

	public ConsoleView(Game game) {
		System.out.println("E = Empty, Y = Yellow (Player1), R = Red (Player 2)"); // instructions

		this.game = game;
		this.game.addObserver(this); // Observer get suscribed to observable (Game)
		this.board = this.game.getBoard();
		this.slots = this.board.getSlots();

	}

	// --------Observer pattern functions------------//
	@Override
	public void update(Board currentBoard) {
		this.board = currentBoard; // Board gets updated, from here the whole graphics get updated
		System.out.println();
		displayBoard();
	}

	/*
	 * prints the 2d array board with print line
	 */
	public void displayBoard() {

		for (int row = 0; row < this.board.getBoardHeight(); row++) {
			for (int col = 0; col < this.board.getBoardWidth(); col++) {
				printSlotContent(this.slots[row][col].getStatus());
			}
			System.out.println();
		}

	}

	/*
	 * prints the content of the slot
	 */

	private void printSlotContent(Chip state) {
		if (state == Chip.PLAYER1) {
			System.out.print("Y ");
		} else if (state == Chip.PLAYER2) {
			System.out.print("R ");
		} else {
			System.out.print("E ");
		}
	}

	// -------- DISPLAY FUNCTIONS ------------//

	@Override
	public void displayWin(Chip winner) {
		if (winner == Chip.PLAYER1) {
			System.out.print("Player 1 (Yellow) wins!");
		} else if (winner == Chip.PLAYER2) {
			System.out.print("Player 2 (Red) wins!)");
		}
	}

	@Override
	public void displayTie() {
		System.out.println("Tie. No one wins.");
	}

	@Override
	public void displayError(String error) {
		System.out.println(error);
	}

	// only works on the graphical view...
	@Override
	public void applyGUIListeners() {
	}

}
