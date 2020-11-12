package infrastructure;

import java.awt.Graphics2D;
import java.util.HashMap;

import game.GameField;
import mainmenu.*;

public class SceneHandler {
	private HashMap<String, Scene> scenes = new HashMap<>();
	private String activeScene = "mainmenu";
	
//Constructor ------------------------------------------------------------------------------------------
	public SceneHandler() {
		setUpScenes();
	}
//methods ----------------------------------------------------------------------------------------------
	private void setUpScenes() {
		scenes.put("mainmenu", new MainMenu());
		scenes.put("settingspage", new SettingsPage());
		scenes.put("gamefield", new GameField());
	}
//getter-setter ----------------------------------------------------------------------------------------
	public boolean setSceneActive(String sceneName) {
		for(String s : scenes.keySet()) {
		    if(s == sceneName) {
		    	activeScene = sceneName;
		    	return true;
		    }
		}
		return false;		 	
	}
	
	public Scene getActiveScene() {
		return scenes.get(activeScene);
	}
	
//paint ------------------------------------------------------------------------------------------------
	public void paint(Graphics2D g) {
		scenes.get(activeScene).paint(g);
	}

}
