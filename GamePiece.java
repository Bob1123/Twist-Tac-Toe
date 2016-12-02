import java.awt.Graphics;

public class GamePiece extends GameObject {
   
	// ---------------------------------------------------------------------------------- Properties
	
	// Essentially the color/player of the GamePiece
	private PieceType type;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public GamePiece(PieceType type, int x, int y, int width, int height) {
		setType(type);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Initialize a blank piece
	public GamePiece(int x, int y, int width, int height) {
		this(PieceType.BLANK, x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public GamePiece clone() {
		return new GamePiece(getType(), getX(), getY(), getWidth(), getHeight());
	}
	
	// Draw a GamePiece
	public void draw(Graphics g) {
		drawFillOval(g, type.getColor(), getX()+getWidth()/10, getY()+getHeight()/10, getWidth()*8/10, getHeight()*8/10);
	}
	
	// Method to return if this piece is the same color as another
	public boolean samePlayer(GamePiece p) {
		return p.getType() == getType();
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public PieceType getType() {
		return type;
	}

	public void setType(PieceType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GamePiece [type=" + type + "] " + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GamePiece))
			return false;
		GamePiece other = (GamePiece) obj;
		if (type != other.type)
			return false;
		return true;
	}
	

   
}
