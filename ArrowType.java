// Enum for direction of a TwistArrow
public enum ArrowType {
	POSITIVE (1),
	NEGATIVE (2),
	NEITHER (3);
	
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
