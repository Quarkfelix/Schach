package game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import libary.Button;

public class GameFieldButtonHandler implements ButtonHandler {
	private Button[][] field;
	private Point markedButton = new Point(500, 0);
	GameField gf;

//Constructor ------------------------------------------------------------------------------------------
	public GameFieldButtonHandler(GameField gf, Button[][] field) {
		this.field = field;
		this.gf = gf;
	}

//methods ----------------------------------------------------------------------------------------------
	@Override
	public void checkKeyInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;

		default:
			break;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j].contains(x, y)) {
					if (field[i][j].getText().equals("")) {
						System.out.println("leeres feld");
						if (positionOK(i, j) && markedButton.x != 500) {
							gf.setMoveStone(markedButton.x, markedButton.y, i, j);
							markedButton.setLocation(500, 0);
							gf.removeMark();
							gf.switchSides();
						}
					} else if (field[i][j].getText().substring(1, 2).equals(gf.activePlayer) && markedButton.x == 500) {
						System.out.println("marked field");
						gf.markStone(i, j);
						markedButton.setLocation(i, j);
					} else if (positionOK(i, j)) {
						gf.setMoveStone(markedButton.x, markedButton.y, i, j);
						markedButton.setLocation(500, 0);
						gf.removeMark();
						gf.switchSides();
					} 
				}
			}
		}
	}

	private boolean positionOK(int x, int y) {
		if (field[x][y].getText().equals("")) { // leeres feld
			// nur steinspezifische sachen prüfen
		} else {
			if (field[x][y].getText().substring(1, 2).equals(gf.activePlayer)) { // return false wenn ziel auf eigenem
																					// feld
				return false;
			}
		}

		int oldx = markedButton.x;
		int oldy = markedButton.y;
		// steinspezifisch
		switch (field[oldx][oldy].getText().substring(0, 1)) {
		case "T":
			// ziel muss auf einer linie liegen und es darf nichts dazwischen sein
			if (x != oldx && y != oldy) {
				return false;
			} else {
				if (oldy < y) {
					for (int i = oldy + 1; i < y; i++) {
						if (!field[oldx][i].getText().equals("")) {
							return false;
						}
					}
				} else if (oldy > y) {
					for (int i = oldy - 1; i > y; i--) {
						if (!field[oldx][i].getText().equals("")) {
							return false;
						}
					}
				} else if (oldx < x) {
					for (int i = oldx + 1; i < x; i++) {
						if (!field[i][oldy].getText().equals("")) {
							return false;
						}
					}
				} else if (oldx > x) {
					for (int i = oldx - 1; i > x; i--) {
						if (!field[i][oldy].getText().equals("")) {
							return false;
						}
					}
				}
				return true;
			}
			
		case "S" :
			return true;

		case "L":
			// ziel muss diagonal sein und auf diagonaler linie darf nichts sein
			if (Math.abs(oldx - x) == Math.abs(oldy - y)) {
				if (oldy < y && oldx < x) {
					int j = oldx + 1;
					for (int i = oldy + 1; i < y; i++) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j++;
					}
				} else if (oldy > y && oldx < x) {
					int j = oldx + 1;
					for (int i = oldy - 1; i > y; i--) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j++;
					}
				} else if (oldy < y && oldx > x) {
					int j = oldx - 1;
					for (int i = oldy + 1; i < y; i++) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j--;
					}
				} else if (oldy > y && oldx > x) {
					int j = oldx - 1;
					for (int i = oldy - 1; i > y; i--) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j--;
					}
				}
				return true;
			} else {
				return false;
			}
			
		case "K":
			if(Math.abs(oldx - x) > 1 | Math.abs(oldy - y) > 1) {
				return false;
			} else {
				return true;
			}
			
		case "D":
			//mix aus läufer und turm
			if(oldx == x || oldy == y) {
				if (x != oldx && y != oldy) {
					return false;
				} else {
					if (oldy < y) {
						for (int i = oldy + 1; i < y; i++) {
							if (!field[oldx][i].getText().equals("")) {
								return false;
							}
						}
					} else if (oldy > y) {
						for (int i = oldy - 1; i > y; i--) {
							if (!field[oldx][i].getText().equals("")) {
								return false;
							}
						}
					} else if (oldx < x) {
						for (int i = oldx + 1; i < x; i++) {
							if (!field[i][oldy].getText().equals("")) {
								return false;
							}
						}
					} else if (oldx > x) {
						for (int i = oldx - 1; i > x; i--) {
							if (!field[i][oldy].getText().equals("")) {
								return false;
							}
						}
					}
					return true;
				}
			}
			if (Math.abs(oldx - x) == Math.abs(oldy - y)) {
				if (oldy < y && oldx < x) {
					int j = oldx + 1;
					for (int i = oldy + 1; i < y; i++) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j++;
					}
				} else if (oldy > y && oldx < x) {
					int j = oldx + 1;
					for (int i = oldy - 1; i > y; i--) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j++;
					}
				} else if (oldy < y && oldx > x) {
					int j = oldx - 1;
					for (int i = oldy + 1; i < y; i++) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j--;
					}
				} else if (oldy > y && oldx > x) {
					int j = oldx - 1;
					for (int i = oldy - 1; i > y; i--) {
						if (!field[j][i].getText().equals("")) {
							return false;
						}
						j--;
					}
				}
				return true;
			} else {
				return false;
			}

		default:
			return true;
		}
	}

//getter-setter ----------------------------------------------------------------------------------------

//paint ------------------------------------------------------------------------------------------------

}
