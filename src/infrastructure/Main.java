package infrastructure;

public class Main {
	public static GUI gui;
	public static SceneHandler sc;
	
	public static void main(String[] args) {
		//create and start sceneHandler
		sc = new SceneHandler();
		//create and start Graphical User Interface
		gui = new GUI();
	}
}
