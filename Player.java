public class Player {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Variables keep track of player's number (=color) and their turn
	private boolean myTurn;
	private int playerNumber;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public Player(boolean myTurn, int playerNumber) {
		setMyTurn(myTurn);
		setPlayerNumber(playerNumber);
	}
	
	// Copy Constructor
	public Player(Player p) {
		this(p.isMyTurn(), p.getPlayerNumber());
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	// Switches status of the turn of this player
	public void swapTurns() {
		setMyTurn(!isMyTurn());
	}
	
	// Gets the PieceType of this player based on their number
	public PieceType getPiece() {
		if(playerNumber == 0) {
			return PieceType.BLACK;
		}
		if(playerNumber == 1) {
			return PieceType.YELLOW;
		}
		return PieceType.BLANK;
	}
	
	public Player clone() {
		return new Player(this);
	}
	
	// Need to add methods for loading and saving player stats to a file
	
	// ---------------------------------------------------------------------------------- Getters and Setters
	
	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	@Override
	public String toString() {
		return "Player [myTurn=" + myTurn + ", playerNumber=" + playerNumber + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Player))
			return false;
		Player other = (Player) obj;
		if (myTurn != other.myTurn)
			return false;
		if (playerNumber != other.playerNumber)
			return false;
		return true;
	}
	
	
	
}