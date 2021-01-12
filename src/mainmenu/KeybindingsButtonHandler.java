package mainmenu;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;

public class KeybindingsButtonHandler implements ButtonHandler{
	private ArrayList<Button> buttons;
	private Button backButton;
	private int changingButton = -1;
	
// ======================================== CONSTRUCTOR ========================================
	public KeybindingsButtonHandler(ArrayList<Button> buttons, Button backButton) {
		this.buttons = buttons;
		this.backButton = backButton;
	}
	
// ======================================== METHODS ============================================
	@Override
	public void checkKeyInput(KeyEvent key) {
		if(changingButton != -1) {
			buttons.get(changingButton).setText(KeyEvent.getKeyText(key.getKeyCode()));
			changingButton = -1;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		if(backButton.contains(x, y)) {
			Main.sc.setSceneActive("settingspage");
		} else {
			for (int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i).contains(x, y)) {
					buttons.get(i).setText("...");
					changingButton = i;
				}
			}
		}
		
	}
	
	private void overrideSettings() {
		try {
			File settings = new File(getClass().getClassLoader().getResource("Settings.txt").getFile());
			Scanner sc = new Scanner(settings);
			String text = sc.nextLine();
			boolean reading = true;
			while (reading) {
				if (text.equals("KEYBINDINGS")) {
					text = sc.nextLine();
					int i = 0;
					while (!text.equals("END KEYBINDS")) {
						System.out.println(text);
						String[] arr = text.split("~");
						nameTextAreas.get(i).setText(arr[0]);
						if(arr.length == 2) {
							keyButtons.get(i).setText(arr[1]);
						}
						i++;
						text = sc.nextLine();
					}		
				}
				reading = false;
			}
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// ======================================== GET/SET METHODS ====================================
	
// ======================================== PAINT-METHODS ======================================
	
	

}
