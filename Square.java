// File: Square.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Square. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Square extends Quadrilateral
{
	public Square ()
	{
	}

	public Square (Square S)
	{
		side = S.side;
		centerX = S.centerX;
		centerY = S.centerY;
		color = S.color;
		angle = S.angle;
		setRadiusTheta ();
	}

	public Square (double S, int X, int Y, Color C, double A)
	{
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setRadiusTheta ();
	}

	public Square clone ()
	{
		Square S = new Square (this);
		return S;
	}

	public void setRadiusTheta ()
	{
		double [] X = {0, 0, 0, 0};
		double [] Y = {0, 0, 0, 0};
		Y[1] = Y[2] = -side;
		X[2] = X[3] = side;
		double cX = side / 2;
		double cY = -side / 2;
		averageRadius = 0;
		for (int i = 0; i < 4; i++)
		{
			X[i] -= cX;
			Y[i] -= cY;
			radius[i] = sqrt (X[i]*X[i] + Y[i]*Y[i]);
			averageRadius += radius[i];
			theta[i] = atan2 (Y[i], X[i]);
			theta [i] += angle;
		}
		averageRadius /= 4;
		setPosition ();
	}

	public double area ()
	{
		return side * side;
	}

	public double perimeter ()
	{
		return 4 * side;
	}

	public String getName ()
	{
		return "Square";
	}
}
