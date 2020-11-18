package mainmenu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;
import settings.GeneralSettings;

public class SettingsButtonHandler implements ButtonHandler{
	private ArrayList<Button> buttons;
	
// ======================================== CONSTRUCTOR ========================================
	
	public SettingsButtonHandler(ArrayList<Button> buttons) {
		this.buttons = buttons;
	}
	
// ======================================== RUN-METHOD =========================================	
	
// ======================================== METHODS ============================================
	
	public void checkKeyInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE: {
			System.out.println("esc");
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
				case "SWITCH MONITOR": 
					Main.mc.play("effects", 1, false, true);
					System.out.println("change display variable in settings savefile");
					break;
					
				case "MUTE":
					Main.mc.play("effects", 1, false, true);
					Main.mc.mute();
					break;
					
				case "BACK":
					Main.mc.play("effects", 1, false, true);
					Main.sc.setSceneActive(Main.sc.getlastScene());
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + button.getText());
				}
			}
		}
	}
	
// ======================================== GET/SET METHODS ====================================
		
}
