// File: Quadrilateral.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Quadrilateral. 

import static java.lang.Math.*;
import java.awt.*;
import java.util.*;

public class Quadrilateral extends GShape
{
	protected int [] vertexX = new int [4]; // integer X coordinates for drawing
	protected int [] vertexY = new int [4]; // integer X coordinates for drawing
	//protected Polygon polygon;		// Java polygon for drawing

	Quadrilateral ()
	{
		radius = new double [4];
		theta = new double [4];
	}

	public void setCenterX (int X)
	{
		centerX = X;
		setPosition ();
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
		setPosition ();
	}

	public void setAngleR (double A)
	{
		angle = A;
		setRadiusTheta ();
	}

	public void setAngleD (double A)
	{
		angle = Math.toRadians(A);
		setRadiusTheta ();
	}

	public String getName ()
	{
		return "Quadrilateral";
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillPolygon (vertexX, vertexY, 4);
		if (isSelected)
			g2.setPaint (Color.WHITE);
		g2.drawPolygon (vertexX, vertexY, 4);
		//g2.setPaint (Color.BLACK);
		//g2.fillOval (centerX-1, centerY-1, 2, 2); // Draw the center point
	}

	protected void setPosition ()
	{
		for (int i = 0; i < 4; i++)
		{
			vertexX[i] = (int) Math.round (radius[i] * cos (theta[i]) + centerX);
			vertexY[i] = (int) Math.round (radius[i] * sin (theta[i]) + centerY);
		}
		polygon = new Polygon (vertexX, vertexY, 4);
	}

	public boolean isIn (int X, int Y)
	{
		return polygon.contains (X, Y);
	}
	
	public void rotate (double A)
	{
		angle += A;
		for (int i = 0; i < 4; i++)
			theta[i] += A;
		setPosition (); 
	}

	int changeXby (int X, int max)
	{
		if (X < 0)
		{
			int remaining = vertexX[0];
			for (int i = 1; i < 4; i++)
				if (vertexX[i] < remaining)
					remaining = vertexX[i];
			if (remaining < -X)
				return X + remaining;
		}
		else if (X > 0)
		{
			int remaining = (max - vertexX[0]);
			for (int i = 1; i < 4; i++)
				if ((max - vertexX[i]) < remaining)
					remaining = (max - vertexX[i]);
			if (remaining < X)
				return X - remaining;
		}
		return 0;
	}

	int changeYby (int Y, int max)
	{
		if (Y < 0)
		{
			int remaining = vertexY[0];
			for (int i = 1; i < 4; i++)
				if (vertexY[i] < remaining)
					remaining = vertexY[i];
			if (remaining < -Y)
				return Y + remaining;
		}
		else if (Y > 0)
		{
			int remaining = (max - vertexY[0]);
			for (int i = 1; i < 4; i++)
				if ((max - vertexY[i]) < remaining)
					remaining = (max - vertexY[i]);
			if (remaining < Y)
				return Y - remaining;
		}
		return 0;
	}
}
