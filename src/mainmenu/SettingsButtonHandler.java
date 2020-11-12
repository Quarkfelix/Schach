package mainmenu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;
import settings.GeneralSettings;

public class SettingsButtonHandler implements ButtonHandler{
	private ArrayList<Button> buttons;

	//Constructor ------------------------------------------------------------------------------------------
		public SettingsButtonHandler(ArrayList<Button> buttons) {
			this.buttons = buttons;
		}
		
	//methods ----------------------------------------------------------------------------------------------
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
						System.out.println("change display variable in settings savefile");
						break;
						
					case "SETTING 2": 
						System.out.println("settings");
						break;
						
					case "BACK": 
						System.out.println(Main.sc.setSceneActive("mainmenu"));
						break;
					default:
						throw new IllegalArgumentException("Unexpected value: " + button.getText());
					}
				}
			}
			
		}

	//getter-setter ----------------------------------------------------------------------------------------

		
	//paint ------------------------------------------------------------------------------------------------
			
}
