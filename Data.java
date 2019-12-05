// File: Data.java
// CS 360 -  Fall 2019 - Watts
// Frames and Animation Example
// November 2019 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the shared data items class.
*/

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class Data
{
	public JFrame game;
	public GameBoard board;
	public HUD hud;
	public ArrayList <Block> blocks = new ArrayList <Block> ();
	public ArrayList <Hole> holes = new ArrayList <Hole> ();
	public Block currentBlock = null;
	public int level;
	public int score;
	public int blocksToPlace;

	public Data (JFrame G)
	{
		game = G;
	}

	public void initLevel (int L)
	{
		level = L;
		switch (level)
		{
			case 0: initLevel0 (); break;
			case 1: initLevel1 (); break;
			case 2: initLeveL2 (); break;
		}
	}

	public void finishLevel ()
	{
		System.out.println ("Level complete! Your score is " + score);
	}

	public void initLevel0 ()
	{
		score = 0;
		blocksToPlace = 6;
		//creating holes
		Circle circle1 = new Circle(210, 400, 200, Color.BLACK); // possibly add 2 to make it a little bigger than shapes since its the hole
		Hole hole1 = new Hole(circle1);
		holes.add(hole1);
		Square square1 = new Square(110, 900, 200, Color.BLACK, 0);
		Hole hole2 = new Hole(square1);
		holes.add(hole2);
		Rectangle rectangle3 = new Rectangle(85, 135,  900, 400, Color.BLACK, 0);
		Hole hole3 = new Hole(rectangle3);
		holes.add(hole3);
		Equilateral equilateral4 = new Equilateral(135, 400, 400, Color.BLACK,0);
		Hole hole4 = new Hole(equilateral4);
		holes.add(hole4);
		Scalene scalene5 = new Scalene(140, 159, 180, 400, 600, Color.BLACK,0);
		Hole hole5 = new Hole(scalene5);
		holes.add(hole5);
		Right right5 = new Right(130, 157, 900, 600, Color.BLACK,0);
		Hole hole6 = new Hole(right5);
		holes.add(hole6);


		//creating blocks
		Circle circleA1 = new Circle(200, 150, 200, Color.PINK);
		Block block1 = new Block(circleA1, 100);
		blocks.add(block1);
		Square squareA2 = new Square(100, 150, 400, Color.RED, 0);
		Block block2 = new Block(squareA2, 10);
		blocks.add(block2);
		Rectangle rectangleA3 = new Rectangle(75, 125,  150, 600, Color.BLUE, 0);
		Block block3 = new Block(rectangleA3, 20);
		blocks.add(block3);
		Equilateral equilateralA4 = new Equilateral(125, 1100, 200, Color.GRAY,0);
		Block block4 = new Block(equilateralA4, 40);
		blocks.add(block4);
		Scalene scaleneA5 = new Scalene(130, 150, 170, 1100, 400, Color.GREEN,0);
		Block block5 = new Block(scaleneA5, 30);
		blocks.add(block5);
		Right rightA5 = new Right(120, 145, 1100, 600, Color.ORANGE,0);
		Block block6 = new Block(rightA5, 50);
		blocks.add(block6);
	}

	public void initLevel1 ()
	{
		score = 0;
		blocksToPlace = 6;
	}

	public void initLeveL2()
	{
		score = 0;
		blocksToPlace = 6;
	}


	void placeBlockOnBoard (Block block)
	{
		block.onBoard = true;
		block.shape.isSelected = true;
		currentBlock = block;
		board.repaint ();
	}

	void removeBlockFromBoard (Block block)
	{
		block.onBoard = false;
		block.shape.isSelected = false;
		currentBlock = null;
		board.repaint ();
	}

	boolean checkIfBlockInHole (Block block)
	{
		int X = block.shape.getCenterX();
		int Y = block.shape.getCenterY();
		Hole hole = null;
		for (int i = 0; hole == null && i < holes.size(); i++)
			if (holes.get(i).isIn (X, Y))
				hole = holes.get(i);
		if (hole == null || hole.filled)
			return false;
		boolean contained = block.containedInHole (hole);
		if (contained)
			placeBlockInHole (block, hole);
		return contained;
	}

	void placeBlockInHole (Block block, Hole hole)
	{
		block.onBoard = false;
		block.shape.isSelected = false;
		block.placed = true;
		hole.filled = true;
		hole.filledWith = block;
			block.moveTo (hole.shape.getCenterX(), hole.shape.getCenterY());
		score += block.getPoints();
		blocksToPlace--;
		currentBlock = null;
		board.repaint ();
		if (blocksToPlace == 0) {
			finishLevel();
		}
	}

	void removeBlockFromHole (Hole hole)
	{
	Block block = hole.filledWith;
	block.placed = false;
	score -= block.getPoints();
	blocksToPlace++;
	hole.filledWith = null;
	hole.filled = false;
			board.repaint ();
	}
}
