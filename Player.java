public class Player {
	
	// ---------------------------------------------------------------------------------- Properties
	
	public boolean myTurn;
	public int playerNumber;
	
	// ---------------------------------------------------------------------------------- Constructors
	
	public Player(boolean myTurn, int playerNumber) {
		setMyTurn(myTurn);
		setPlayerNumber(playerNumber);
	}
	
	// ---------------------------------------------------------------------------------- Methods
	
	public void swapTurns() {
		setMyTurn(!isMyTurn());
	}
	
	public PieceType getPiece() {
		if(playerNumber == 0) {
			return PieceType.BLACK;
		}
		if(playerNumber == 1) {
			return PieceType.YELLOW;
		}
		return PieceType.BLANK;
	}
	
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