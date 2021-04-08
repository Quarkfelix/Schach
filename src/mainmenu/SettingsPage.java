package mainmenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import infrastructure.ButtonHandler;
import infrastructure.Scene;
import libary.Button;
import settingsClasses.GeneralSettings;
import settingsClasses.MainMenuSettings;
import settingsClasses.SettingsPageSettings;

public class SettingsPage implements Scene{
	private String name = "settings";
	private ArrayList<Button> buttons = new ArrayList<>();
	public static SettingsButtonHandler buttonHandler;
	
	// Settings
	private Color backgroundColor;
	private Color buttonColor;
	private Color buttonBorderColor;
	private Color buttonTextColor;
	
// ======================================== CONSTRUCTOR ========================================
	
	public SettingsPage() {
		importSettings();
		addButtons();
		setUpButtons();
		buttonHandler = new SettingsButtonHandler(buttons);
	}
	
// ======================================== RUN-METHOD =========================================
	
// ======================================== METHODS ============================================
	@Override
	public void onActivation() {
		// TODO Auto-generated method stub
		
	}
	
	private void importSettings() {
		backgroundColor = SettingsPageSettings.backgroundColor;
		buttonColor = SettingsPageSettings.buttonColor;
		buttonBorderColor = SettingsPageSettings.buttonBorderColor;
		buttonTextColor = SettingsPageSettings.buttonTextColor;
	}
	
	private void addButtons() {
		buttons.add(new Button(0,0,0,0));
		buttons.add(new Button(0,0,0,0));
		buttons.add(new Button(0,0,0,0));
	}
	
	private void setUpButtons() {
		int width = 300;
		int height = 100;
		int y = (int) ((GeneralSettings.screenHeight-height)/2);
		int x = (int) ((GeneralSettings.screenWidth-width)/2);
		
		//switch monitor
		buttons.get(0).setY(y/2);
		buttons.get(0).setText("SWITCH MONITOR");
		
		//mute
		buttons.get(1).setY(y);
		buttons.get(1).setText("KEYBINDS");
		
		//back
		buttons.get(2).setY(y + y/2);
		buttons.get(2).setText("BACK");
		
		//general
		for (Button button : buttons) {
			button.setX(x);
			button.setWidth(width);
			button.setHeight(height);
			button.setBorderActive(false);
			button.setTextActive(false);
			
			button.setTextColor(buttonTextColor);
			button.setBorderColor(buttonBorderColor);
			button.setColor(buttonColor);
		}	
		

		try {
//			buttons.get(0).setImg(ImageIO.read(getClass().getClassLoader().getResource("Resume.png")));
			buttons.get(1).setImg(ImageIO.read(getClass().getClassLoader().getResource("Keybinds.png")));
			buttons.get(2).setImg(ImageIO.read(getClass().getClassLoader().getResource("Back.png")));
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
	}
	
	private void drawButtons(Graphics2D g) {
		buttons.forEach((v) -> v.paint(g));
	}
}
