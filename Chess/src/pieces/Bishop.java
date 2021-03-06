package pieces;

import java.util.ArrayList;


import chess.Position;
import chess.Cell;

/**
 * This is the Bishop Class. The Move Function defines the basic rules for
 * movement of Bishop on a chess board
 * 
 *
 */
public class Bishop extends Piece {

	// Constructor
	public Bishop(String i, String p, int c, int x, int y) {
		setId(i);
		setPath(p);
		setColor(c);
		setXPosition(x);
		setYPosition(y);
	}

	// move function defined. It returns a list of all the possible destinations of
	// a Bishop
	// The basic principle of Bishop Movement on chess board has been implemented
	public ArrayList<Position> move(Piece state[][], int x, int y) {
		// Bishop can Move diagonally in all 4 direction (NW,NE,SW,SE)
		// This function defines that logic
		possiblemoves.clear();
		int tempx = x + 1, tempy = y - 1;
		while (tempx < 8 && tempy >= 0) {
			if (state[tempx][tempy] == null) {
				possiblemoves.add( new Position(tempx, tempy) );
			} else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx++;
			tempy--;
		}
		tempx = x - 1;
		tempy = y + 1;
		while (tempx >= 0 && tempy < 8) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx--;
			tempy++;
		}
		tempx = x - 1;
		tempy = y - 1;
		while (tempx >= 0 && tempy >= 0) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx--;
			tempy--;
		}
		tempx = x + 1;
		tempy = y + 1;
		while (tempx < 8 && tempy < 8) {
			if (state[tempx][tempy] == null)
				possiblemoves.add( new Position(tempx, tempy) );
			else if (state[tempx][tempy].getcolor() == this.getcolor())
				break;
			else {
				possiblemoves.add( new Position(tempx, tempy) );
				break;
			}
			tempx++;
			tempy++;
		}
		return possiblemoves;
	}
}