package game;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import infrastructure.ButtonHandler;
import infrastructure.Main;
import infrastructure.Scenes;
import libary.Button;

public class GameFieldButtonHandler implements ButtonHandler {
	private Button[][] field;
	private Button skipTrackButton;
	private Point markedButton = new Point(500, 0);
	GameField gf;

//Constructor ------------------------------------------------------------------------------------------
	public GameFieldButtonHandler(GameField gf, Button[][] field, Button skipTrackButton) {
		this.field = field;
		this.gf = gf;
		this.skipTrackButton = skipTrackButton;
	}

//methods ----------------------------------------------------------------------------------------------
	@Override
	public void checkKeyInput(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			Main.sc.setSceneActive(Scenes.pausemenu);
			break;
		default:
			break;
		}
	}

	@Override
	public void checkMouseInput(int x, int y) {
		// check UI
		if (skipTrackButton.contains(x, y)) {
			Main.mc.play("effects", 1, false, true);
			Main.mc.nextSong("background");
			return;
		}

		// check schachfeld
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j].contains(x, y)) {
					if (field[i][j].getText().equals("")) {
						// empty field
						if (markedButton.x != 500) { // move
							if (positionOK(i, j)) {
								Main.mc.play("effects", 4, false, true);
								gf.setMoveStone(markedButton.x, markedButton.y, i, j);
								markedButton.setLocation(500, 0);
								gf.removeMark();
								gf.switchSides();
							}
						}
					} else if (field[i][j].getText().substring(1, 2).equals(gf.activePlayer)) { // select stone
						Main.mc.play("effects", 3, false, true);
						gf.markStone(i, j);
						markedButton.setLocation(i, j);
					} else if (markedButton.x != 500) { // move
						if (positionOK(i, j)) {
							Main.mc.play("effects", 4, false, true);
							gf.setMoveStone(markedButton.x, markedButton.y, i, j);
							markedButton.setLocation(500, 0);
							gf.removeMark();
							gf.switchSides();
							checkWinCondition();
						}
					}
				}
			}
		}
	}

	private boolean checkWinCondition() {
		boolean blackKing = false;
		boolean whiteKing = false;

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j].getText().equals("KB")) {
					blackKing = true;
				}
				if (field[i][j].getText().equals("KW")) {
					whiteKing = true;
				}
			}
		}
		if (!blackKing && whiteKing) {
			gf.moveCountB++;
			gf.timeBlack = gf.timeBlack + (System.nanoTime() - gf.time0);
			gf.time0 = System.nanoTime();
			gf.winner = "white";
			Main.sc.setSceneActive(Scenes.endscreen);
		} else if (!whiteKing && blackKing) {
			gf.moveCountW++;
			gf.timeWhite = gf.timeWhite + (System.nanoTime() - gf.time0);
			gf.time0 = System.nanoTime();
			gf.winner = "black";
			Main.sc.setSceneActive(Scenes.endscreen);
		}
		return false;
	}

	// ist die logik hinter dem game.
	private boolean positionOK(int x, int y) {
		if (field[x][y].getText().equals("")) { // leeres feld
			// nur steinspezifische sachen prüfen
		} else {
			if (field[x][y].getText().substring(1, 2).equals(gf.activePlayer)) { // wenn auf eigenem feld
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

		case "S":
			if (oldx + 2 == x) {
				if (oldy - 1 == y) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
				if (oldy + 1 == y) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
			} else if (oldx - 2 == x) {
				if (oldy - 1 == y) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
				if (oldy + 1 == y) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
			} else if (oldy + 2 == y) {
				if (oldx - 1 == x) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
				if (oldx + 1 == x) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
			} else if (oldy - 2 == y) {
				if (oldx - 1 == x) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
				if (oldx + 1 == x) {
					if (field[x][y].getText().equals("") || !field[x][y].getText().substring(1).equals(gf.activePlayer))
						return true;
				}
			}
			return false;

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
			if (Math.abs(oldx - x) > 1 | Math.abs(oldy - y) > 1) {
				return false;
			} else {
				return true;
			}

		case "D":
			// mix aus läufer und turm
			if (oldx == x || oldy == y) {
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
		case "B":

			switch (field[oldx][oldy].getText().substring(1)) {
			case "B":
				if (Math.abs(x - oldx) == 1) {
					if (!field[x][y].getText().equals("")) {
						if (field[x][y].getText().substring(1).equals("W")) {
							if (oldy < y && y - oldy <= 1) {
								return true;
							}
						}
					}
				}

				if (Math.abs(x - oldx) == 0) {
					if (oldy == 1) {
						if (oldy < y && y - oldy <= 2) {
							if (field[x][y].getText().equals(""))
								return true;
						}
						return false;
					}
					if (oldy < y && y - oldy <= 1) {
						if (field[x][y].getText().equals(""))
							return true;
						return false;
					}
				}
				break;

			case "W":
				if (Math.abs(x - oldx) == 1) {
					if (!field[x][y].getText().equals("")) {
						if (field[x][y].getText().substring(1).equals("B")) {
							if (oldy > y && oldy - y <= 1) {
								return true;
							}
						}
					}
				}

				if (Math.abs(x - oldx) == 0) {
					if (oldy == 6) {
						if (oldy > y && oldy - y <= 2) {
							if (field[x][y].getText().equals(""))
								return true;
						}
						return false;
					}
					if (oldy > y && oldy - y <= 1) {
						if (field[x][y].getText().equals(""))
							return true;
						return false;
					}
				}
				break;

			default:
				break;
			}
			return false;

		default:
			return true;
		}
	}

//getter-setter ----------------------------------------------------------------------------------------

//paint ------------------------------------------------------------------------------------------------

}
