import java.awt.Color;

// Gives the color/player of a GamePiece. Blank belongs to no player.
// The board is initialized to blank pieces. Perhaps we change this
// so that null GamePieces are allowed instead of blank ones, and we
// only draw if null.
public enum PieceType {
	YELLOW (Color.YELLOW),
	BLACK (Color.BLACK),
	BLANK (Color.WHITE);
	
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
