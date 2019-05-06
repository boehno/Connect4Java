package views;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.*;

public class GraphicalView extends JPanel implements View, MouseListener { // this is a panel that is added as a
																			// componen of the ControlFrame

	private static final long serialVersionUID = 1L;

	private Game game;
	private Board board;
	private Slot[][] plainSlots;

	private SlotGUI[][] slotsGUI; // This is a 2d Array of JLabels, it is used to represent the slots graphically

	public GraphicalView(Game game) {
		this.game = game;
		this.game.addObserver(this);
		this.board = this.game.getBoard();

		this.plainSlots = this.board.getSlots(); // this is just the regular slots

		this.slotsGUI = new SlotGUI[this.board.getBoardHeight()][this.board.getBoardWidth()]; // initiation of the
																								// slotgui array

		createGUI();
	}

	private void createGUI() {
		this.setSize(400, 400); // arbitrary numbers
		this.setLayout(new GridLayout(this.board.getBoardHeight(), this.board.getBoardWidth())); // used a grid because
																									// it fits the best
		createGraphicalBoard(); // the graphical ui is created
		this.setVisible(true);
	}

	/*
	 * the graphical ui is created
	 */

	private void createGraphicalBoard() {
		for (int row = 0; row < this.board.getBoardHeight(); row++) {
			for (int col = 0; col < this.board.getBoardWidth(); col++) {
				this.slotsGUI[row][col] = new SlotGUI(col);
				this.slotsGUI[row][col].setBackground(Color.BLUE);
				this.slotsGUI[row][col].setBorder(new EmptyBorder(0, 15, 0, 0));
				this.slotsGUI[row][col].setOpaque(true);
				this.slotsGUI[row][col].addMouseListener(this); // to respond to mouse effects
				this.add(slotsGUI[row][col]);
			}
		}
	}

	// --------------DISPLAY METHODS-----------------------//

	/*
	 * displays and updates the board on call
	 */

	public void displayBoard() {
		for (int row = 0; row < this.board.getBoardHeight(); row++) {
			for (int col = 0; col < this.board.getBoardWidth(); col++) {
				this.slotsGUI[row][col].setState(plainSlots[row][col].getStatus());
			}
		}
	}

	@Override
	public void displayWin(Chip winner) {
		// TODO Auto-generated method stub
		if (winner == Chip.PLAYER1) {
			JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), "Player 1 (Yellow) wins!"); // the getRoot() is
																									// used to get the
																									// ancestor form of
																									// this jpanel (the
																									// jframe)
		} else if (winner == Chip.PLAYER2) {
			JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), "Player 2 (Red) wins!");
		}

	}

	@Override
	public void displayTie() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), "Tie. No one wins."); // the getRoot() is used to
																							// get the ancestor form of
																							// this jpanel (the jframe)
	}

	@Override
	public void displayError(String error) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(SwingUtilities.getRoot(this), error); // the getRoot() is used to get the ancestor
																			// form of this jpanel (the jframe)

	}

	/*
	 * This is used to give listening properties to the parent form from the
	 * ControlFrame view in the jlabels
	 */

	public void applyGUIListeners() {
		for (SlotGUI[] row : this.slotsGUI) {
			for (SlotGUI s : row) {
				s.addMouseListener((MouseListener) SwingUtilities.getRoot(this));
			}
		}
	}

	// -------Observer Pattern Update--------------

	@Override
	public void update(Board currentBoard) {
		this.board = currentBoard; // updates current board and displays it
		displayBoard();
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		SlotGUI sourceSlot = (SlotGUI) e.getSource(); // I get the source of the clicks and then transform it into a
														// SlotGUI object that can be later use to get the column
														// clicked

		for (SlotGUI[] row : this.slotsGUI) {
			for (SlotGUI s : row) {
				if (s.getColumn() == sourceSlot.getColumn()) {
					s.setBackground(Color.black); // this is what makes the column black when you hove over
				}
			}
		}

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		for (SlotGUI[] row : this.slotsGUI) {
			for (SlotGUI s : row) {
				if (s.getBackground() == Color.black) {
					s.setBackground(Color.blue); // this just returns the column back to blue when you exit the mouse
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
