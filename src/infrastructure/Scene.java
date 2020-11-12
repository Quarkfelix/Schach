package infrastructure;

import java.awt.Graphics2D;

public interface Scene {
	String name = "null";
	
	public String getName();
	
	public ButtonHandler getButtonHandler();
	
	public void paint(Graphics2D g);
}
