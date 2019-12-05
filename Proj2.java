// File: Proj2.java
// CS 360 - Fall 2019 - Watts
// Shape Sorter Game App
// Novmber 2019 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the implementation of a small
GUI application that uses the Shape class hierarchy
and animates the shapes.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class Proj2
{
	Proj2 ()
	{
		System.out.println ("In Proj2 constructor");
		JFrame frame = new JFrame ("Shape Sorter Game");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setSize (1250,800);
		frame.setResizable (true);
		//frame.setLocation (500, 75);// desktop settings
		frame.setLocation (200, 25);// desktop settings
		Data data = new Data (frame);
		Container pane = frame.getContentPane();
		GameBoard gameboard = new GameBoard (data);
		data.board = gameboard;
		data.initLevel (0);

		HUD hud = new HUD(data);
		data.hud = hud;

		pane.setLayout (new BorderLayout());
		pane.add (gameboard, BorderLayout.CENTER);
		pane.add(hud, BorderLayout.NORTH);
		frame.setVisible (true);
	}

	public static void main (String[] args)
	{
		Proj2 proj2 = new Proj2 ();
	}
}
