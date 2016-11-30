import java.awt.Graphics;

public class GamePiece extends GameObject {
   
	// ---------------------------------------------------------------------------------- Properties
	
	private PieceType type;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public GamePiece(PieceType type) {
		setType(type);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public GamePiece clone() {
		return new GamePiece(getType());
	}
	
	public void draw(Graphics g) {
		drawFillOval(g, type.getColor(), getX()+getWidth()/10, getY()+getHeight()/10, getWidth()*8/10, getHeight()*8/10);
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
		return "GamePiece [type=" + type + ", x=" + getX() + ", y=" + getY() + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		// Figure this out later
		return true;
	}
   
}
