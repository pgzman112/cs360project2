// File: Circle.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Circle. 

import static java.lang.Math.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public final class Circle extends GShape
{
	int ULX = 0;
	int ULY = 0;
	int diameter = 0;
	int [] vertexX = new int [360];
	int [] vertexY = new int [360];

	public Circle ()
	{
		radius = new double [1];
		radius[0] = 0.0;
		theta = new double [1];
		theta[0] = 0.0;
	}

	public Circle (double S, int X, int Y, Color C)
	{
		radius = new double [1];
		theta = new double [1];
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
		setRadiusTheta();
	}

	public Circle (Circle C)
	{
		radius = new double [1];
		theta = new double [1];
		side = C.side;
		centerX = C.centerX;
		centerY = C.centerY;
		color = C.color;
		setRadiusTheta();
	}
	
	public Circle clone ()
	{
		Circle C = new Circle (this);
		return C;
	}

	public void setRadiusTheta ()
	{
		radius[0] = side / 2.0;
		theta[0] = 0.0;
		averageRadius = radius[0];
		setPosition ();
	}

	public void setPosition ()
	{
		ULX = (int) Math.round (centerX - radius[0]);
		ULY = (int) Math.round (centerY - radius[0]);
		diameter = (int) Math.round (side);
		for (int i = 0; i < 360; i++)
		{
			double theta = Math.toRadians (i);
			vertexX[i] = (int) Math.round (radius[0] * cos (theta) + centerX);
			vertexY[i] = (int) Math.round (radius[0] * sin (theta) + centerY);
		}
		polygon = new Polygon (vertexX, vertexY, 360);
	}

	public void setDiameter (double S)
	{
		side = S;
		setRadiusTheta();
	}

	public double getDiameter ()
	{
		return side;
	}

	public double area ()
	{
		return Math.PI * side * side / 4;
	}

	public double perimeter ()
	{
		return Math.PI * side;
	}

	public String getName ()
	{
		return "Circle";
	}

	public void paintComponent (Graphics2D g2)
	{
		g2.setPaint (color);
		g2.fillOval (ULX, ULY, diameter, diameter);
		if (isSelected)
			g2.setPaint (Color.WHITE);
		g2.drawOval (ULX, ULY, diameter, diameter);
		//g2.setPaint (Color.GRAY);
		//g2.drawPolygon (vertexX, vertexY, 360);
		//g2.setPaint (Color.BLACK);
		//g2.fillOval ((int) centerX-1, (int) centerY-1, 2, 2); // Draw the center point
	}

	public boolean isIn (int X, int Y)
	{
		double deltaX = X - centerX;
		double deltaY = Y - centerY;
		double dist = sqrt (deltaX * deltaX + deltaY * deltaY);
		return dist <= side / 2;
	}

	int changeXby (int X, int max)
	{
		if (X < 0)
		{
			int remaining = (int) (centerX - radius[0]);
			if (remaining < -X)
				return X + remaining;
		}
		else if (X > 0)
		{
			int remaining = (int) (max - (centerX + radius[0]));
			if (remaining < X)
				return X - remaining;
		}
		return 0;
	}

	int changeYby (int Y, int max)
	{
		if (Y < 0)
		{
			int remaining = (int) (centerY - radius[0]);
			if (remaining < -Y)
				return Y + remaining;
		}
		else if (Y > 0)
		{
			int remaining = (int) (max - (centerY + radius[0]));
			if (remaining < Y)
				return Y - remaining;
		}
		return 0;
	}
}
