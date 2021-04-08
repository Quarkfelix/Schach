package infrastructure;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import game.Endscreen;
import game.GameField;
import game.Pausemenu;
import mainmenu.*;
import settings.Keybindings;

public class SceneHandler {
	private HashMap<Scenes, Scene> scenes = new HashMap<>();
	private ArrayList<Scenes> sceneStack = new ArrayList<>();
	private Scenes activeScene = Scenes.mainmenu;

// ======================================== CONSTRUCTOR ========================================

	public SceneHandler() {
		setUpScenes();
	}

// ======================================== RUN-METHOD =========================================

// ======================================== METHODS ============================================

	private void setUpScenes() {
		scenes.put(Scenes.mainmenu, new MainMenu());
		scenes.put(Scenes.settingspage, new SettingsPage());
		scenes.put(Scenes.gamefield, new GameField());
		scenes.put(Scenes.pausemenu, new Pausemenu());
		scenes.put(Scenes.keybindings, new Keybindings());
		scenes.put(Scenes.endscreen, new Endscreen());
	}

// ======================================== GET/SET METHODS ====================================

	public boolean setSceneActive(Scenes sceneName) {
		for (Scenes s : scenes.keySet()) {
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

	public Scene getScene(Scenes scene) {
		return scenes.get(scene);
	}

	public Scenes getlastScene() {
		return sceneStack.get(sceneStack.size() - 1);
	}
	
	public ArrayList<Scenes> getSceneHistory() {
		return sceneStack;
	}

// ======================================== PAINT-METHODS ======================================

	public void paint(Graphics2D g) {
		scenes.get(activeScene).paint(g);
	}
}
