package mainmenu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;

public class MainMenuButtonHandler implements ButtonHandler{
	private ArrayList<Button> buttons;

// ======================================== CONSTRUCTOR ========================================
	public MainMenuButtonHandler(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}
	
// ======================================== RUN-METHOD =========================================
	
// ======================================== METHODS ============================================
	public void checkKeyInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: {
			System.exit(0);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + e);
		}
	}
	
	@Override
	public void checkMouseInput(int x, int y) {
		for (Button button : buttons) {
			if(button.contains(x, y)) {
				switch (button.getText()) {
				case "PLAY": 
					Main.mc.play("effects", 1, false, true);
					System.out.println(Main.sc.setSceneActive("gamefield"));
					break;
					
				case "SETTINGS":
					Main.mc.play("effects", 1, false, true);
					System.out.println(Main.sc.setSceneActive("settingspage"));
					break;
					
				case "QUIT": 
					Main.mc.play("effects", 1, false, true);
					System.out.println("quit");
					System.exit(0);
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + button.getText());
				}
			}
		}
	}
	
// ======================================== GET/SET METHODS ====================================
	
}
