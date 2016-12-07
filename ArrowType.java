// Enum for direction of a TwistArrow
public enum ArrowType {
	Positive (1),
	Negative (2),
	Neither (3);
	
	// ---------------------------------------------------------------------------------- Properties
	
	private final int code;
	
	// ---------------------------------------------------------------------------------- Constructor
	
	ArrowType(int code) {
		this.code = code;
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public int getCode() {
		return code;
	}
}
