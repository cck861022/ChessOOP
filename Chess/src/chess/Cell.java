package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;

/**
 * This is the Cell Class. It is the token class of our GUI. There are total of
 * 64 cells that together makes up the Chess Board
 *
 */
public class Cell extends JPanel implements Cloneable {

	// Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	int x, y; // is public because this is to be accessed by all the other class
	private boolean isSelected = false;
	private boolean ischeck = false;

	// Constructors
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;

		setLayout(new BorderLayout());

		if ((x + y) % 2 == 0)
			setBackground(new Color(113, 198, 113));

		else
			setBackground(Color.white);

	}

	// A constructor that takes a cell as argument and returns a new cell will the
	// same data but different reference
	// public Cell(Cell cell) throws CloneNotSupportedException {
	// 	this.x = cell.x;
	// 	this.y = cell.y;
	// 	setLayout(new BorderLayout());
	// 	if ((x + y) % 2 == 0)
	// 		setBackground(new Color(113, 198, 113));
	// 	else
	// 		setBackground(Color.white);
	// 	if (cell.getpiece() != null) {
	// 		setPiece(cell.getpiece().getcopy());
	// 	} else
	// 		piece = null;
	// }

	public void select() // Function to mark a cell indicating it's selection
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red, 6));
		this.isSelected = true;
	}

	public boolean isselected() // Function to return if the cell is under selection
	{
		return this.isSelected;
	}

	public void deselect() // Function to delselect the cell
	{
		this.setBorder(null);
		this.isSelected = false;
	}

	public void setpossibledestination() // Function to highlight a cell to indicate that it is a possible valid move
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
		this.ispossibledestination = true;
	}

	public void removepossibledestination() // Remove the cell from the list of possible moves
	{
		this.setBorder(null);
		this.ispossibledestination = false;
	}

	public boolean ispossibledestination() // Function to check if the cell is a possible destination
	{
		return this.ispossibledestination;
	}

	public void setcheck() // Function to highlight the current cell as checked (For King)
	{
		this.setBackground(Color.RED);
		this.ischeck = true;
	}

	public void removecheck() // Function to deselect check
	{
		this.setBorder(null);
		if ((x + y) % 2 == 0)
			setBackground(new Color(113, 198, 113));
		else
			setBackground(Color.white);
		this.ischeck = false;
	}

	public boolean ischeck() // Function to check if the current cell is in check
	{
		return ischeck;
	}

	// TODO:should be removed
	// public Piece getpiece(){
	// 	return new Pawn("1", "wp", 1);
	// }
}