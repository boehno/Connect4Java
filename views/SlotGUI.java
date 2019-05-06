package views;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;

import model.Chip;

public class SlotGUI extends JLabel {

	private static final long serialVersionUID = 1L;

	private int column;

	private Chip state;

	public SlotGUI(int column) { // recevies a column
		this.column = column;
	}

	// SETTERS

	public void setState(Chip state) {
		this.state = state;
		repaint(); // calls paintcomponent
	}

	public int getColumn() {
		return this.column; // returns the column clicked
	}

	public void paintComponent(Graphics g) // Draws ovals for the Jlabels
	{
		super.paintComponent(g);

		Color currentColor;

		g.setColor(Color.black); // A draw circle is drawed before to make it look like a border in the circle
		g.fillOval(13, 13, 53, 53);

		if (state == Chip.PLAYER1) { // select colors based on a state (Enum)
			currentColor = Color.YELLOW;
		} else if (state == Chip.PLAYER2) {
			currentColor = Color.RED;
		} else {
			currentColor = Color.WHITE;
		}
		g.setColor(currentColor);
		g.fillOval(15, 15, 50, 50);

	}

}
