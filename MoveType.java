import java.awt.Color;

public enum MoveType {
	PIECE (1),
	TWIST (2);
	
	// ---------------------------------------------------------------------------------- Properties
	
	private final int code;
	
	// ---------------------------------------------------------------------------------- Constructor
	
	MoveType(int code) {
		this.code = code;
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public int getCode() {
		return code;
	}
}
