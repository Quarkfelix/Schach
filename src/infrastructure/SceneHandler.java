package infrastructure;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import game.Endscreen;
import game.GameField;
import game.Pausemenu;
import mainmenu.*;

public class SceneHandler {
	private HashMap<String, Scene> scenes = new HashMap<>();
	private ArrayList<String> sceneStack = new ArrayList<>();
	private String activeScene = "mainmenu";

// ======================================== CONSTRUCTOR ========================================

	public SceneHandler() {
		setUpScenes();
	}

// ======================================== RUN-METHOD =========================================

// ======================================== METHODS ============================================

	private void setUpScenes() {
		scenes.put("mainmenu", new MainMenu());
		scenes.put("settingspage", new SettingsPage());
		scenes.put("gamefield", new GameField());
		scenes.put("pausemenu", new Pausemenu());
		scenes.put("keybindings", new Keybindings());
		scenes.put("endscreen", new Endscreen());
	}

// ======================================== GET/SET METHODS ====================================

	public boolean setSceneActive(String sceneName) {
		for (String s : scenes.keySet()) {
			if (s == sceneName) {
				sceneStack.add(activeScene);
				activeScene = sceneName;
				scenes.get(s).onActivation();
				return true;
			}
		}
		return false;
	}

	public Scene getActiveScene() {
		return scenes.get(activeScene);
	}

	public Scene getScene(String scenename) {
		return scenes.get(scenename);
	}

	public String getlastScene() {
		return sceneStack.get(sceneStack.size() - 1);
	}
	
	public ArrayList<String> getSceneHistory() {
		return sceneStack;
	}

// ======================================== PAINT-METHODS ======================================

	public void paint(Graphics2D g) {
		scenes.get(activeScene).paint(g);
	}
}
