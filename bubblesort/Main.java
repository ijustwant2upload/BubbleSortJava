package bubblesort;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Main.
 * <p>
 * Starts Everything from: {@link bubble_sort.Cell} / {@link bubble_sort.Simulation}/ {@link bubble_sort.Frame}.
 * <p>
 * Must have {@link bubble_sort.Cell} / {@link bubble_sort.Simulation}/ {@link bubble_sort.Frame}.
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

public class Main {
	static int width, height;
	static JMenuItem generateButton;
	static JMenuItem PauseTimeSet;
	static JMenuItem startButton;
	static JMenuItem stopButton;
	static JMenuItem clearButton;
	static JRadioButtonMenuItem outlineRadioButton;

	public static void main(String[] args) {
		Frame f = new Frame();
		JMenuBar MenuBar = new JMenuBar();
		JMenu Menu = new JMenu("Menu");
		outlineRadioButton = new JRadioButtonMenuItem("Outline");
		clearButton = new JMenuItem("Clear");
		startButton = new JMenuItem("Start");
		stopButton = new JMenuItem("Stop");
		generateButton = new JMenuItem("Generate");
		PauseTimeSet = new JMenuItem("Set Pausetime");
		PauseTimeSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float eingabe = Frame.getPAUSETIME();
					String name = JOptionPane.showInputDialog(null, "Pausetime?", eingabe);
					if (name != null) {
						eingabe = Float.parseFloat(name);
					}
					Frame.setPAUSETIME(eingabe);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButton.doClick();
				Simulation.setRandomNum(true);
				stopButton.doClick();
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulation.reset = 1;
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulation.go = true;
			}
		});
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Simulation.go = false;
			}
		});
		outlineRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cell.grid = !Cell.grid;
			}
		});
		Menu.add(startButton);
		Menu.add(stopButton);
		Menu.addSeparator();
		Menu.add(clearButton);
		Menu.addSeparator();
		Menu.add(outlineRadioButton);
		Menu.addSeparator();
		Menu.add(generateButton);
		Menu.addSeparator();
		Menu.add(PauseTimeSet);
		MenuBar.add(Menu);
		f.setJMenuBar(MenuBar);
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
		f.setResizable(true);
		f.setMinimumSize(new Dimension(400, 200));
		f.setTitle("BubbleSort");
		width = f.getWidth() - 30;
		height = f.getHeight() - 70;
		f.createScreen();
		long lastFrame = System.currentTimeMillis();
		while (true) {
			long thisFrame = System.currentTimeMillis();
			float tslf = (float) ((thisFrame - lastFrame) / 1000.0);
			lastFrame = thisFrame;
			f.update(tslf);
			f.repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
