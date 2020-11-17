package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scene;
import libary.Button;
import settings.GeneralSettings;
import settings.MainMenuSettings;
import settings.PausemenuSettings;

public class Pausemenu implements Scene {
	private PausemenuButtonHandler buttonHandler;
	private ArrayList<Button> buttons = new ArrayList<>();

	// Settings
	private Color backgroundColor;
	private Color buttonColor;
	private Color buttonBorderColor;
	private Color buttonTextColor;

// ======================================== CONSTRUCTOR ========================================
	
	public Pausemenu() {
		importSettings();
		addButtons();
		setUpButtons();
		buttonHandler = new PausemenuButtonHandler(buttons);
	}
	
// ======================================== RUN-METHOD =========================================	
	
// ======================================== METHODS ============================================
	
	private void importSettings() {
		backgroundColor = PausemenuSettings.backgroundColor;
		buttonColor = PausemenuSettings.buttonColor;
		buttonBorderColor = PausemenuSettings.buttonBorderColor;
		buttonTextColor = PausemenuSettings.buttonTextColor;
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

		// resume
		buttons.get(0).setY(y / 2);
		buttons.get(0).setText("RESUME");

		// settings
		buttons.get(1).setY(y);
		buttons.get(1).setText("SETTINGS");

		// menu
		buttons.get(2).setY(y + y / 2);
		buttons.get(2).setText("MENU");

		//general
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
		Main.sc.getScene("gamefield").paint(g);
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
