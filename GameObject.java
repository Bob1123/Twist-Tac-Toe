import java.awt.Color;
import java.awt.Graphics;

public abstract class GameObject implements Drawable {

	// ---------------------------------------------------------------------------------- Properties
	
	private int x, y, width, height;
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void drawFillRect(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}
	
	public void drawFillRect(Graphics g, Color c) {
		drawFillRect(g, c, x, y, width, height);
	}
	
	public void drawFillOval(Graphics g, Color c, int x, int y, int width, int height) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
		g.setColor(Color.BLACK);
		g.fillOval(x, y, width, height);
	}
	
	public void drawFillOval(Graphics g, Color c) {
		drawFillOval(g, c, x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters

	public void setX(int x){
		this.x = x; 
	}

	public int getX(){
		return this.x;
	}

	public void setY(int y){
		this.y = y; 
	}

	public int getY(){
		return this.y;
	}

	//Width is always non negative
	public void setWidth(int width){
		if(width <= 0) this.width = 0; 
		else this.width = width;
	}

	public int getWidth(){
		return this.width;
	}

	//Height is always non negative
	public void setHeight(int height){
		if(height <= 0) this.height = 0;
		else this.height = height;
	}

	public int getHeight(){
		return this.height;
	}

	@Override
	public String toString() {
		return "GameObject [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof GameObject))
			return false;
		GameObject other = (GameObject) obj;
		if (height != other.getHeight())
			return false;
		if (width != other.getWidth())
			return false;
		if (x != other.getX())
			return false;
		if (y != other.getY())
			return false;
		return true;
	}
	
}
