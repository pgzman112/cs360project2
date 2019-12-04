// File: Block.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Block. 

import java.text.NumberFormat;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class Block // implements Comparable<Block>
{
	public GShape shape;
	public Area area;
	public boolean onBoard;
	public boolean placed;
	private int blockPoints;

	public Block ()
	{
		shape = null;
		area = null;
		onBoard = false;
		placed = false;
		blockPoints = -1;
	}

	public Block (GShape S, int P)
	{
		shape = S;
		area = new Area ();
		onBoard = false;
		placed = false;
		blockPoints = P;
	}

	public Block (Block B)
	{
		shape = B.shape.clone();
		area = B.area;
		onBoard = B.onBoard;
		placed = B.placed;
		blockPoints = B.blockPoints;
	}

	public Block clone ()
	{
		return new Block (this);
	}

	int getPoints ()
	{
		return blockPoints;
	}

	public boolean containedInHole (Hole H)
	{
		Polygon BP = shape.getPolygon();
		Polygon HP = H.shape.getPolygon();
		for (int i = 0; i < BP.npoints; i++)
			if (!HP.contains (BP.xpoints[i], BP.ypoints[i]))
			// A vertex of the block is not in the hole
				return false;
		for (int i = 0; i < HP.npoints; i++)
			if (BP.contains (HP.xpoints[i], HP.ypoints[i]))
			// A vertex of the hole is in the block
				return false;
		return true;
	}

	public void paintComponent (Graphics2D g2)
	{
		shape.paintComponent (g2);
		String pointStr = "" + blockPoints;
		Font font = new Font ("Arial", Font.PLAIN, 22);
        	g2.setFont (font);
		Rectangle2D rect = font.getStringBounds (pointStr, g2.getFontRenderContext());
		int width = (int) rect.getWidth();
		int height = (int) rect.getHeight();
		int left = shape.getCenterX() - width / 2;
		int top = shape.getCenterY() - height / 2;
		g2.setPaint (Color.BLACK);
        	g2.drawString (pointStr, left, top + 4*height/5);
	}

	boolean compareTo (Block B)
	{
		return false;
	}
	
	public boolean isIn (int X, int Y)
	{
		return shape.isIn (X, Y);
	}

	public void move (int deltaX, int deltaY)
	{
		shape.move (deltaX, deltaY);
	}
	
	public void moveTo (int X, int Y)
	{
		shape.moveTo (X, Y);
	}
	
	public void rotate (double A)
	{
		shape.rotate (A);
	}
}
