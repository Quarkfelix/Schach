package mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Scene;
import libary.Button;
import libary.Screenkeyboard;
import settings.GeneralSettings;
import settings.MainMenuSettings;

public class MainMenu implements Scene {
	private String name = "mainmenu";
	private ArrayList<Button> buttons = new ArrayList<>();
	public static MainMenuButtonHandler buttonHandler;

	// Settings
	private Color backgroundColor;
	private Color buttonColor;
	private Color buttonBorderColor;
	private Color buttonTextColor;

// ======================================== CONSTRUCTOR ========================================
	
	public MainMenu() {
		importSettings();
		addButtons();
		setUpButtons();
		buttonHandler = new MainMenuButtonHandler(buttons);
	}
	
// ======================================== RUN-METHOD =========================================	
	
// ======================================== METHODS ============================================
	@Override
	public void onActivation() {
		// TODO Auto-generated method stub
		
	}
	
	private void importSettings() {
		backgroundColor = MainMenuSettings.backgroundColor;
		buttonColor = MainMenuSettings.buttonColor;
		buttonBorderColor = MainMenuSettings.buttonBorderColor;
		buttonTextColor = MainMenuSettings.buttonTextColor;
	}

	private void addButtons() {
		buttons.add(new Button(0, 0, 0, 0));
		buttons.add(new Button(0, 0, 0, 0));
		buttons.add(new Button(0, 0, 0, 0));
	}

	private void setUpButtons() {
		int width = 300;
		int height = 100;
		int y = (int) ((GeneralSettings.screenHeight - height) / 2);
		int x = (int) ((GeneralSettings.screenWidth - width) / 2);

		// setup Playbutton
		buttons.get(0).setY(y / 2);
		buttons.get(0).setText("PLAY");

		// setup Settingsbutton
		buttons.get(1).setY(y);
		buttons.get(1).setText("SETTINGS");

		// setup Quitbutton
		buttons.get(2).setY(y + y / 2);
		buttons.get(2).setText("QUIT");

		// overall design
		for (Button button : buttons) {
			button.setX(x);
			button.setWidth(width);
			button.setHeight(height);

			button.setTextColor(buttonTextColor);
			button.setBorderColor(buttonBorderColor);
			button.setColor(buttonColor);
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
		drawBackground(g);
		drawButtons(g);
	}

	private void drawBackground(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, GeneralSettings.screenWidth, GeneralSettings.screenHeight);
	}

	private void drawButtons(Graphics2D g) {
		buttons.forEach((v) -> v.paint(g));
	}
}
