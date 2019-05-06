package views;

import model.Board;
import model.Chip;

/*
 *  I created my own observer pattern interfaces because the ones built-in
 *  in java ide were not working as I wanted them to be
 */
public interface View {
	// --------observer pattern------//
	void update(Board board);

	// ------regular view behaviour-----//
	void displayBoard();

	void displayWin(Chip winner);

	void displayTie();

	void displayError(String error);

	void applyGUIListeners();
}
