import java.awt.Graphics;

public class GamePiece extends GameObject {
   
	// ---------------------------------------------------------------------------------- Properties
	
	private PieceType type;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public GamePiece(PieceType type) {
		
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void draw(Graphics g) {
		drawFillOval(g, type.getColor(), getX()+getWidth()/10, getY()+getHeight()/10, getWidth()*8/10, getHeight()*8/10);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
   
}
