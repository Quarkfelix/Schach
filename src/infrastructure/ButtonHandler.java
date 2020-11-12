package infrastructure;

import java.awt.event.KeyEvent;

public interface ButtonHandler {
	public void checkKeyInput(KeyEvent key);
	public void checkMouseInput(int x, int y);
}
