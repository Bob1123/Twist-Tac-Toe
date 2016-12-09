import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class GameBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Gives the number of CompBoards in GameBoard
	public static final int ROWS = 3;
	public static final int COLUMNS = 3;
	
	// Array of CompBoards and an ArrowBoard, along with an ArrayList of moves
	private CompBoard[][] board = new CompBoard[ROWS][COLUMNS];
	private ArrowBoard arrows;
	private ArrayList<GameMove> moves;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public GameBoard(CompBoard[][] board, ArrowBoard arrows, ArrayList<GameMove> moves, int x, int y, int width, int height) {
		setBoard(board);
		setArrows(arrows);
		setMoves(moves);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Use this constructor generally
	public GameBoard(int x, int y, int width, int height) {
		this(createBoard(x, y, width, height), createArrows(x+width*11/10, y+height/3, width/2, height/2), new ArrayList<GameMove>(), x, y, width, height);
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
	
	public void save(RandomAccessFile raf) throws IOException {
		raf.writeInt(moves.size());
		for(GameMove m : moves) {
			System.out.println(moves.size());
			System.out.println(m.getTwistType());
			System.out.println("Trying to save move.");
			try {
				m.save(raf, this);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void load(RandomAccessFile raf) throws Exception {
		moves.clear();
		int length = raf.readInt();
		for(int i = 0; i < length; i++) {
			moves.add(GameMove.load(raf, this));
		}
	}
	
	public void run() {
		for(GameMove m : moves) {
			playMove(m);
		}
	}
	
	public GameMove getMove(Point p, PieceType type) {
		Point compCell = getPosition(p);
		if(compCell.x != -1 && compCell.y != -1) {
			Point pieceCell = getBoard()[compCell.x][compCell.y].getPosition(p);
			if(getBoard()[compCell.x][compCell.y].getBoard()[pieceCell.x][pieceCell.y].getType() == PieceType.BLANK){
				int arrowCounter = 0;
				for(TwistArrow[] row : getArrows().getBoard()) {
					for(TwistArrow t : row) {
						if(t.isUsed()) {
							arrowCounter++;
						}
					}
				}
				TwistArrow[] usedArrows = new TwistArrow[arrowCounter];
				arrowCounter = 0;
				for(TwistArrow[] row : getArrows().getBoard()) {
					for(TwistArrow t : row) {
						if(t.isUsed()) {
							usedArrows[arrowCounter] = t;
							arrowCounter++;
						}
					}
				}
				return new GameMove(MoveType.PIECE, ArrowType.Neither, type, compCell, pieceCell, usedArrows);
			}
		}
		
		Point twistCell = getArrows().getPosition(p);
		Point exactTwistCell = getArrows().getExactPosition(p);
		if(twistCell.x != -1 && twistCell.y != -1) {
			if(!getArrows().getBoard()[exactTwistCell.x][exactTwistCell.y].isUsed()) {
				int arrowCounter = 0;
				for(TwistArrow[] row : getArrows().getBoard()) {
					for(TwistArrow t : row) {
						if(t.isUsed()) {
							arrowCounter++;
						}
					}
				}
				TwistArrow[] usedArrows = new TwistArrow[arrowCounter];
				arrowCounter = 0;
				for(TwistArrow[] row : getArrows().getBoard()) {
					for(TwistArrow t : row) {
						if(t.isUsed()) {
							usedArrows[arrowCounter] = t;
							arrowCounter++;
						}
					}
				}
				return new GameMove(MoveType.TWIST, getArrows().getDirection(p), PieceType.BLANK, twistCell, new Point(0,0), usedArrows);
			}
		}
		
		return null;
	}
	
	public void playMove(GameMove m) {
		if(m.getType() == MoveType.PIECE) {
			getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].getBoard()[m.getCompCoord().x][m.getCompCoord().y].setType(m.getPieceType());
			for(TwistArrow[] row : getArrows().getBoard()) {
				for(TwistArrow t : row) {
					t.setUsed(false);
				}
			}
		} else if(m.getType() == MoveType.TWIST) {
			if(m.getTwistType() == ArrowType.Negative) {
				getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
				getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
			}
			getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
			if(m.getTwistType() == ArrowType.Positive) {
				getArrows().getBoard()[m.getBoardCoord().x*2][m.getBoardCoord().y].setUsed(true);
			} else if(m.getTwistType() == ArrowType.Negative) {
				getArrows().getBoard()[m.getBoardCoord().x*2+1][m.getBoardCoord().y].setUsed(true);
			}
		}
	}
	
	public boolean tryMove(Point p, PieceType type) {
		GameMove m = getMove(p, type);
		if(m == null) {
			System.out.println("null");
			return false;
		}
		System.out.println(m.getPieceType());
		System.out.println(m.getType());
		System.out.println(m.getBoardCoord());
		System.out.println(m.getCompCoord());
		moves.add(m);
		playMove(m);
		return true;
	}
	
	public void undo() {
		if(moves.size() == 0) return;
		GameMove m = moves.get(moves.size()-1);
		moves.remove(moves.size()-1);
		if(m.getType() == MoveType.PIECE) {
			getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].getBoard()[m.getCompCoord().x][m.getCompCoord().y].setType(PieceType.BLANK);
		} else if(m.getType() == MoveType.TWIST) {
			if(m.getTwistType() == ArrowType.Positive) {
				getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
				getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
			}
			getBoard()[m.getBoardCoord().x][m.getBoardCoord().y].twist();
			if(m.getTwistType() == ArrowType.Negative) {
				getArrows().getBoard()[m.getBoardCoord().x*2][m.getBoardCoord().y].setUsed(false);
			} else if(m.getTwistType() == ArrowType.Positive) {
				getArrows().getBoard()[m.getBoardCoord().x*2+1][m.getBoardCoord().y].setUsed(false);
			}
		}
		for(TwistArrow[] row : getArrows().getBoard()) {
			for(TwistArrow t : row) {
				t.setUsed(false);
			}
		}
		for(TwistArrow t : m.getArrows()) {
			t.setUsed(true);
		}
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
		int rows = ROWS * CompBoard.ROWS;
		int columns = COLUMNS * CompBoard.COLUMNS;
		GamePiece[][] board = getWholeBoard();
		boolean result = true;
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < columns; c++){
				result = result &&
						!checkWin(r,c,Direction.Left) &&
						!checkWin(r,c,Direction.Right) && 
						!checkWin(r,c,Direction.Up) &&
						!checkWin(r,c,Direction.UpLeft) &&
						!checkWin(r,c,Direction.UpRight) &&
						!checkWin(r,c,Direction.Down) &&
						!checkWin(r,c,Direction.DownLeft) &&
						!checkWin(r,c,Direction.DownRight);
			}
		}
		
		return !result;
	}
	public enum Direction{
		Left,
		Right,
		Up,
		UpLeft,
		UpRight,
		Down,
		DownRight,
		DownLeft,
	}
	private boolean checkWin(int r, int c, Direction dir){
		int rows = ROWS * CompBoard.ROWS;
		int columns = COLUMNS * CompBoard.COLUMNS;
		GamePiece[][] board = getWholeBoard();
		boolean result = true;
		if(board[r][c].getType()==PieceType.BLANK){
			return false;
		}
		switch(dir){
		case Up:
			if(r <= WIN-1){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r-i][c].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case UpLeft:
			if(r <= WIN || c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r-i][c-i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case UpRight:
			if(r <= WIN || columns - c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r-i][c+i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case Down:
			if(rows - r <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r+i][c].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case DownLeft:
			if(rows - r <= WIN || c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r+i][c-i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case DownRight:
			if(rows - r <= WIN || columns - c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r+i][c+i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case Left:
			if(c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r][c-i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		case Right:
			if(columns - c <= WIN){
				result = result && true;
			}else{
				int count = 0;
				for(int i = 1; i < WIN; i++){
					if(board[r][c].getType()==board[r][c+i].getType()) count++;
				}
				result = result && (count < WIN-1);
			}
			break;
		}
		return !result;
	}

	
	public GameBoard clone() {
		return new GameBoard(getBoard(), getArrows(), getMoves(), getX(), getY(), getWidth(), getHeight());
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public GamePiece[][] getWholeBoard(){
		int wholeBoardRows = ROWS * CompBoard.ROWS;
		int wholeBoardColumns = COLUMNS * CompBoard.COLUMNS;
		GamePiece[][] board = new GamePiece[wholeBoardRows][wholeBoardColumns];
		
		for(int row = 0; row < wholeBoardRows; row++){
			for(int col = 0; col < wholeBoardColumns; col++){
			board[row][col] = getBoard()[row/ROWS][col/COLUMNS].getBoard()
					[row%CompBoard.ROWS][col%CompBoard.COLUMNS];
			}
		}
		return board;
	}
	
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

	public ArrayList<GameMove> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<GameMove> moves) {
		this.moves = moves;
	}	
	
}
