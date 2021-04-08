package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scene;
import infrastructure.Scenes;
import libary.Button;
import libary.TextArea;
import settingsClasses.EndscreenSettings;
import settingsClasses.GeneralSettings;
import settingsClasses.PausemenuSettings;

public class Endscreen implements Scene {
	private Button toMenuButton;
	private EndscreenButtonHandler buttonHandler;
	private HashMap<String, TextArea> infoAreas = new HashMap<>();
	private HashMap<String, TextArea> whiteData = new HashMap<>();
	private HashMap<String, TextArea> blackData = new HashMap<>();
	private Image winnerIcon = null;
	private String winner = "no";
	private Color backgroundColor;
	private Color buttonColor;
	private Color buttonBorderColor;
	private Color buttonTextColor;
	
	
// ======================================== CONSTRUCTOR ========================================
	public Endscreen() {
		importSettings();
		setUpButtons();
		setUpTextAreas();
		setUpWinnerIcon();
		buttonHandler = new EndscreenButtonHandler(toMenuButton);
	}
// ======================================== RUN-METHOD =========================================	

// ======================================== METHODS ============================================
	@Override
	public void onActivation() {
		GameField gf = (GameField)Main.sc.getScene(Scenes.gamefield);
		//anzahl zuege
		whiteData.get("Anzahl Zuege").setText(""+gf.getMoveCount("W"));
		blackData.get("Anzahl Zuege").setText(""+gf.getMoveCount("B"));
		//uebrige figuren
		whiteData.get("Figuren Uebrig").setText(""+gf.getWhiteAndBlackStones().x);
		blackData.get("Figuren Uebrig").setText(""+gf.getWhiteAndBlackStones().y);
		//geschlagene figuren
		whiteData.get("Figuren Geschlagen").setText(""+ (16-gf.getWhiteAndBlackStones().y));
		blackData.get("Figuren Geschlagen").setText(""+(16-gf.getWhiteAndBlackStones().x));
		//zeit vergangen
		whiteData.get("Zeit Vergangen").setText(""+(gf.getTotalMoveTime()[0]+gf.getTotalMoveTime()[1]));
		blackData.get("Zeit Vergangen").setText(""+(gf.getTotalMoveTime()[0]+gf.getTotalMoveTime()[1]));
		//zeit am Zug
		whiteData.get("Zeit am Zug").setText(""+gf.getTotalMoveTime()[0]);
		blackData.get("Zeit am Zug").setText(""+gf.getTotalMoveTime()[1]);
		//winner
		winner = gf.getWinner();
		winner = "white";
	}

	private void importSettings() {
		backgroundColor = EndscreenSettings.backgroundColor;
		buttonColor = EndscreenSettings.buttonColor;
		buttonBorderColor = EndscreenSettings.buttonBorderColor;
		buttonTextColor = EndscreenSettings.buttonTextColor;
	}

