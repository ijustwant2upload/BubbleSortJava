package bubblesort;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Draw Frame({@link javax.swing.JFrame}).
 * <p>
 * Pausetime, Calls Simulation, Updates, ETC. 
 * <p>
 * Must have {@link bubble_sort.Cell} / {@link bubble_sort.Main}/ {@link bubble_sort.Simulation}
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

@SuppressWarnings("serial")
public class Frame extends JFrame {
	private Screen s;
	private Simulation sim;
	private float tslu;
	private static float PAUSETIME = 0.01f;

	public static float getPAUSETIME() {
		return PAUSETIME;
	}

	public static void setPAUSETIME(float pAUSETIME) {
		PAUSETIME = pAUSETIME;
	}

	public Frame() {
		String input2 = "0.01";
		try {
			PAUSETIME = Float.parseFloat(input2);
		} catch (Exception e) {
			System.exit(0);
		}
		setExtendedState(MAXIMIZED_BOTH);
	}

	public void createScreen() {
		sim = new Simulation();
		addKeyListener(sim);
		addMouseListener(sim);
		addMouseMotionListener(sim);
		s = new Screen();
		s.setBounds(0, 0, Main.width, Main.height);
		add(s);
	}

	public void update(float tslf) {
		tslu += tslf;
		if (tslu > PAUSETIME) {
			sim.update();
			tslu = 0;
		}
	}

	public void repaint() {
		s.repaint();
	}

	private class Screen extends JLabel {
		public void paintComponent(Graphics g) {
			super.paintComponents(g);
			sim.draw(g);
		}
	}
}
