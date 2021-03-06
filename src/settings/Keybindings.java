package settings;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import infrastructure.ButtonHandler;
import infrastructure.Scene;
import libary.Button;
import libary.TextArea;
import settingsClasses.GeneralSettings;
import settingsClasses.KeybindingSettings;
import settingsClasses.MainMenuSettings;
import settingsClasses.SettingsPageSettings;

public class Keybindings implements Scene {
	private ArrayList<Button> keyButtons = new ArrayList<>();
	private ArrayList<TextArea> nameTextAreas = new ArrayList<>();
	private Button backButton;
	private KeybindingsButtonHandler buttonHandler;
	private File settings;

// ======================================== CONSTRUCTOR ========================================
	public Keybindings() {
		setUpTextAreas();
		setUpButtons();
		importKeybinds();
		buttonHandler = new KeybindingsButtonHandler(keyButtons, backButton, nameTextAreas);
	}

// ======================================== METHODS ============================================
	@Override
	public void onActivation() {
		// TODO Auto-generated method stub
		
	}
	
	private void setUpTextAreas() {
		nameTextAreas.add(new TextArea(0, 0, 0, 0, "0"));
		nameTextAreas.add(new TextArea(0, 0, 0, 0, "1"));
		nameTextAreas.add(new TextArea(0, 0, 0, 0, "2"));

		int textAreaWidth = 500;
		int textAreaHeight = 40;
		int width = GeneralSettings.screenWidth;
		int height = 200;

		for (TextArea textArea : nameTextAreas) {
			textArea.setX(width / 2 - 450);
			textArea.setY(height);
			textArea.setWidth(textAreaWidth);
			textArea.setHeight(textAreaHeight);
			height += 60;
			// cosmetic
			textArea.setBackgroundActive(false);
			textArea.setOutlineActive(false);
			textArea.setTextColor(KeybindingSettings.buttonColor);
		}
	}

	private void setUpButtons() {
		// add Buttons
		keyButtons.add(new Button(0, 0, 0, 0));
		keyButtons.add(new Button(0, 0, 0, 0));
		keyButtons.add(new Button(0, 0, 0, 0));
		backButton = new Button(0, 0, 0, 0);

		int buttonWidth = 200;
		int buttonHeight = 70;
		int width = GeneralSettings.screenWidth;
		int height = 200;
		Color buttonColor = KeybindingSettings.buttonColor;
		Color buttonBorderColor = KeybindingSettings.buttonBorderColor;
		Color buttonTextColor = KeybindingSettings.buttonTextColor;

		// Assign buttons
		for (Button b : keyButtons) {
			b.setWidth(buttonWidth);
			b.setHeight(buttonHeight);
			b.setX(width / 2 + 300);
			b.setY(height);
			height += 60;
			// cosmetic
			b.setTextColor(buttonTextColor);
			b.setBorderColor(buttonBorderColor);
			b.setColor(buttonColor);
		}
		backButton.setTextFontSize(20);
		backButton.setText("BACK");
		backButton.setTextActive(false);
		backButton.setBorderActive(false);
		backButton.setWidth(buttonWidth);
		backButton.setHeight(buttonHeight);
		backButton.setX(width/2 - buttonWidth/2);
		backButton.setY(GeneralSettings.screenHeight - buttonHeight - 20);
		backButton.setTextColor(buttonTextColor);
		backButton.setBorderColor(buttonBorderColor);
		backButton.setColor(new Color(0,0,0,0));
		try {
			backButton.setImg(ImageIO.read(getClass().getClassLoader().getResource("Back.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void importKeybinds() {
		try {
			settings = new File(getClass().getClassLoader().getResource("Settings.txt").getFile());
			Scanner sc = new Scanner(settings);
			String text = sc.nextLine();
			while (!text.equals("KEYBINDINGS")) {
				text = sc.nextLine();
			}
			text = sc.nextLine();
			int i = 0;
			while (!text.equals("END KEYBINDS")) {
				System.out.println(text);
				String[] arr = text.split("~");
				nameTextAreas.get(i).setText(arr[0]);
				if (arr.length == 2) {
					keyButtons.get(i).setText(arr[1]);
				}
				i++;
				text = sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

// ======================================== GET/SET METHODS ====================================
	@Override
	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}

// ======================================== PAINT-METHODS ======================================
	@Override
	public void paint(Graphics2D g) {
		for (Button b : keyButtons) {
			b.paint(g);
		}

		for (TextArea textArea : nameTextAreas) {
			textArea.paint(g);
		}

		backButton.paint(g);

	}

}
