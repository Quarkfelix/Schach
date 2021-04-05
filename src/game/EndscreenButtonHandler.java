package game;

import java.awt.event.KeyEvent;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;

public class EndscreenButtonHandler implements ButtonHandler {
	private Button toMenuButton;

// ======================================== CONSTRUCTOR ========================================
	public EndscreenButtonHandler(Button toMenuButton) {
		this.toMenuButton = toMenuButton;
	}
// ======================================== RUN-METHOD =========================================	

// ======================================== METHODS ============================================
	@Override
	public void checkKeyInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			Main.sc.setSceneActive("mainmenu");
			break;
		default:
			break;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		if(toMenuButton.contains(x, y)) {
			Main.sc.setSceneActive("mainmenu");
			GameField game = (GameField) Main.sc.getScene("gamefield");
			game.reset();
		}
	}
// ======================================== GET/SET METHODS ====================================

// ======================================== PAINT-METHODS ======================================

}
