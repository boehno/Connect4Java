package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * This is an extra view used as a "Control Panel" that receives the  controller and handles the UI from here,
 * instead of making the controller a JFrame, from my point of view it will make it easier to implement extensions
 * to the game and makes the actual controller more slim
 */

public class ControlFrame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private Controller controller; // receives the controller

	private View gameGraphicalView; // Receives a gui of the board

	public ControlFrame(Controller controller, View gameGraphicalView) {
		super("ConnectFour");

		this.controller = controller;
		this.gameGraphicalView = gameGraphicalView;

		createGUI();

		setVisible(true);
		this.gameGraphicalView.applyGUIListeners(); // Here the Slots become clickable from this form so we can get the
													// column value from them
	}

	private void createGUI() {
		setSize(600, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); // USED A BORDER LAYOUT SO I CAN HAVE PANEL NORTH(BUTTONS), PANEL CENTER (GAME
										// GUI), AND SOUTH (STATUS BAR)

		JPanel topPanel = new JPanel();

		JButton button1 = new JButton("Button 1");
		button1.setPreferredSize(new Dimension(170, 30));
		button1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				///// whatever is asked on the assignment
			}
		});

		JButton button2 = new JButton("Button 2");
		button2.setPreferredSize(new Dimension(170, 30));
		button2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				///// whatever is asked on the assignment
			}
		});

		JButton button3 = new JButton("Button 3");
		button3.setPreferredSize(new Dimension(170, 30));
		button3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				///// whatever is asked on the assignment
			}
		});

		topPanel.add(button1);
		topPanel.add(button2);
		topPanel.add(button3);

		this.add(topPanel, BorderLayout.NORTH);

		this.add((Component) this.gameGraphicalView, BorderLayout.CENTER);

		this.add(new JPanel().add(new JLabel("Status bar goes here2")), BorderLayout.SOUTH);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		SlotGUI sourceSlot = (SlotGUI) e.getSource(); // GET the source of the click and used to find a column
		this.controller.addChip(sourceSlot.getColumn());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

}
