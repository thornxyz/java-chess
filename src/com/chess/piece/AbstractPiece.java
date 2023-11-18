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
