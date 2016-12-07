import java.awt.Point;
import java.io.RandomAccessFile;

public class GameMove {
	
	// ---------------------------------------------------------------------------------- Properties
	
	private final MoveType type;
	private ArrowType twistType;
	private PieceType pieceType;
	private Point boardCoord;
	private Point compCoord;
	private TwistArrow[] arrows;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public GameMove(MoveType type, ArrowType twistType, PieceType pieceType, Point boardCoord, Point compCoord, TwistArrow[] arrows) {
		this.type = type;
		setTwistType(twistType);
		setPieceType(pieceType);
		setBoardCoord(boardCoord);
		setCompCoord(compCoord);
		setArrows(arrows);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void save(RandomAccessFile raf, GameBoard playField) throws Exception {
		raf.seek(raf.length());
		System.out.println(raf.length());
		raf.writeByte(getType().getCode());
		raf.writeByte(getTwistType().getCode());
		raf.writeByte(getPieceType().getCode());
		raf.writeInt(getBoardCoord().x);
		raf.writeInt(getBoardCoord().y);
		raf.writeInt(getCompCoord().x);
		raf.writeInt(getCompCoord().y);
		raf.writeInt(getArrows().length);
		for(TwistArrow t : arrows) {
			raf.writeInt(t.getX());
			raf.writeInt(t.getY());
		}
	}
	
	public static GameMove load(RandomAccessFile raf, GameBoard playField) throws Exception {
		int moveCode = raf.readByte();
		MoveType thisMoveType = null;
		for(MoveType type : MoveType.values()) {
			if(moveCode == type.getCode()) {
				thisMoveType = type;
			}
		}
		int arrowCode = raf.readByte();
		ArrowType thisArrowType = null;
		for(ArrowType type : ArrowType.values()) {
			if(arrowCode == type.getCode()) {
				thisArrowType = type;
			}
		}
		int pieceCode = raf.readByte();
		PieceType thisPieceType = null;
		for(PieceType type : PieceType.values()) {
			if(pieceCode == type.getCode()) {
				thisPieceType = type;
			}
		}
		Point thisBoardCoord = new Point(raf.readInt(), raf.readInt());
		Point thisCompCoord = new Point(raf.readInt(), raf.readInt());
		int arrowArrayLength = raf.readInt();
		TwistArrow[] thisArrowArray = new TwistArrow[arrowArrayLength];
		int nextX;
		int nextY;
		for(int i = 0; i < arrowArrayLength; i++) {
			nextX = raf.readInt();
			nextY = raf.readInt();
			for(TwistArrow[] row : playField.getArrows().getBoard()) {
				for(TwistArrow arrow : row) {
					if(arrow.getX() == nextX && arrow.getY() == nextY) {
						thisArrowArray[i] = arrow;
					}
				}
			}
		}
		return new GameMove(thisMoveType, thisArrowType, thisPieceType, thisBoardCoord, thisCompCoord, thisArrowArray);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public ArrowType getTwistType() {
		return twistType;
	}

	public void setTwistType(ArrowType twistType) {
			this.twistType = twistType;
	}

	public PieceType getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceType pieceType) {
			this.pieceType = pieceType;
	}

	public Point getBoardCoord() {
		return boardCoord;
	}

	public void setBoardCoord(Point boardCoord) {
		this.boardCoord = boardCoord;
	}

	public Point getCompCoord() {
		return compCoord;
	}

	public void setCompCoord(Point compCoord) {
			this.compCoord = compCoord;
	}

	public TwistArrow[] getArrows() {
		return arrows;
	}

	public void setArrows(TwistArrow[] arrows) {
		this.arrows = arrows;
	}

	public MoveType getType() {
		return type;
	}
	
}
