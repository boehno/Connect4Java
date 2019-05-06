package model;

public class Board {

	private final int WIDTH;
	private final int HEIGHT;

	private Slot[][] slots;

	public Board(int width, int height) {

		this.HEIGHT = height;
		this.WIDTH = width;

		this.slots = new Slot[this.HEIGHT][this.WIDTH]; //received width and height

		// Creation of the board, this is only made once, to reset use resetBoard()
		for (int row = 0; row < this.HEIGHT; row++) {
			for (int col = 0; col < this.WIDTH; col++) {
				slots[row][col] = new Slot(); // SLOT is settle as Chip.empty on instance
			}
		}

		resetBoard();
	}

	// -------------GETTERS-----------------//

	public Slot[][] getSlots() {
		return this.slots;
	}

	public int getBoardWidth() {
		return this.WIDTH;
	}

	public int getBoardHeight() {
		return this.HEIGHT;
	}

	// ---------------VOIDS--------------//

	/*
	 * set the status of every chip to empty, so they can be reused in a new game
	 */

	public void resetBoard() {
		for (int row = 0; row < this.HEIGHT; row++) {
			for (int col = 0; col < this.WIDTH; col++) {
				this.slots[row][col].setStatus(Chip.EMPTY); // SLOT is settle as Chip.empty on instance
			}
		}
	}

	/*
	 * active column is the column clicked sets the current row to the chip of the
	 * activePlayer
	 */

	public void addChip(int activeColumn, Chip activePlayer) {

		for (int row = (this.HEIGHT - 1); row >= 0; row--) {
			if (this.slots[row][activeColumn].getStatus() == Chip.EMPTY) {
				this.slots[row][activeColumn].setStatus(activePlayer); // activePlayer is the Chip enum with the current
																		// player
				break;
			}
		}
	}

	// ---------------BOARD CONDITIONS--------------//

	public boolean boardFull() {
		for (int row = 0; row < this.HEIGHT; row++) {
			for (int col = 0; col < this.WIDTH; col++) {
				if (this.slots[row][col].getStatus() == Chip.EMPTY) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean columnFull(int column) {
		for (int row = (this.HEIGHT - 1); row >= 0; row--) {
			if (this.slots[row][column].getStatus() == Chip.EMPTY) {
				return false;
			}
		}
		return true;
	}

	// ---------------WIN CONDITIONS--------------//

	/*
	 * if any of the win conditions below is met then...
	 */

	public boolean existsWinner(Chip player) {
		if (horizontalWin(player))
			return true;
		if (verticalWin(player))
			return true;
		if (diagonalLowerRightWin(player))
			return true;
		if (diagonalLowerLeftWin(player))
			return true;

		return false;
	}

	private boolean horizontalWin(Chip player) {
		for (int col = 0; col < this.HEIGHT - 3; col++)
			for (int row = 0; row < this.WIDTH; row++)
				if (this.slots[col][row].getStatus() == player && this.slots[col + 1][row].getStatus() == player
						&& this.slots[col + 2][row].getStatus() == player
						&& this.slots[col + 3][row].getStatus() == player)
					return true;
		return false;
	}

	private boolean verticalWin(Chip player) {
		for (int col = 0; col < HEIGHT; col++)
			for (int row = 0; row < WIDTH - 3; row++)
				if (this.slots[col][row].getStatus() == player && this.slots[col][row + 1].getStatus() == player
						&& this.slots[col][row + 2].getStatus() == player
						&& this.slots[col][row + 3].getStatus() == player)
					return true;

		return false;
	}

	private boolean diagonalLowerRightWin(Chip player) {
		for (int col = 0; col < HEIGHT - 3; col++)
			for (int row = 0; row < WIDTH - 3; row++)
				if (this.slots[col][row].getStatus() == player && this.slots[col + 1][row + 1].getStatus() == player
						&& this.slots[col + 2][row + 2].getStatus() == player
						&& this.slots[col + 3][row + 3].getStatus() == player)
					return true;

		return false;
	}

	private boolean diagonalLowerLeftWin(Chip player) {
		for (int col = 0; col < HEIGHT - 3; col++)
			for (int row = 3; row < WIDTH; row++)
				if (this.slots[col][row].getStatus() == player && this.slots[col + 1][row - 1].getStatus() == player
						&& this.slots[col + 2][row - 2].getStatus() == player
						&& this.slots[col + 3][row - 3].getStatus() == player)
					return true;

		return false;
	}

}
