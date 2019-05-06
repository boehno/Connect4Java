package main;

import controller.Controller;
import model.Game;
import model.GameConnectFour;
import views.ConsoleView;
import views.ControlFrame;
import views.GraphicalView;
import views.View;

public class ConnectFourApp{
	
	public ConnectFourApp() {
		Game game = new GameConnectFour();	
		View consoleView = new ConsoleView(game);
		View graphicalView = new GraphicalView(game);
		Controller controller = new Controller(game,consoleView ,graphicalView); 
		new ControlFrame(controller, graphicalView);	//this is the jframe you see when you start the app
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ConnectFourApp();
	}
	

}
