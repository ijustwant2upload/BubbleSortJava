package bubblesort;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Cell class, x, y, size, grid on off, alive, dead.
 * <p>
 * Can stand Alone.
 */
/*
 *
 * A little test programm.
 * Created on: PrakIT
 * Creation date: 19.03.2018
 * Author: Leo T.
 * Copyright (C) 2018 , PrakIT
 *
 */

public class Cell {
	private int x;
	private int y;
	private boolean alive;
	static int size = 10;
	static boolean grid = false;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public void draw(Graphics g) {
		if (grid == true) {
			g.setColor(Color.BLACK);
			g.drawRect(x * size, y * size, size, size);
			if (alive == true) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillRect(x * size + 1, y * size + 1, size - 1, size - 1);
		} else {
			if (alive) {
				g.setColor(Color.BLACK);
			} else {
				g.setColor(Color.WHITE);
			}
			g.fillRect(x * size, y * size, size, size);
		}
	}
}
