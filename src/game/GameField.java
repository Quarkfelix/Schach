package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;

import javax.imageio.ImageIO;

import infrastructure.ButtonHandler;
import infrastructure.Scene;
import libary.Button;
import libary.TextArea;
import libary.Textalign;
import settings.GameFieldSettings;
import settings.GeneralSettings;
import settings.MainMenuSettings;

public class GameField implements Scene {
	private Button[][] field = new Button[8][8];
	private TextArea player1;
	private TextArea player2;
	private Button skipTrackButton;
	private GameFieldButtonHandler buttonHandler;
	int x = 0;
	int y = 0;
	int width = 1000;
	int height = 1000;
	int markedStoneX = 500;
	int markedStoneY = 500;
	public String activePlayer = "B";

	// settings
	private Color backgroundColor;
	private Color buttonColor;
	private Color buttonBorderColor;
	private Color UIbuttonColor;

// ======================================== CONSTRUCTOR ========================================

	public GameField() {
		importSettings();
		setupUI();
		setupField();
		startingFormation();
		buttonHandler = new GameFieldButtonHandler(this, field, skipTrackButton);
	}

// ======================================== RUN-METHOD =========================================

// ======================================== METHODS ============================================

	private void importSettings() {
		backgroundColor = GameFieldSettings.backgroundColor;
		buttonColor = GameFieldSettings.buttonColor;
		buttonBorderColor = GameFieldSettings.buttonBorderColor;
		UIbuttonColor = GameFieldSettings.UIbuttonColor;
	}

	private void setupUI() {
		setupTextFields();
		setupButtons();
	}

	private void setupButtons() {
		x = (int) ((GeneralSettings.screenWidth - 1920) / 2);
		y = (int) ((GeneralSettings.screenHeight - 1080) / 2);
		int width = 150;
		int height = 50;

		skipTrackButton = new Button(x + 140, y + 300, width, height);
		skipTrackButton.setText("SKIP MUSIC");
		skipTrackButton.setColor(UIbuttonColor);
		skipTrackButton.setBorderColor(buttonBorderColor);
		skipTrackButton.setTextColor(Color.BLACK);
		skipTrackButton.setTextAlignment(Textalign.mittig);
		skipTrackButton.setCornerRadius(20);
		skipTrackButton.setTextFontSize(23);
	}

	private void setupTextFields() {
		x = (int) ((GeneralSettings.screenWidth - 1920) / 2);
		y = (int) ((GeneralSettings.screenHeight - 1080) / 2);

		int width = 250;
		int height = 90;
		player1 = new TextArea((int) (x + 100), (int) (y + 40), width, height);
		player2 = new TextArea((int) (x + 1570), (int) (y + 40), width, height);

		player1.setText("PLAYER1");
		player1.setThiccness(3);
		player1.setTextAlignment("zentriert");
		player1.setTextAlignmentVertical("center");
		player1.setBackgroundColor(new Color(20, 20, 20));
		player1.setTextColor(Color.WHITE);
		player1.setFramingColor(new Color(158, 0, 0));

		player2.setText("PLAYER2");
		player2.setThiccness(3);
		player2.setTextAlignment("zentriert");
		player2.setTextAlignmentVertical("center");
		player2.setBackgroundColor(new Color(20, 20, 20));
		player2.setTextColor(Color.WHITE);
		player2.setFramingColor(new Color(15, 15, 15));
	}