	private void setUpWinnerIcon() {
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("winner.gif"));
		this.winnerIcon = icon.getImage();
	}
	
	private void setUpButtons() {
		int width = 250;
		int height = 70;
		int y = (int) ((GeneralSettings.screenHeight - height) / 2);
		int x = (int) ((GeneralSettings.screenWidth - width) / 2);

		// back to menu
		toMenuButton = new Button(x, y - 450, width, height);
		toMenuButton.setColor(this.buttonColor);
		toMenuButton.setBorderColor(this.buttonBorderColor);
		toMenuButton.setTextColor(this.buttonTextColor);
		toMenuButton.setText("Back to Menu");
		toMenuButton.setTextFontSize(30);
	}

	private void setUpTextAreas() {
		int width = 1150;
		int height = 80;
		int y = (int) ((GeneralSettings.screenHeight - height) / 2);
		int x = (int) ((GeneralSettings.screenWidth - width) / 2);

		// infonames
		int ydiff = 130;
		int yshift = 250;
		infoAreas.put("Anzahl Zuege", new TextArea(x, y - yshift + ydiff, width, height, "Anzahl Züge"));
		infoAreas.put("Figuren Uebrig", new TextArea(x, y - yshift + ydiff * 2, width, height, "Figuren Übrig"));
		infoAreas.put("Figuren Geschlagen", new TextArea(x, y - yshift + ydiff * 3, width, height, "Figuren Geschlagen"));
		infoAreas.put("Zeit Vergangen", new TextArea(x, y - yshift + ydiff * 4, width, height, "Zeit Vergangen"));
		infoAreas.put("Zeit am Zug", new TextArea(x, y - yshift + ydiff * 5, width, height, "Zeit am Zug"));
		infoAreas.forEach((k, v) -> {
			v.setTextAlignment("zentriert");
			v.setTextAlignmentVertical("center");
			v.setBackgroundActive(true);
			v.setBackgroundColor(new Color(20,20,20, 220));
			v.setOutlineActive(false);
			v.setTextColor(Color.WHITE);
		});

		width = 250;
		height = 80;
		y = (int) ((GeneralSettings.screenHeight - height) / 2);
		x = (int) ((GeneralSettings.screenWidth - width) / 2);
		
		// white data
		whiteData.put("name", new TextArea(x - 500, y - 300, width, height, "WHITE"));
		whiteData.put("Anzahl Zuege", new TextArea(x - 500, y - yshift + ydiff, width, height, "---"));
		whiteData.put("Figuren Uebrig", new TextArea(x - 500, y - yshift + ydiff * 2, width, height, "---"));
		whiteData.put("Figuren Geschlagen", new TextArea(x - 500, y - yshift + ydiff * 3, width, height, "---"));
		whiteData.put("Zeit Vergangen", new TextArea(x - 500, y - yshift + ydiff * 4, width, height, "---"));
		whiteData.put("Zeit am Zug", new TextArea(x - 500, y - yshift + ydiff * 5, width, height, "---"));
		// general optics
		whiteData.forEach((k, v) -> {
			v.setTextAlignment("zentriert");
			v.setTextAlignmentVertical("center");
			v.setBackgroundActive(false);
			v.setOutlineActive(false);
			v.setTextColor(Color.WHITE);
		});
		// optic special cases
		whiteData.get("name").setThiccness(3);
		whiteData.get("name").setTextAlignment("zentriert");
		whiteData.get("name").setTextAlignmentVertical("center");
		whiteData.get("name").setBackgroundColor(new Color(20, 20, 20));
		whiteData.get("name").setTextColor(Color.WHITE);
		whiteData.get("name").setFramingColor(new Color(158, 0, 0));
		whiteData.get("name").setOutlineActive(false);

		// blackData
		blackData.put("name", new TextArea(x + 500, y - 300, width, height, "BLACK"));
		blackData.put("Anzahl Zuege", new TextArea(x + 500, y - yshift + ydiff, width, height, "---"));
		blackData.put("Figuren Uebrig", new TextArea(x + 500, y - yshift + ydiff * 2, width, height, "---"));
		blackData.put("Figuren Geschlagen", new TextArea(x + 500, y - yshift + ydiff * 3, width, height, "---"));
		blackData.put("Zeit Vergangen", new TextArea(x + 500, y - yshift + ydiff * 4, width, height, "---"));
		blackData.put("Zeit am Zug", new TextArea(x + 500, y - yshift + ydiff * 5, width, height, "---"));
		// general optics
		blackData.forEach((k, v) -> {
			v.setTextAlignment("zentriert");
			v.setTextAlignmentVertical("center");
			v.setBackgroundActive(false);
			v.setOutlineActive(false);
			v.setTextColor(Color.WHITE);
		});
		// optic special cases
		blackData.get("name").setThiccness(3);
		blackData.get("name").setTextAlignment("zentriert");
		blackData.get("name").setTextAlignmentVertical("center");
		blackData.get("name").setBackgroundColor(new Color(20, 20, 20));
		blackData.get("name").setTextColor(Color.WHITE);
		blackData.get("name").setFramingColor(new Color(158, 0, 0));
		blackData.get("name").setOutlineActive(false);
	}

// ======================================== GET/SET METHODS ====================================
	@Override
	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}

// ======================================== PAINT-METHODS ======================================
	public void paint(Graphics2D g) {
		drawBackground(g);
		drawButtons(g);
		drawTextAreas(g);
		drawIcon(g);
	}

	private void drawBackground(Graphics2D g) {
		Main.sc.getScene(Scenes.gamefield).paint(g);
		g.setColor(this.backgroundColor);
		g.fillRect(0, 0, GeneralSettings.screenWidth, GeneralSettings.screenHeight);
	}

	private void drawButtons(Graphics2D g) {
		this.toMenuButton.paint(g);
	}

	private void drawTextAreas(Graphics2D g) {
		infoAreas.forEach((k, v) -> {
			v.paint(g);
		});
		whiteData.forEach((k, v) -> {
			v.paint(g);
		});
		blackData.forEach((k, v) -> {
			v.paint(g);
		});
	}
	
	private void drawIcon(Graphics2D g) {
		if(winner.equals("white")) {
			g.drawImage(this.winnerIcon, (GeneralSettings.screenWidth/2-(310/2))-500, 40, 310, 155, null);
		} else if(winner.equals("black")) {
			g.drawImage(this.winnerIcon, (GeneralSettings.screenWidth/2-(310/2))+500, 40, 310, 155, null);
		}
		
	}
}



