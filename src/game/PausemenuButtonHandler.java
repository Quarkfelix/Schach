package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;

public class PausemenuButtonHandler implements ButtonHandler {
	private ArrayList<Button> buttons;

// ======================================== CONSTRUCTOR ========================================
	
	public PausemenuButtonHandler(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}
	
// ======================================== RUN-METHOD =========================================
	
// ======================================== METHODS ============================================
	
	@Override
	public void checkKeyInput(KeyEvent key) {
		switch (key.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			Main.sc.setSceneActive("gamefield");
			break;

		default:
			break;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		for (Button button : buttons) {
			if (button.contains(x, y)) {
				switch (button.getText()) {
				case "RESUME":
					Main.mc.play("effects", 1, false, true);
					System.out.println(Main.sc.setSceneActive("gamefield"));
					break;

				case "SETTINGS":
					Main.mc.play("effects", 1, false, true);
					System.out.println(Main.sc.setSceneActive("settingspage"));
					break;

				case "MENU":
					Main.mc.play("effects", 1, false, true);
					Main.sc.setSceneActive("mainmenu");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + button.getText());
				}
			}
		}
	}
	
// ======================================== GET/SET METHODS ====================================
	
// ======================================== PAINT-METHODS ======================================
	
}
