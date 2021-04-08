package mainmenu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scenes;
import libary.Button;
import settingsClasses.GeneralSettings;

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
				Main.mc.play("effects", 1, false, true);
				
				switch (button.getText()) {
				case "SWITCH MONITOR": 
					System.out.println("change display variable in settings savefile");
					break;
					
				case "KEYBINDS":
					Main.sc.setSceneActive(Scenes.keybindings);
					
					break;
					
				case "BACK":
					ArrayList<Scenes> scenehistory = Main.sc.getSceneHistory();
					int i = scenehistory.size()-1;
					for (; i >= 0; i--) {
						if(scenehistory.get(i) == Scenes.mainmenu || scenehistory.get(i) == Scenes.pausemenu) {
							break;
						}
					}
					Main.sc.setSceneActive(scenehistory.get(i));
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + button.getText());
				}
			}
		}
	}
	
// ======================================== GET/SET METHODS ====================================
		
}
