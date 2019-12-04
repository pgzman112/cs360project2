// File: Hole.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Hole. 

import java.text.NumberFormat;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.geom.*;

public class Hole
{
	public GShape shape;
	public Area area;
	public boolean filled;
	public Block filledWith;

	public Hole ()
	{
		shape = null;
		area = null;
		filled = false;
		filledWith = null;
	}

	public Hole (Hole H)
	{
		shape = H.shape.clone();
		area = H.area;
		filled = H.filled;
		filledWith = H.filledWith;
	}

	public Hole (GShape S)
	{
		shape = S;
		shape.isSelected = true;
		area = new Area ();
		filled = false;
		filledWith = null;
	}

	public Hole clone ()
	{
		return new Hole (this);
	}
	
	public boolean containsBlock (Block B)
	{
		Polygon HP = shape.getPolygon();
		Polygon BP = B.shape.getPolygon();
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
		if (filled)
			filledWith.paintComponent (g2);
	}

	public boolean isIn (int X, int Y)
	{
		return shape.isIn (X, Y);
	}
}
