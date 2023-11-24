package chess.squares;

import chess.common.Location;
import chess.piece.AbstractPiece;

public class Square {
	private final SquareColor squareColor;
	private final Location location;
	private boolean isOccupied;
	private AbstractPiece currentPiece;

	public Square(SquareColor squareColor, Location location) {
		this.squareColor = squareColor;
		this.location = location;
		this.isOccupied = false;
	}

	public void reset() {
		this.isOccupied = false;
		this.currentPiece = null;
	}

	public AbstractPiece getCurrentPiece() {
		return currentPiece;
	}

	public void setCurrentPiece(AbstractPiece currentPiece) {
		this.currentPiece = currentPiece;
	}

	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}

	public SquareColor getSquareColor() {
		return squareColor;
	}

	public Location getLocation() {
		return location;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	@Override
	public String toString() {
		return "Square{" +
				"squareColor=" + squareColor +
				", location=Location{file=" + location.getFile() + " ,rank=" + location.getRank() + '}' +
				", isOccupied=" + isOccupied +
				'}';
	}
}
