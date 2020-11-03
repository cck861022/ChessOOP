package chess;

import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

import pieces.*;


public class ChessGame {

    public Cell boardState[][];
    public Piece board[][];
    
    private static Rook wr01, wr02, br01, br02;
	private static Knight wk01, wk02, bk01, bk02;
	private static Bishop wb01, wb02, bb01, bb02;
	private static Pawn wp[], bp[];
	private static Queen wq, bq;
    private static King wk, bk;
    
    ChessGame(){
        wr01 = new Rook("WR01", "White_Rook.png", 0);
		wr02 = new Rook("WR02", "White_Rook.png", 0);
		br01 = new Rook("BR01", "Black_Rook.png", 1);
		br02 = new Rook("BR02", "Black_Rook.png", 1);
		wk01 = new Knight("WK01", "White_Knight.png", 0);
		wk02 = new Knight("WK02", "White_Knight.png", 0);
		bk01 = new Knight("BK01", "Black_Knight.png", 1);
		bk02 = new Knight("BK02", "Black_Knight.png", 1);
		wb01 = new Bishop("WB01", "White_Bishop.png", 0);
		wb02 = new Bishop("WB02", "White_Bishop.png", 0);
		bb01 = new Bishop("BB01", "Black_Bishop.png", 1);
		bb02 = new Bishop("BB02", "Black_Bishop.png", 1);
		wq = new Queen("WQ", "White_Queen.png", 0);
		bq = new Queen("BQ", "Black_Queen.png", 1);
		wk = new King("WK", "White_King.png", 0, 7, 3);
		bk = new King("BK", "Black_King.png", 1, 0, 3);
		wp = new Pawn[8];
		bp = new Pawn[8];
		for (int i = 0; i < 8; i++) {
			wp[i] = new Pawn("WP0" + (i + 1), "White_Pawn.png", 0);
			bp[i] = new Pawn("BP0" + (i + 1), "Black_Pawn.png", 1);
		}
    }

    // A function to retrieve the Black King or White King
	public King getKing(int color) {
		if (color == 0)
			return wk;
		else
			return bk;
    }

    public Cell[][] setBoard(JPanel board){
        pieces.Piece P;
        Cell cell;
        // Defining all the Cells
		boardState = new Cell[8][8];
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++) {
				P = null;
				if (i == 0 && j == 0)
					P = br01;
				else if (i == 0 && j == 7)
					P = br02;
				else if (i == 7 && j == 0)
					P = wr01;
				else if (i == 7 && j == 7)
					P = wr02;
				else if (i == 0 && j == 1)
					P = bk01;
				else if (i == 0 && j == 6)
					P = bk02;
				else if (i == 7 && j == 1)
					P = wk01;
				else if (i == 7 && j == 6)
					P = wk02;
				else if (i == 0 && j == 2)
					P = bb01;
				else if (i == 0 && j == 5)
					P = bb02;
				else if (i == 7 && j == 2)
					P = wb01;
				else if (i == 7 && j == 5)
					P = wb02;
				else if (i == 0 && j == 3)
					P = bk;
				else if (i == 0 && j == 4)
					P = bq;
				else if (i == 7 && j == 3)
					P = wk;
				else if (i == 7 && j == 4)
					P = wq;
				else if (i == 1)
					P = bp[j];
				else if (i == 6)
					P = wp[j];
				cell = new Cell(i, j, P);
				board.add(cell);
				boardState[i][j] = cell;
            }
        }
        return boardState;
    }

    // Function to check if the king will be in danger if the given move is made
	public boolean willkingbeindanger(Cell fromcell, Cell tocell, int chance) {
		Cell newboardstate[][] = new Cell[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				try {
					newboardstate[i][j] = new Cell(boardState[i][j]);
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
					System.out.println("There is a problem with cloning !!");
				}
			}

		if (newboardstate[tocell.x][tocell.y].getpiece() != null)
			newboardstate[tocell.x][tocell.y].removePiece();

		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
		if (newboardstate[tocell.x][tocell.y].getpiece() instanceof King) {
			((King) (newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
			((King) (newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
		}
		newboardstate[fromcell.x][fromcell.y].removePiece();
		if (((King) (newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece()))
				.isindanger(newboardstate) == true)
			return true;
		else
			return false;
    }
    
    // A Function to filter the possible moves when the king of the current player
	// is under Check
	private ArrayList<Cell> incheckfilter(ArrayList<Cell> destlist, Cell fromcell, int color) {
		ArrayList<Cell> newlist = new ArrayList<Cell>();
		Cell newboardstate[][] = new Cell[8][8];
		ListIterator<Cell> it = destlist.listIterator();
		int x, y;
		while (it.hasNext()) {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++) {
					try {
						newboardstate[i][j] = new Cell(boardState[i][j]);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			Cell tempc = it.next();
			if (newboardstate[tempc.x][tempc.y].getpiece() != null)
				newboardstate[tempc.x][tempc.y].removePiece();
			newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
			x = getKing(color).getx();
			y = getKing(color).gety();
			if (newboardstate[tempc.x][tempc.y].getpiece() instanceof King) {
				((King) (newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
				((King) (newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
				x = tempc.x;
				y = tempc.y;
			}
			newboardstate[fromcell.x][fromcell.y].removePiece();
			if ((((King) (newboardstate[x][y].getpiece())).isindanger(newboardstate) == false))
				newlist.add(tempc);
		}
		return newlist;
    }
    // A function to check if the King is check-mate. The Game Ends if this function
	// returns true.
	public boolean checkmate(int color) {
		ArrayList<Cell> dlist = new ArrayList<Cell>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (boardState[i][j].getpiece() != null && boardState[i][j].getpiece().getcolor() == color) {
					dlist.clear();
					dlist = boardState[i][j].getpiece().move(boardState, i, j);
					dlist = incheckfilter(dlist, boardState[i][j], color);
					if (dlist.size() != 0)
						return false;
				}
			}
		}
		return true;
    }
    
    public boolean ischeck(int chance){
        return boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck();
    }
}
