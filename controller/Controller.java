package controller;
import model.Game;
import views.View;

public class Controller {
	
	private Game game; //Game interface instance
	private View consoleView;  //both available views, called from their interface
	private View graphicalView; //<--
	
	/*
	 *  The constructor is used to pass the model and view instances,
	 */
	
	public Controller(Game game, View consoleView, View graphicalView){
		this.game = game;
		this.consoleView = consoleView;
		this.graphicalView = graphicalView;	
		
		start();
	}
	
	/*
	 *  the main operations affecting the board happen here, exceptions are
	 *  catched from here to be able to displayed them in the views, most
	 *  game conditions are handled through booleans
	 */
	
	public void addChip(int column) {		
			try {			
				this.game.addChip(column);
				
				if(this.game.isGameEnded()) {
					
					this.consoleView.displayWin(this.game.getWinner());
					this.graphicalView.displayWin(this.game.getWinner());
					this.start(); //resets game
					
				}else if(this.game.isTie()){
					
					this.consoleView.displayTie();
					this.graphicalView.displayTie();
					this.start(); //resetsgame
				}
				else{
					this.game.switchPlayer();
				}
							
			}catch(Exception error){ //error displayed in views
				this.consoleView.displayError(error.getMessage());
				this.graphicalView.displayError(error.getMessage());
			}
		
	}
		
	public void start() {						
		this.game.start(); // start 
	}
	
}
