// File: GameBoard.java
// CS 360 -  Fall 2019 - Watts
// Frames and Animation Example
// November 2019 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the definition and implementation of 
a background Panel class for a small GUI application that
uses the Block class hierarchy.
*/

import static java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GameBoard extends JPanel implements MouseMotionListener, MouseListener, KeyListener
{
	Data data;
	private JFrame outside;
	private boolean inFrame = true;
	private Block selected = null;
	private int currentX, currentY;

	public GameBoard (Data D)
	{
		data = D;
		data.board = this;
		data.board.setBackground(Color.BLACK);
		setDoubleBuffered(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable (true);
		grabFocus();
		addKeyListener(this);
		repaint ();
	}

	public void paintComponent (Graphics g)
	{
		super.paintComponent (g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < data.holes.size(); i++)
			data.holes.get(i).paintComponent (g2);
		for (int i = 0; i < data.blocks.size(); i++)
			data.blocks.get(i).paintComponent (g2);
		grabFocus();
	}

	public void mouseDragged(MouseEvent e)
	{
		//System.out.println ("Mouse dragged event: " + e);
		int mouseX = e.getX();
		int mouseY = e.getY();
		if(inFrame && selected != null && selected.placed == false ){
			selected.move (mouseX - currentX, mouseY - currentY);
			data.checkIfBlockInHole(selected);
			repaint();
		}
		currentX = mouseX;
		currentY = mouseY;
	}

	public void mouseMoved(MouseEvent e)
	{
		//System.out.println ("Mouse moved event: " + e);
	}

	public void mousePressed (MouseEvent e)
	{
		//System.out.println ("Mouse pressed event: " + e);
		inFrame = true;
		currentX = e.getX();
		currentY = e.getY();
		if(selected != null)
			selected.shape.isSelected = false;
		selected = null;
		if(e.getButton() == e.BUTTON1){
			for(int i= data.blocks.size()-1; selected == null && i>=0; i--){
				if(data.blocks.get(i).shape.isIn(currentX, currentY)) {
					selected = data.blocks.get(i);
					data.blocks.get(i).shape.isSelected = true;
				}
			}
		}
		if(selected != null)
			selected.shape.isSelected = false;
		repaint();
	}

	public void mouseReleased (MouseEvent e)
	{
		//System.out.println ("Mouse released event: " + e);
	}

	public void mouseEntered (MouseEvent e) 
	{
		//System.out.println ("Mouse entered event: " + e);
		inFrame = true;
	}

	public void mouseExited (MouseEvent e)
	{
		//System.out.println ("Mouse exited event: " + e);
		inFrame = false;
	}

	public void mouseClicked (MouseEvent e)
	{
		//System.out.println ("Mouse clicked event: " + e);
	}

	public void keyTyped (KeyEvent e)
	{
		// System.out.println ("Key Listener event: " + e);
	}

   
	public void keyPressed (KeyEvent e)
	{
		//System.out.println ("Key Listener event: " + e);
	}
  
	public void keyReleased (KeyEvent e)
	{
		// System.out.println ("Key Listener event: " + e);
	}
}

