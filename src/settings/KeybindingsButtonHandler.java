package settings;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scenes;
import libary.*;

public class KeybindingsButtonHandler implements ButtonHandler{
	private ArrayList<Button> buttons;
	private ArrayList<TextArea> textAreas;
	private Button backButton;
	private int changingButton = -1;
	
// ======================================== CONSTRUCTOR ========================================
	public KeybindingsButtonHandler(ArrayList<Button> buttons, Button backButton, ArrayList<TextArea> textAreas) {
		this.buttons = buttons;
		this.backButton = backButton;
		this.textAreas = textAreas;
	}
	
// ======================================== METHODS ============================================
	@Override
	public void checkKeyInput(KeyEvent key) {
		if(changingButton != -1) {
			buttons.get(changingButton).setText(KeyEvent.getKeyText(key.getKeyCode()));
			overrideSetting(textAreas.get(changingButton).getText(), KeyEvent.getKeyText(key.getKeyCode()));
			changingButton = -1;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		if(backButton.contains(x, y)) {
			Main.sc.setSceneActive(Scenes.settingspage);
		} else {
			for (int i = 0; i < buttons.size(); i++) {
				if(buttons.get(i).contains(x, y)) {
					buttons.get(i).setText("...");
					changingButton = i;
				}
			}
		}
		
	}
	
	private void overrideSetting(String bind, String key) {
		try {
			System.out.println("bind: " + bind + "key: " + key);
			File settings = new File(getClass().getClassLoader().getResource("Settings.txt").getFile());
//			Scanner sc = new Scanner(settings);
			FileWriter fw = new FileWriter(getClass().getClassLoader().getResource("test.txt").getFile());
//			String text = sc.nextLine();
			fw.write("writing files in java ist kacke");
			
			fw.flush();
			System.out.println("written");
//			while (!text.equals("KEYBINDINGS")) {
//				text = sc.nextLine();
//			}
//			text = sc.nextLine();
//			int i = 0;
//			while (!text.equals("END KEYBINDS")) {
//				System.out.println(text);
//				String[] arr = text.split("~");
//				if(arr[0].equals(bind)) {
//					
//				}
//				
//				nameTextAreas.get(i).setText(arr[0]);
//				if(arr.length == 2) {
//					keyButtons.get(i).setText(arr[1]);
//				}
//				i++;
//				text = sc.nextLine();
//			}
//			sc.close();
			fw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
// ======================================== GET/SET METHODS ====================================	
	

}
