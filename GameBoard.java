import java.awt.Graphics;
import java.awt.Point;

public class GameBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Gives the number of CompBoards in GameBoard
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	
	// Array of CompBoards and an ArrowBoard
	private CompBoard[][] board = new CompBoard[ROWS][COLUMNS];
	private ArrowBoard arrows;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public GameBoard(CompBoard[][] board, ArrowBoard arrows, int x, int y, int width, int height) {
		setBoard(board);
		setArrows(arrows);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Use this constructor generally
	public GameBoard(int x, int y, int width, int height) {
		this(createBoard(x, y, width, height), createArrows(x+width*11/10, y+height/3, width/2, height/2), x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// Creates initial GameBoard with blank CompBoards. Used in Constructor.
	private static CompBoard[][] createBoard(int x, int y, int width, int height) {
		CompBoard[][] board = new CompBoard[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				board[row][col] = new CompBoard(x+col*width/COLUMNS, y+row*height/ROWS, width/COLUMNS, height/ROWS);
			}
		}
		return board;
	}
	
	// Creates ArrowBoard. Used in Constructor
	private static ArrowBoard createArrows(int x, int y, int width, int height) {
		return new ArrowBoard(x, y, width, height);
	}
	
	// Draws GameBoard by drawing its CompBoards
	public void draw(Graphics g) {
		for(int i = 0; i <= ROWS; i++) {
			g.drawLine(getX() + i*getWidth()/ROWS+1, getY(), getX() + i*getWidth()/ROWS+1, getY()+getHeight());
			g.drawLine(getX() + i*getWidth()/ROWS-1, getY(), getX() + i*getWidth()/ROWS-1, getY()+getHeight());
		}
		for(int i = 0; i <= COLUMNS; i++) {
			g.drawLine(getX(), getY() + i*getHeight()/COLUMNS+1, getX()+getWidth(), getY() + i*getHeight()/COLUMNS+1);
			g.drawLine(getX(), getY() + i*getHeight()/COLUMNS-1, getX()+getWidth(), getY() + i*getHeight()/COLUMNS-1);
		}
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				board[i][j].draw(g);
			}
		}
		arrows.draw(g);
	}
	
	// Given a point on the graphical board and whose turn it is, changes the piece there to that color
	public boolean playPiece(Point p, PieceType type) {
		Point compCell = getPosition(p);
		if(compCell.x != -1 && compCell.y != -1) {
			Point pieceCell = getBoard()[compCell.x][compCell.y].getPosition(p);
			if(getBoard()[compCell.x][compCell.y].getBoard()[pieceCell.x][pieceCell.y].getType() == PieceType.BLANK){
				getBoard()[compCell.x][compCell.y].getBoard()[pieceCell.x][pieceCell.y].setType(type);
				for(TwistArrow[] row : getArrows().getBoard()) {
					for(TwistArrow t : row) {
						t.setUsed(false);
					}
				}
				return true;
			}
		}
		return false;
	}
	
	// Given a point on the graphical board, twists a CompBoard
	public boolean twistBoard(Point p, PieceType type) {
		Point twistCell = getArrows().getPosition(p);
		Point exactTwistCell = getArrows().getExactPosition(p);
		System.out.println(twistCell);
		if(twistCell.x != -1 && twistCell.y != -1) {
			if(!getArrows().getBoard()[exactTwistCell.x][exactTwistCell.y].isUsed()) {
				if(getArrows().getDirection(p) == ArrowType.Negative) {
					getBoard()[twistCell.x][twistCell.y].twist();
					getBoard()[twistCell.x][twistCell.y].twist();
				}
				getBoard()[twistCell.x][twistCell.y].twist();
				getArrows().getBoard()[exactTwistCell.x+1-(exactTwistCell.x%2)*2][exactTwistCell.y].setUsed(true);
				return true;
			}
		}
		return false;
	}
	
	// Gets coordinates for CompBoard[][] from a point on the graphical board
	public Point getPosition(Point p) {
		Point answer = new Point();
		int cellRow = (p.y-getY() < 0) ? -1 : (p.y-getY())*ROWS/getWidth();
		int cellColumn = (p.x-getX() < 0) ? -1 : (p.x-getX())*COLUMNS/getHeight();
		answer.x = (cellRow < 0 || cellRow >= ROWS) ? -1 : cellRow;
		answer.y = (cellColumn < 0 || cellColumn >= COLUMNS) ? -1 : cellColumn;
		return answer;
	}
	
	// Used to see if there is a win in GameBoard
	public boolean checkWin() {
		return true;
	}
	
	public GameBoard clone() {
		return new GameBoard(getBoard(), getArrows(), getX(), getY(), getWidth(), getHeight());
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public CompBoard[][] getBoard() {
		return board;
	}

	public void setBoard(CompBoard[][] board) {
		this.board = board;
	}

	public ArrowBoard getArrows() {
		return arrows;
	}

	public void setArrows(ArrowBoard arrows) {
		this.arrows = arrows;
	}
	
	
	
	
}
