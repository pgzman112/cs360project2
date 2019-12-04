// File: Rectangle.java
// Author: Dr. Watts
// Contents: This file contains the description and implementation
// of a class called Rectangle. 

import static java.lang.Math.*;
import java.awt.*;
import javax.swing.*;

public final class Rectangle extends Quadrilateral
{
	private double side2;

	public Rectangle ()
	{
		side2 = 0;
	}

	public Rectangle (Rectangle R)
	{
		side = R.side;
		side2 = R.side2;
		centerX = R.centerX;
		centerY = R.centerY;
		color = R.color;
		angle = R.angle;
		setRadiusTheta ();
	}

	public Rectangle (double S1, double S2, int X, int Y, Color C, double A)
	{
		side = S1;
		side2 = S2;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setRadiusTheta ();
	}

	public Rectangle clone ()
	{
		Rectangle R = new Rectangle (this);
		return R;
	}

	public void setRadiusTheta ()
	{
		double [] X = {0, 0, 0, 0};
		double [] Y = {0, 0, 0, 0};
		X[2] = X[3] = side;
		Y[1] = Y[2] = -side2;
		double cX = side / 2;
		double cY = -side2 / 2;
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

	public void setSide1 (double S1)
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

	public double perimeter ()
	{
		return side + side2 + sqrt (side * side + side2 * side2);
	}

	public double area ()
	{
		return side * side2 / 2;
	}

	public String getName ()
	{
		return "Rectangle";
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
			color = new Color(Integer.parseInt(parts[4]));
			setAngleD (Double.parseDouble (parts[5]));
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
		string += color.getRGB() + " ";
		string += getAngleD() + " ";
		return string;
	}

	public void resizeP (double P)
	{
		double pct = P;
		double min = side < side2 ? side : side2;
		if (min * P < 5)
			pct = 5 / min;
		side *= pct;
		side2 *= pct;
		setRadiusTheta();
	}
}	
