package model;

import views.View;

	/*
	 *  I created my own observer pattern interfaces because the ones built-in
	 *  in java ide were not working as I wanted them to be
	 */

public interface Game { //GAME MODEL INTERFACE
	void start();
	void addChip(int column) throws BoardException;
	boolean isGameEnded();
	boolean isTie();
	void switchPlayer();
	Chip getWinner();
	Board getBoard();
	
	
	//-----Observable methods----------//
	void addObserver(View obs);
	void removeObserver(View obs);
	void notifyObservers();
}
