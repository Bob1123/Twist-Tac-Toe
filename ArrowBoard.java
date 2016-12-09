import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Arrays;

public class ArrowBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Sets the number of rows and columns in each ArrowBoard. Two TwistArrows for
	// every CompBoard, here arranged vertically.
	public static final int ROWS = 2*GameBoard.ROWS;
	public static final int COLUMNS = GameBoard.COLUMNS;
	
	// Stores the TwistArrows for display and activation.
	private TwistArrow[][] board;

	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public ArrowBoard(TwistArrow[][] board, int x, int y, int width, int height) {
		setBoard(board);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Use this constructor generally
	public ArrowBoard(int x, int y, int width, int height) {
		this(createBoard(x, y, width, height), x, y, width, height);
	}
	
	// Copy Constructor
	public ArrowBoard(ArrowBoard b) {
		this(b.cloneBoard(), b.getX(), b.getY(), b.getWidth(), b.getHeight());
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// Creates initial ArrowBoard with blank GamePieces. Used in Constructor.
	private static TwistArrow[][] createBoard(int x, int y, int width, int height) {
		TwistArrow[][] board = new TwistArrow[ROWS][COLUMNS];
		for(int row = 0; row < ROWS; row++) {
			for(int col = 0; col < COLUMNS; col++) {
				if(row % 2 == 0) {
					board[row][col] = new TwistArrow(ArrowType.POSITIVE, x+col*width/COLUMNS, y+row*height/ROWS, width/COLUMNS, height/ROWS);
				}
				if(row % 2 == 1) {
					board[row][col] = new TwistArrow(ArrowType.NEGATIVE, x+col*width/COLUMNS, y+row*height/ROWS, width/COLUMNS, height/ROWS);
				}
			}
		}
		return board;
	}
	
	// From a point on the graphical board, returns the coordinates for TwistArrow[][]
	public Point getExactPosition(Point p) {
		Point answer = new Point();
		int cellRow = (p.y-getY() < 0) ? -1 : (p.y-getY())*ROWS/getWidth();
		int cellColumn = (p.x-getX() < 0) ? -1 : (p.x-getX())*COLUMNS/getHeight();
		answer.x = (cellRow < 0 || cellRow >= ROWS) ? -1 : cellRow;
		answer.y = (cellColumn < 0 || cellColumn >= COLUMNS) ? -1 : cellColumn;
		return answer;
	}
	
	// From a point on the graphical board, returns the coordinates for the CompBoard[][] corresponding to TwistArrow[][]
	public Point getPosition(Point p) {
		Point exactPosition = getExactPosition(p);
		Point answer = new Point();
		answer.x = (exactPosition.x == -1) ? -1 : exactPosition.x/2;
		answer.y = exactPosition.y;
		return answer;
	}
	
	// From a point on the graphical board, returns whether that point was in the upper half of the section of ArrowBoard
	public ArrowType getDirection(Point p) {
		Point exactPosition = getExactPosition(p);
		if(exactPosition.x % 2 == 1) {
			return ArrowType.POSITIVE;
		}
		if(exactPosition.x % 2 == 0) {
			return ArrowType.NEGATIVE;
		}
		System.out.println("Error");
		return ArrowType.NEGATIVE;
	}
	
	// Draws the ArrowBoard by drawing its lines and TwistPieces.
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i = 0; i <= ROWS; i++) {
			if(i % 2 == 0) {
				g.drawLine(getX() + i*getWidth()/ROWS, getY(), getX() + i*getWidth()/ROWS, getY()+getHeight());
			}
		}
		for(int i = 0; i <= COLUMNS; i++) {
			g.drawLine(getX(), getY() + i*getHeight()/COLUMNS, getX()+getWidth(), getY() + i*getHeight()/COLUMNS);
		}
		for(TwistArrow[] row : board) {
			for(TwistArrow p : row) {
				if(!p.isUsed()) {
					p.draw(g);
				}
			}
		}
	}
	
	public TwistArrow[][] cloneBoard() {
		TwistArrow[][] newboard = new TwistArrow[ROWS][COLUMNS];
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLUMNS; j++) {
				newboard[i][j] = getBoard()[i][j].clone();
			}
		}
		return newboard;
	}

	public ArrowBoard clone() {
		return new ArrowBoard(this);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public TwistArrow[][] getBoard() {
		return board;
	}

	public void setBoard(TwistArrow[][] board) {
		this.board = board;
	}

	@Override
	public String toString() {
		String output = "ArrowBoard [board=";
		for(int i = 0; i < getBoard().length; i++) {
			for(int j = 0; j < getBoard()[i].length; j++) {
				output = output + "@(" + i + "," + j + "): " + getBoard()[i][j].toString() + "\t";
			}
			output = output + "\n";
		}
		output = output + "]" + super.toString();
		return output;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof ArrowBoard))
			return false;
		ArrowBoard other = (ArrowBoard) obj;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		return true;
	}
	
}