	public void setupField() {
		// positions
		x = (int) ((GeneralSettings.screenWidth - width) / 2);
		y = (int) ((GeneralSettings.screenHeight - height) / 2);
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[j][i] = new Button(x, y, width / field.length, height / field.length);
				x += width / field.length;
			}
			x = (int) ((GeneralSettings.screenWidth - width) / 2);
			y += height / field.length;
		}

		// design
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j].setColor(buttonColor);
				field[i][j].setBorderColor(buttonBorderColor);
				field[i][j].setTextActive(false);
			}
		}
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j <= 1; j++) {
				field[i][j].setTextColor(Color.BLACK);
			}
		}
		for (int i = 0; i < field.length; i++) {
			for (int j = 6; j < field.length; j++) {
				field[i][j].setTextColor(Color.WHITE);
			}
		}
	}

	public void startingFormation() {
		// TB -> Turm Black
		field[0][0].setText("TB");
		field[1][0].setText("SB");
		field[2][0].setText("LB");
		field[3][0].setText("KB");
		field[4][0].setText("DB");
		field[5][0].setText("LB");
		field[6][0].setText("SB");
		field[7][0].setText("TB");
		field[0][1].setText("BB");
		field[1][1].setText("BB");
		field[2][1].setText("BB");
		field[3][1].setText("BB");
		field[4][1].setText("BB");
		field[5][1].setText("BB");
		field[6][1].setText("BB");
		field[7][1].setText("BB");

		field[0][7].setText("TW");
		field[1][7].setText("SW");
		field[2][7].setText("LW");
		field[3][7].setText("KW");
		field[4][7].setText("DW");
		field[5][7].setText("LW");
		field[6][7].setText("SW");
		field[7][7].setText("TW");
		field[0][6].setText("BW");
		field[1][6].setText("BW");
		field[2][6].setText("BW");
		field[3][6].setText("BW");
		field[4][6].setText("BW");
		field[5][6].setText("BW");
		field[6][6].setText("BW");
		field[7][6].setText("BW");
		
		try {
			field[0][0].setImg(ImageIO.read(getClass().getResource("ZturmSchwarz.png")));
			field[1][0].setImg(ImageIO.read(getClass().getResource("ZspringerSchwarz.png")));
			field[2][0].setImg(ImageIO.read(getClass().getResource("ZlauferSchwarz.png")));
			field[3][0].setImg(ImageIO.read(getClass().getResource("ZkoenigSchwarz.png")));
			field[4][0].setImg(ImageIO.read(getClass().getResource("ZdameSchwarz.png")));
			field[5][0].setImg(ImageIO.read(getClass().getResource("ZlauferSchwarz.png")));
			field[6][0].setImg(ImageIO.read(getClass().getResource("ZspringerSchwarz.png")));
			field[7][0].setImg(ImageIO.read(getClass().getResource("ZturmSchwarz.png")));
			field[0][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[1][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[2][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[3][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[4][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[5][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[6][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			field[7][1].setImg(ImageIO.read(getClass().getResource("ZbauerSchwarz.png")));
			
			field[0][7].setImg(ImageIO.read(getClass().getResource("ZturmWeiﬂ.png")));
			field[1][7].setImg(ImageIO.read(getClass().getResource("ZspringerWeiﬂ.png")));
			field[2][7].setImg(ImageIO.read(getClass().getResource("ZlauferWeiﬂ.png")));
			field[3][7].setImg(ImageIO.read(getClass().getResource("ZkoenigWeiﬂ.png")));
			field[4][7].setImg(ImageIO.read(getClass().getResource("ZdameWeiﬂ.png")));
			field[5][7].setImg(ImageIO.read(getClass().getResource("ZlauferWeiﬂ.png")));
			field[6][7].setImg(ImageIO.read(getClass().getResource("ZspringerWeiﬂ.png")));
			field[7][7].setImg(ImageIO.read(getClass().getResource("ZturmWeiﬂ.png")));
			field[0][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[1][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[2][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[3][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[4][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[5][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[6][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
			field[7][6].setImg(ImageIO.read(getClass().getResource("ZbauerWeiﬂ.png")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchSides() {
		if (activePlayer.equals("B")) {
			activePlayer = "W";
			player2.setFramingColor(new Color(158, 0, 0));
			player1.setFramingColor(new Color(15, 15, 15));
		} else {
			activePlayer = "B";
			player1.setFramingColor(new Color(158, 0, 0));
			player2.setFramingColor(new Color(15, 15, 15));
		}
	}

	public void markStone(int x, int y) {
		markedStoneX = field[x][y].getX();
		markedStoneY = field[x][y].getY();
	}

// ======================================== GET/SET METHODS ====================================

	@Override
	public ButtonHandler getButtonHandler() {
		return buttonHandler;
	}

	public void setMoveStone(int oldX, int oldY, int newX, int newY) {
		field[newX][newY].setText(field[oldX][oldY].getText());
		field[newX][newY].setImg(field[oldX][oldY].getImage());
		if (activePlayer.equals("B")) {
			field[newX][newY].setTextColor(Color.BLACK);
		} else {
			field[newX][newY].setTextColor(Color.WHITE);
		}
		field[oldX][oldY].setText("");
		field[oldX][oldY].setImg(null);
	}

	public void removeMark() {
		markedStoneX = 500;
		markedStoneY = 500;
	}

// ======================================== PAINT-METHODS ======================================

	@Override
	public void paint(Graphics2D g) {
		drawBackground(g);
		drawButtons(g);
		if (markedStoneX != 500) {
			drawMarker(g);
		}
		drawTextAreas(g);
		drawUIButtons(g);
	}

	private void drawBackground(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, GeneralSettings.screenWidth, GeneralSettings.screenHeight);
	}

	private void drawButtons(Graphics2D g) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				field[i][j].paint(g);
			}
		}
	}

	private void drawMarker(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawOval(markedStoneX, markedStoneY, width / field.length, height / field.length);
	}

	private void drawUIButtons(Graphics2D g) {
		skipTrackButton.paint(g);
	}

	private void drawTextAreas(Graphics2D g) {
		player1.paint(g);
		player2.paint(g);
	}
}
