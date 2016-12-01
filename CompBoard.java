import java.awt.Color;
import java.awt.Graphics;

public class CompBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	private GamePiece[][] board = new GamePiece[3][3];
	private TwistArrow[] arrows;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public CompBoard(GamePiece[][] board, TwistArrow[] arrows) {
		
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawLine(getX()+getWidth()/3, getY(), getX()+getWidth()/3, getY()+getHeight());
		g.drawLine(getX()+getWidth()*2/3, getY(), getX()+getWidth()*2/3, getY()+getHeight());
		g.drawLine(getX(), getY()+getHeight()/3, getX(), getY()+getHeight()/3);
		g.drawLine(getX(), getY()+getHeight()*2/3, getX(), getY()+getHeight()*2/3);
		for(GamePiece[] row : board) {
			for(GamePiece p : row) {
				if(p != null) {
					p.draw(g);
				}
			}
		}
		for(TwistArrow t : arrows) {
			if(!t.isUsed()) {
				t.draw(g);
			}
		}
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
}
