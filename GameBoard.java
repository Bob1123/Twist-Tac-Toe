import java.awt.Graphics;

public class GameBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	private CompBoard NW, NE, SW, SE;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public GameBoard(CompBoard NW, CompBoard NE, CompBoard SW, CompBoard SE) {
		
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void draw(Graphics g) {
		NW.draw(g);
		NE.draw(g);
		SW.draw(g);
		SE.draw(g);
	}
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
}
