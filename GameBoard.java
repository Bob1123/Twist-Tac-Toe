import java.awt.Graphics;

public class GameBoard extends GameObject {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// The four CompBoard. Might change to Array.
	private CompBoard NW, NE, SW, SE;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	// Workhorse
	public GameBoard(CompBoard NW, CompBoard NE, CompBoard SW, CompBoard SE, int x, int y, int width, int height) {
		SetNE(NE);
		SetNW(NW);
		SetSE(SE);
		SetSW(SW);
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	// Use this constructor generally
	public GameBoard(int x, int y, int width, int height) {
		this(new CompBoard(CompType.NW, x, y, width/2, height/2), new CompBoard(CompType.NE, x+width/2, y, width/2, height/2),
				new CompBoard(CompType.SW, x, y+height/2, width/2, height/2), new CompBoard(CompType.SE, x+width/2, y+height/2, width/2, height/2),
				x, y, width, height);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// Draws GameBoard by drawing its CompBoards
	public void draw(Graphics g) {
		NW.draw(g);
		NE.draw(g);
		SW.draw(g);
		SE.draw(g);
	}
	
	// Used to see if there is a win in GameBoard
	public boolean checkWin() {
		return true;
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	
	
	@Override
	public String toString() {
		return "GameBoard [NW: "+ NW.toString()  + " NE: " + NE.toString() + " SW: " + SW.toString() 
			+ " SE: " + SE.toString() + "] " + super.toString();
	}
	
	public CompBoard getNW() {
		return NW;
	}

	public void SetNW(CompBoard NW) {
		NW = this.NW;
	}

	public CompBoard getNE() {
		return NE;
	}

	public void SetNE(CompBoard NE) {
		NE = this.NE;
	}

	public CompBoard getSW() {
		return SW;
	}

	public void SetSW(CompBoard SW) {
		SW = this.SW;
	}

	public CompBoard getSE() {
		return SE;
	}

	public void SetSE(CompBoard SE) {
		SE = this.SE;
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
