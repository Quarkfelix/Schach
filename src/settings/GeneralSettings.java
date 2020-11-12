package settings;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class GeneralSettings {
	public static int Monitor = 1;
	public static int screenWidth = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[GeneralSettings.Monitor].getDisplayMode().getWidth();
	public static int screenHeight = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[GeneralSettings.Monitor].getDisplayMode().getHeight();
	

}
