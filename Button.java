import java.awt.Graphics;

public class Button extends GameObject {
	
	public String type;
	
	public Button(int x, int y, int width, int height, String type) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.type = type;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawString(type, getX() + getWidth()/3, getY() + getHeight()/2);
	}

}
