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
	
	@Override
	public String toString() {
		return "GameBoard [NW: "+ NW.toString()  + " NE: " + NE.toString() + " SW: " + SW.toString() 
			+ " SE: " + SE.toString() + "] " + super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GameBoard))
			return false;
		GameBoard other = (GameBoard) obj;
		if (!(NE.equals(other.getNE())))
			return false;
		if (!(NW.equals(other.getNW())))
			return false;
		if (!(SE.equals(other.getSE())))
			return false;
		if (!(SW.equals(other.getSW())))
			return false;
		return true;
	}
	
}
