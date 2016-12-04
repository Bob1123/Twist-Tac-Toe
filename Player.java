public class Player {
	
	// ---------------------------------------------------------------------------------- Properties
	
	// Variables keep track of player's number (=color) and their turn
	public boolean myTurn;
	public int playerNumber;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public Player(boolean myTurn, int playerNumber) {
		setMyTurn(myTurn);
		setPlayerNumber(playerNumber);
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
	
}