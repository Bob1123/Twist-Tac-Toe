import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;

public class CompBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Sets the number of rows and columns in each CompBoard
	public static final int rows = 3;
	public static final int columns = 3;
	
	private GamePiece[][] board;
	private TwistArrow[] arrows;
	private CompType type;

	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public CompBoard(GamePiece[][] board, TwistArrow[] arrows, CompType type, int x, int y, int width, int height) {
		setBoard(board);
		setArrows(arrows);
		setType(type);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Use this constructor generally
	public CompBoard(CompType type, int x, int y, int width, int height) {
		this(createBoard(x, y, width, height), createArrows(x, y, width, height, type), type, x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// Creates initial CompBoard with blank GamePieces. Used in Constructor.
	private static GamePiece[][] createBoard(int x, int y, int width, int height) {
		GamePiece[][] board = new GamePiece[rows][columns];
		for(int row = 0; row < rows; row++) {
			for(int col = 0; col < columns; col++) {
				board[row][col] = new GamePiece(x+col*width/columns, y+row*height/rows, width/columns, height/rows);
			}
		}
		return board;
	}
	
	// Creates direction and placement of TwistArrows for CompBoard. Used in constructor.
	private static TwistArrow[] createArrows(int x, int y, int width, int height, CompType type) {
		TwistArrow[] arrows = new TwistArrow[2];
		if(type == CompType.NE) {
			arrows[0] = new TwistArrow(ArrowType.NW, x+(columns-1)*width/columns, y-height/rows, width/columns, height/rows);
			arrows[1] = new TwistArrow(ArrowType.SE, x+width, y, width/columns, height/rows);
		}
		if(type == CompType.NW) {
			arrows[0] = new TwistArrow(ArrowType.NE, x, y-height/rows, width/columns, height/rows);
			arrows[1] = new TwistArrow(ArrowType.SW, x-width/columns, y, width/columns, height/rows);
		}
		if(type == CompType.SW) {
			arrows[0] = new TwistArrow(ArrowType.NW, x-width/columns, y+(rows-1)*height/rows, width/columns, height/rows);
			arrows[1] = new TwistArrow(ArrowType.SE, x, y+height, width/columns, height/rows);
		}
		if(type == CompType.SE) {
			arrows[0] = new TwistArrow(ArrowType.NE, x+width, y+(rows-1)*height/rows, width/columns, height/rows);
			arrows[1] = new TwistArrow(ArrowType.SW, x+(columns-1)*width/columns, y+height, width/columns, height/rows);
		}
		return arrows;
	}
	
	// The following four methods give the CompType in the resulting direction, or null if it does not exist.
	public CompType getUp() {
		switch(getType()) {
		case SE: return CompType.NE;
		case SW: return CompType.NW;
		default: return null;
		}
	}
	
	public CompType getDown() {
		switch(getType()) {
		case NE: return CompType.SE;
		case NW: return CompType.SW;
		default: return null;
		}
	}
	
	public CompType getRight() {
		switch(getType()) {
		case NE: return CompType.NW;
		case SE: return CompType.SW;
		default: return null;
		}
	}
	
	public CompType getLeft() {
		switch(getType()) {
		case NW: return CompType.NE;
		case SW: return CompType.SE;
		default: return null;
		}
	}
	
	// Draws the CompBoard by drawing its lines, GamePieces, and TwistPieces. (Replace with a loop)
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawLine(getX()+getWidth()/3, getY(), getX()+getWidth()/3, getY()+getHeight());
		g.drawLine(getX()+getWidth()*2/3, getY(), getX()+getWidth()*2/3, getY()+getHeight());
		g.drawLine(getX(), getY()+getHeight()/3, getX(), getY()+getHeight()/3);
		g.drawLine(getX(), getY()+getHeight()*2/3, getX(), getY()+getHeight()*2/3);
		for(GamePiece[] row : board) {
			for(GamePiece p : row) {
				if(p != null) {
					p.draw(g);
				}
			}
		}
		for(TwistArrow t : arrows) {
			if(!t.isUsed()) {
				t.draw(g);
			}
		}
	}

	public CompBoard clone() {
		return new CompBoard(getBoard(), getArrows(), getType(), getX(), getY(), getWidth(), getHeight());
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public GamePiece[][] getBoard() {
		return board;
	}

	public void setBoard(GamePiece[][] board) {
		this.board = board;
	}

	public TwistArrow[] getArrows() {
		return arrows;
	}

	public void setArrows(TwistArrow[] arrows) {
		this.arrows = arrows;
	}

	public CompType getType() {
		return type;
	}

	public void setType(CompType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "CompBoard [board=" + Arrays.toString(board) + ", arrows=" + Arrays.toString(arrows) + ", type=" + type
				+ "] " + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof CompBoard))
			return false;
		CompBoard other = (CompBoard) obj;
		if (!Arrays.equals(arrows, other.arrows))
			return false;
		if (!Arrays.deepEquals(board, other.board))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	
		
	
}
