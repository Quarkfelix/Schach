package io;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import infrastructure.Main;
import infrastructure.SceneHandler;
import mainmenu.MainMenu;

public class KeyHandler implements KeyListener{

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Main.sc.getActiveScene().getButtonHandler().checkKeyInput(e);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
