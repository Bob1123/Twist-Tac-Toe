import java.awt.Color;

public enum PieceType {
	YELLOW (Color.YELLOW),
	BLACK (Color.BLACK);
	
	// ---------------------------------------------------------------------------------- Properties
	
	private final Color color;
	
	// ---------------------------------------------------------------------------------- Constructor
	
	PieceType(Color color) {
		this.color = color;
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public Color getColor() {
		return color;
	}
	
}
