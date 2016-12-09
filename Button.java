import java.awt.Graphics;

public class Button extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	private String type;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public Button(int x, int y, int width, int height, String type) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.type = type;
	}
	
	// Copy Constructor
	public Button(Button b) {
		this(b.getX(), b.getY(), b.getWidth(), b.getHeight(), b.getType());
	}
	
	// ---------------------------------------------------------------------------------- Methods

	@Override
	public void draw(Graphics g) {
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawString(type, getX() + getWidth()/3, getY() + getHeight()/2);
	}
	
	public Button clone() {
		return new Button(this);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Button [type=" + type + "] " + super.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Button))
			return false;
		Button other = (Button) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	
}
