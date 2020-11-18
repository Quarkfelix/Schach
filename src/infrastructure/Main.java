package infrastructure;

import libary.MusicController;

public class Main {
	public static GUI gui;
	public static SceneHandler sc;
	public static MusicController mc;
	
	public static void main(String[] args) {
		//create MusicController
		mc = new MusicController();
		loadBackgroundMusic();
		loadSoundEffects();
		//create and start sceneHandler
		sc = new SceneHandler();
		//create and start Graphical User Interface
		gui = new GUI();
	}
	
	private static void loadBackgroundMusic() {
		String[] songs = new String[8];
		songs[0] = "starwarsLoFiClip1.wav";
		songs[1] = "starwarsLoFiClip2.wav";
		songs[2] = "starwarsLoFiClip3.wav";
		songs[3] = "starwarsLoFiClip4.wav";
		songs[4] = "starwarsLoFiClip5.wav";
		songs[5] = "starwarsLoFiClip6.wav";
		songs[6] = "starwarsLoFiClip7.wav";
		songs[7] = "starwarsLoFiClip8.wav";
		mc.newPlaylist("background", songs);
		mc.play("background", true, false);
	}
	
	private static void loadSoundEffects() {
		String[] songs = new String[3];
		songs[0] = "attackSound.wav";
		songs[1] = "enterSound.wav";
		songs[2] = "moveSound.wav";
		mc.newPlaylist("effects", songs);
	}
}
