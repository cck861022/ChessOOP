package pieces;

import java.awt.BorderLayout;

import java.awt.Color;
import java.util.ArrayList;

import chess.Board;
import chess.Coordinate;

/**
 * This is the Piece Class. It is an abstract class from which all the actual
 * pieces are inherited. It defines all the function common to all the pieces
 * The move() function an abstract function that has to be overridden in all the
 * inherited class It implements Cloneable interface as a copy of the piece is
 * required very often
 */
public abstract class Piece implements Cloneable {

	// Member Variables
	private int color;
	//piece position
	private int xPosition, yPosition;
	private String id = null;
	private String path;
	protected ArrayList<Coordinate> possiblemoves = new ArrayList<Coordinate>(); // Protected (access from child classes)

	// public abstract ArrayList<Coordinate> move(Cell pos[][], int x, int y); // Abstract Function. Must be overridden
	public abstract ArrayList<Coordinate> move(Board board, Coordinate coordinate);

	// Id Setter
	public void setId(String id) {
		this.id = id;
	}

	// Path Setter
	public void setPath(String path) {
		this.path = path;
	}

	// Color Setter
	public void setColor(int c) {
		this.color = c;
	}
	
	//xPosition Setter
	public void setXPosition(int x) {
		this.xPosition = x;
	}
	
	//yPosition Setter
	public void setYPosition(int y) {
		this.yPosition = y;
	}
	// Path getter
	public String getPath() {
		return path;
	}

	// Id getter
	public String getId() {
		return id;
	}

	// Color Getter
	public int getcolor() {
		return this.color;
	}
	// xPosition Getter
	public int getXPosition() {
		return this.xPosition;
	}
	// yPosition Getter
	public int getYPosition() {
		return this.yPosition;
	}
	// Function to return the a "shallow" copy of the object. The copy has exact
	// same variable value but different reference
	public Piece getcopy() throws CloneNotSupportedException {
		return (Piece) this.clone();
	}
}