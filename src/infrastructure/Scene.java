package infrastructure;

import java.awt.Graphics2D;

public interface Scene {	
	public ButtonHandler getButtonHandler();
	
	public void paint(Graphics2D g);
}
