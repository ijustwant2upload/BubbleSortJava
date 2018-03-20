package bubblesort;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * MouseListener, MouseMotionListener, KeyListener, Update Cells see- {@link bubble_sort.Cell}.
 * <p>
 * Generation countdown, Random Cell height, Fill Cells, ETC. 
 * <p>
 * Must have {@link bubble_sort.Cell} / {@link bubble_sort.Main}/ {@link bubble_sort.Frame}
 *
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

public class Simulation implements KeyListener, MouseMotionListener, MouseListener {
	private Cell[][] cells;
	private int width = Main.width / Cell.size;
	private int height = Main.height / Cell.size;
	private int generation = 0;
	private int button;
	private static boolean randomNum = false;
	public static boolean go = false;
	public static int reset = 0;

	public Simulation() {
		cells = new Cell[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				cells[x][y] = new Cell(x, y);
				cells[x][y].setAlive(false);
			}
		}
	}

	public void update() {
		if (go == true) {
			generation++;
			int[] rows = new int[width];
			if (randomNum == false) {
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height - 1; y++) {
						if (cells[x][y].isAlive() == true) {
							rows[x] += 1;
						}
					}
				}
			} else {
				for (int x = 0; x < width; x++) {
					int numba = (int) Math.round((Math.random() * height));
					rows[x] += numba;
					button = 1;
					mouseClickAction(x, numba-2);
				}
				randomNum = false;
			}
			System.out.println();
			for (int j = 0; j < rows.length; j++) {
				if (j != rows.length - 1) {
					if (rows[j] == rows[j + 1] || rows[j] < rows[j + 1]) {
						//
					} else if (rows[j] > rows[j + 1]) {
						for (int yy = 0; yy < height; yy++) {
							if (cells[j][yy] != null && cells[j + 1][yy] != null) {
								boolean temp = cells[j + 1][yy].isAlive();
								cells[j + 1][yy].setAlive(cells[j][yy].isAlive());
								cells[j][yy].setAlive(temp);
							}
						}
					}
				}
			}
		}
		if (reset > 0) {
			// Clear all
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					cells[x][y].setAlive(false);
				}
			}
			reset = 0;
		}
	}

	public void draw(Graphics g) {
		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				cells[xx][yy].draw(g);
			}
		}
		g.setColor(Color.black);
		g.setFont(new Font("SansSerif", Font.BOLD, 25));
		g.drawString("" + generation, (10 + 3), 10 + g.getFont().getSize());
		g.drawString("" + generation, (10 - 3), 10 + g.getFont().getSize());
		g.drawString("" + generation, 10, (10 + g.getFont().getSize() + 3));
		g.drawString("" + generation, 10, (10 + g.getFont().getSize() - 3));
		g.setColor(Color.yellow);
		g.drawString("" + generation, 10, 10 + g.getFont().getSize());
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			go = !go;
		}
		if (e.getKeyCode() == KeyEvent.VK_G) {
			boolean temp = go;
			Main.generateButton.doClick();
			go=temp;
		}
		
	}

	public void mouseDragged(MouseEvent e) {
		int mx = e.getX() / (Cell.size) - 1;
		int my = e.getY() / (Cell.size) - 6;
		mouseClickAction(mx, my);
	}

	public void mousePressed(MouseEvent e) {
		button = e.getButton();
		int mx = e.getX() / (Cell.size) - 1;
		int my = e.getY() / (Cell.size) - 6;
		mouseClickAction(mx, my);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
		button = -1;
	}

	private void mouseClickAction(int mx, int my) {
		if (button == 1) {
			mouseLeft(mx, my);
		} else if (button == 2) {
			mouseMiddle();
		} else if (button == 3) {
			mouseRight(mx, my);
		}
	}

	private void mouseLeft(int mx, int my) {
		try {
			for (int y = height - 1; y > my; y--) {
				if (go == true && randomNum == false) {
					if (cells[0][y] != null) {
						cells[0][y].setAlive(true);
					}
				} else {
					if (cells[mx][y] != null) {
						cells[mx][y].setAlive(true);
					}
				}
			}
		} catch (Exception e2) {
		}
	}

	private void mouseRight(int mx, int my) {
		my+=2;
		try {
			for (int y = 0; y < my; y++) {
				if (go == true && randomNum == false) {
					if (cells[width - 1][y] != null) {
						cells[width - 1][y].setAlive(false);
					}
				} else {
					if (cells[mx][y] != null) {
						cells[mx][y].setAlive(false);
					}
				}
			}
		} catch (Exception e2) {
		}
	}

	private void mouseMiddle() {
		try {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (cells[x][y] != null) {
						cells[x][y].setAlive(false);
					}
				}

			}
		} catch (Exception e2) {
		}
	}

	public static void setRandomNum(boolean randomNum_) {
		randomNum = randomNum_;
	}
}
