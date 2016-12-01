import java.awt.Graphics;
import java.awt.Point;

public class TwistArrow extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	private ArrowType type;
	private boolean used;
	
	private Point UL = new Point(getX(), getY());
	private Point UR = new Point(getX()+getWidth(), getY());
	private Point BL = new Point(getX(), getY()+getHeight());
	private Point BR = new Point(getX()+getWidth(), getY()+getHeight());
	
	// ---------------------------------------------------------------------------------- Constructors
	
	TwistArrow(boolean used) {
		
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void draw(Graphics g) {
		switch(type) {
		case NE:
			g.drawLine(BL.x, BL.y, UR.x, UR.y);
			g.drawLine(UR.x, UR.y, (UL.x+UR.x)/2, UR.y);
			g.drawLine(UR.x, UR.y, BR.x, (BR.y+UR.y)/2);
			break;
		case NW:
			g.drawLine(BR.x, BR.y, UL.x, UL.y);
			g.drawLine(UL.x, UL.y, (UR.x+UL.x)/2, UL.y);
			g.drawLine(UL.x, UL.y, BL.x, (BL.y+UL.y)/2);
			break;
		case SW:
			g.drawLine(UR.x, UR.y, BL.x, BL.y);
			g.drawLine(BL.x, BL.y, (BR.x+BL.x)/2, BL.y);
			g.drawLine(BL.x, BL.y, UL.x, (UL.y+BL.y)/2);
			break;
		case SE:
			g.drawLine(UL.x, UL.y, BR.x, BR.y);
			g.drawLine(BR.x, BR.y, (BL.x+BR.x)/2, BR.y);
			g.drawLine(BR.x, BR.y, UR.x, (UR.y+BR.y)/2);
			break;
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
	
}
