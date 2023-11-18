package com.chess.piece;

import java.util.List;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.squares.Square;

public abstract class AbstractPiece {
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;

	List<Location> getValidMoves(Board board) {
		return null;
	};

	List<Location> getValidMoves(Board board, Square square) {
		return null;
	};

	public void makeMove(Board board, Square square) {
		Square currentSquare = this.getCurrentSquare();
	
		// Save the state of the current square
		boolean wasOccupied = currentSquare.isOccupied();
		AbstractPiece previousPiece = currentSquare.getCurrentPiece();
	
		// Attempt to make the move
		List<Location> validMoves = getValidMoves(board, currentSquare);
		if (validMoves.contains(square.getLocation())) {
			// Move is valid, update the board
			currentSquare.setOccupied(false);
			currentSquare.setCurrentPiece(null);
	
			this.setCurrentSquare(square);
			square.setCurrentPiece(this);
			square.setOccupied(true);
		} else {
			// Invalid move, revert the state of the current square
			currentSquare.setOccupied(wasOccupied);
			currentSquare.setCurrentPiece(previousPiece);
	
			System.out.println("Invalid move. The board remains unchanged.");
		}
	}
	
	

	public AbstractPiece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	public String getName() {
		return name;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}

	public Square getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Square currentSquare) {
		this.currentSquare = currentSquare;
	}

	@Override
	public String toString() {
		return "AbstractPiece {name='" + getName() + "', pieceColor=" + getPieceColor() + ", currentSquare="
				+ getCurrentSquare() + "}";
	}

}
