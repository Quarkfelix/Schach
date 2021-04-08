package game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scenes;
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
			Main.sc.setSceneActive(Scenes.gamefield);
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
					System.out.println(Main.sc.setSceneActive(Scenes.gamefield));
					break;

				case "SETTINGS":
					Main.mc.play("effects", 1, false, true);
					System.out.println(Main.sc.setSceneActive(Scenes.settingspage));
					break;

				case "MENU":
					Main.mc.play("effects", 1, false, true);
					Main.sc.setSceneActive(Scenes.mainmenu);
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
