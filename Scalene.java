// File: Scalene.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Scalene. 

import static java.lang.Math.*;
import javax.swing.*;
import java.awt.*;

public final class Scalene extends Triangle
{
	private double side2;
	private double side3;

	public Scalene ()
	{
		side2 = 0;
		side3 = 0;
	}

	public Scalene (Scalene S)
	{
		side = S.side;
		side2 = S.side2;
		side3 = S.side3;
		centerX = S.centerX;
		centerY = S.centerY;
		color = S.color;
		angle = S.angle;
		setRadiusTheta ();
	}

	public Scalene (double S1, double S2, double S3, int X, int Y, Color C, double A)
	{
		side = S1;
		side2 = S2;
		side3 = S3;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setRadiusTheta ();
	}

	public Scalene clone ()
	{
		Scalene S = new Scalene (this);
		return S;
	}

	public void setRadiusTheta ()
	{
		double [] X = {0, 0, 0};
		double [] Y = {0, 0, 0};
		double cosAngle = (side * side + side2 * side2 - side3 * side3) / (2.0 * side * side2);
		double angle0 = acos (cosAngle);
		double height = sin(angle0) * side2;
		double offX = cos(angle0) * side2;
		X[1] = offX; Y[1] = -height;
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

	public void setSide1 (int S1)
	{
		side = S1;
		setRadiusTheta ();
	}

	public double getSide1 ()
	{
		return side;
	}

	public void setSide2 (double S2)
	{
		side2 = S2;
		setRadiusTheta ();
	}

	public double getSide2 ()
	{
		return side2;
	}

	public void setSide3 (int S3)
	{
		side3 = S3;
		setRadiusTheta ();
	}

	public Double getSide3 ()
	{
		return side3;
	}

	public double perimeter ()
	{
		return side + side2 + side3;
	}

	public double area ()
	{
		double h = (side + side2 + side3) / 2.0;
		return sqrt (h * (h-side) * (h-side2) * (h-side3));
	}

	public String getName ()
	{
		return "Scalene";
	}

	public void fromString (String str)
	{
		String [] parts = str.split (" ");
		try
		{
			centerX = Integer.parseInt(parts[0]);
			centerY = Integer.parseInt(parts[1]);
			side = Double.parseDouble(parts[2]);
			side2 = Double.parseDouble(parts[3]);
			side3 = Double.parseDouble(parts[4]);
			color = new Color(Integer.parseInt(parts[5]));
			setAngleD (Double.parseDouble (parts[6]));
			setRadiusTheta ();
		}
		catch (NumberFormatException e)
		{
			System.out.println ("File input error - invalid numeric value");;
		}
	}

	public String toString ()
	{
		String string = new String ();
		string += centerX + " ";
		string += centerY + " ";
		string += side + " ";
		string += side2 + " ";
		string += side3 + " ";
		string += color.getRGB() + " ";
		string += getAngleD() + " ";
		return string;
	}

	public void resizeP (double P)
	{
		double pct = P;
		double min = side < side2 ? side : side2;
		min = min < side3 ? min : side3;
		if (min * P < 5)
			pct = 5 / min;
		side *= pct;
		side2 *= pct;
		side3 *= pct;
		setRadiusTheta();
	}
}	
