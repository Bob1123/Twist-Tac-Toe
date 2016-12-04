import java.awt.Color;
import java.awt.Graphics;

public class TwistArrow extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// type gives arrow direction, used tells whether it the arrow was just clicked.
	private ArrowType type;
	private boolean used;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public TwistArrow(boolean used, ArrowType type, int x, int y, int width, int height) {
		setType(type);
		setUsed(used);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Default TwistArrow is not "used".
	public TwistArrow(ArrowType type, int x, int y, int width, int height) {
		this(false, type, x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public TwistArrow clone() {
		return new TwistArrow(isUsed(), getType(), getX(), getY(), getWidth(), getHeight());
	}
	
	// Draws TwistArrow depending on type
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		if(getType() == ArrowType.Positive) {
			g.drawArc(getX() + getWidth()/6, getY() + getHeight()/3, getWidth()*2/3, 2*getHeight()*2/3, 0, 90);
			g.drawLine(getX() + getWidth()/2, getY() + getHeight()/3, getX() + getWidth()*2/3, getY() + getHeight()/6);
			g.drawLine(getX() + getWidth()/2, getY() + getHeight()/3, getX() + getWidth()*2/3, getY() + getHeight()*2/3);
		}
		if(getType() == ArrowType.Negative) {
			g.drawArc(getX() + getWidth()/6, getY() - getHeight()*2/3, getWidth()*2/3, 2*getHeight()*2/3, 180, 90);
			g.drawLine(getX() + getWidth()/6, getY(), getX() + getWidth()/6, getY() + getHeight()/2);
			g.drawLine(getX() + getWidth()/6, getY(), getX() + getWidth()/3, getY() + getHeight()/3);
		}
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public ArrowType getType() {
		return type;
	}

	public void setType(ArrowType type) {
		this.type = type;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "TwistArrow [type=" + type + ", used=" + used + "] " + super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof TwistArrow))
			return false;
		TwistArrow other = (TwistArrow) obj;
		if (used != other.isUsed())
			return false;
		if (type != other.getType())
			return false;
		return true;
	}
	
	
}
