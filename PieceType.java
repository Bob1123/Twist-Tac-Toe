import java.awt.Color;

// Gives the color/player of a GamePiece. Blank belongs to no player.
// The board is initialized to blank pieces. Perhaps we change this
// so that null GamePieces are allowed instead of blank ones, and we
// only draw if null. Each PieceType has an associated color.
public enum PieceType {
	YELLOW (new Color(250, 250, 153), 1),
	BLACK (new Color(64, 64, 64), 2),
	BLANK (Color.WHITE, 3);
	
	// ---------------------------------------------------------------------------------- Properties
	
	private final Color color;
	private final int code;
	
	// ---------------------------------------------------------------------------------- Constructor
	
	PieceType(Color color, int code) {
		this.color = color;
		this.code = code;
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public Color getColor() {
		return color;
	}
	
	public int getCode() {
		return code;
	}
	
}
