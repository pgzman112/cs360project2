// File: GShape.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a virtual class called GShape. 

import java.text.NumberFormat;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class GShape implements Comparable<GShape>
{
	public enum GShapeType {CIRCLE, SQUARE, RECTANGLE, EQUILATERAL, RIGHT, SCALENE};
	public boolean isSelected;
	protected int centerX;
	protected int centerY;
	protected double side;
	protected Color color;
	protected double angle;	    // stored in radians
	protected double [] radius; // precise radius values
	protected double [] theta;  // precise theta values
	protected double averageRadius;
	protected Polygon polygon;

	public GShape ()
	{
		centerX = centerY = 0;
		side = angle = 0;
		color = Color.WHITE;
		isSelected = false;
	}

	public GShape (GShape S)
	{
		centerX = centerY = 0;
		side = angle = 0;
		color = S.color;
		//color = Color.WHITE;
		isSelected = false;
	}

	public GShape clone ()
	{
		return null;
	}

	public int getCenterX ()
	{
		return centerX;
	}

	public int getCenterY ()
	{
		return centerY;
	}

	protected void setRadiusTheta ()
	{
	}

	public Polygon getPolygon ()
	{
		return new Polygon (polygon.xpoints, polygon.ypoints, polygon.npoints);
	}

	protected void setPosition ()
	{
	}

	public void setCenterX (int X)
	{
		centerX = X;
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
	}

	public void setSide (double S)
	{
		side = S;
	}

	public void setColor (Color C)
	{
		color = C;
	}

	public void setAngleR (double A)
	{
	}

	public double getAngleR ()
	{
		return angle;
	}

	public void setAngleD (double A)
	{
	}

	public double getAngleD ()
	{
		return Math.toDegrees(angle);
	}

	public double area ()
	{
		return 0;
	}

	public double perimeter ()
	{
		return 0;
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			side = Double.parseDouble(parts[2]);
			color = new Color(Integer.parseInt(parts[3]));
			setAngleD(Double.parseDouble(parts[4]));
			setRadiusTheta ();
			setPosition ();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("Numeric input error");
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += color.getRGB() + " ";
		string += getAngleD();
		return string;
	}

	public int compareTo (GShape S)
	{
		double a1 = area (), a2 = S.area ();
		double p1 = perimeter (), p2 = S.perimeter ();
		if (a1 < a2) return -1;
		if (a1 > a2) return 1;
		if (p1 < p2) return -2;
		if (p1 > p2) return 2;
		return 0;
	}

	public String getName ()
	{
		return "GShape";
	}

	public void paintComponent (Graphics2D g2)
	{
	}

	public boolean isIn (int X, int Y)
	{
		return false;
	}

	public void move (int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
		setPosition ();
	}
	
	public void moveTo (int X, int Y)
	{
		centerX = X;
		centerY = Y;
		setPosition ();
	}
	
	public void resizeD (double D)
	{
		resizeP (1 + D / averageRadius);
	}
	
	public void resizeP (double P)
	{
		side *= P;
		if (side < 5)
			side = 5;
		setRadiusTheta();
	}
	
	public void rotate (double A)
	{
	}

	int changeXby (int X, int max)
	{
		return 0;
	}

	int changeYby (int Y, int max)
	{
		return 0;
	}
}
