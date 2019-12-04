// File: Equilateral.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Equilateral. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Equilateral extends Triangle
{
	public Equilateral ()
	{
	}

	public Equilateral (Equilateral E)
	{
		side = E.side;
		centerX = E.centerX;
		centerY = E.centerY;
		color = E.color;
		angle = E.angle;
		setRadiusTheta ();
	}

	public Equilateral (double S, int X, int Y, Color C, double A)
	{
		side = S;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setRadiusTheta ();
	}

	public Equilateral clone ()
	{
		Equilateral E = new Equilateral (this);
		return E;
	}

	public void setRadiusTheta ()
	{
		double [] X = {0, 0, 0};
		double [] Y = {0, 0, 0};
		X[1] = side/2; Y[1] = -side * sqrt(3) / 2;
		X[2] = side;
		double cX = (X[0] + X[1] + X[2]) / 3;
		double cY = (Y[0] + Y[1] + Y[2]) / 3;
		averageRadius = 0;
		for (int i = 0; i < 3; i++)
		{
			X[i] -= cX;
			Y[i] -= cY;
			radius[i] = sqrt (X[i]*X[i] + Y[i]*Y[i]);
			averageRadius += radius[i];
			theta[i] = atan2 (Y[i], X[i]);
			theta [i] += angle;
		}
		averageRadius /= 3;
		setPosition ();
	}

	public double area ()
	{
		return sqrt(3) * side * side / 4;
	}

	public double perimeter ()
	{
		return 3 * side;
	}

	public String getName ()
	{
		return "Equilateral";
	}
}
