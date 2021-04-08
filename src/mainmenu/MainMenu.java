package mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import infrastructure.ButtonHandler;
import infrastructure.Scene;
import libary.Button;
import libary.Screenkeyboard;
import settingsClasses.GeneralSettings;
import settingsClasses.MainMenuSettings;

public class MainMenu implements Scene {
	private String name = "mainmenu";
	private ArrayList<Button> buttons = new ArrayList<>();
	public static MainMenuButtonHandler buttonHandler;
	private BufferedImage background;

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
		try {
			background = ImageIO.read(getClass().getClassLoader().getResource("Chess.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		int height = 110;
		int y = (int) ((GeneralSettings.screenHeight - height) / 2);
		int x = (int) ((GeneralSettings.screenWidth - width) / 2);

		// setup Playbutton
		buttons.get(0).setY(y-400);
		buttons.get(0).setX(x);
		buttons.get(0).setText("PLAY");
		
		// setup Settingsbutton
		buttons.get(1).setY(y-400);
		buttons.get(1).setX(x-600);
		buttons.get(1).setText("SETTINGS");

		// setup Quitbutton
		buttons.get(2).setY(y-400);
		buttons.get(2).setX(x+600);
		buttons.get(2).setText("QUIT");

		// overall design
		for (Button button : buttons) {
			button.setWidth(width);
			button.setHeight(height);
			button.setBorderActive(false);
			button.setTextActive(false);
			
			button.setTextColor(buttonTextColor);
			button.setBorderColor(buttonBorderColor);
			button.setColor(buttonColor);
		}
		try {
			buttons.get(0).setImg(ImageIO.read(getClass().getClassLoader().getResource("Play.png")));
			buttons.get(1).setImg(ImageIO.read(getClass().getClassLoader().getResource("Settings.png")));
			buttons.get(2).setImg(ImageIO.read(getClass().getClassLoader().getResource("Quit.png")));
		} catch (IOException e) {
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
		drawBackground(g);
		drawButtons(g);
	}

	private void drawBackground(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, GeneralSettings.screenWidth, GeneralSettings.screenHeight);
		g.drawImage(this.background, GeneralSettings.screenWidth/2 - 960, GeneralSettings.screenHeight/2 - 540, null);
	}

	private void drawButtons(Graphics2D g) {
		buttons.forEach((v) -> v.paint(g));
	}
}
