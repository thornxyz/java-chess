package chess.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.common.File;
import chess.common.Location;
import chess.piece.AbstractPiece;
import chess.piece.PieceColor;
import chess.piece.PieceFactory;
import chess.squares.Square;
import chess.squares.SquareColor;

public class Board {
	private static final Integer BOARD_LENGTH = 8;
	private final Map<Location, Square> locationSquareMap;

	Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];

	private final List<AbstractPiece> lightPieces = new ArrayList<>();
	private final List<AbstractPiece> darkPieces = new ArrayList<>();

	public Board() {
		locationSquareMap = new HashMap<>();
		Map<Location, AbstractPiece> pieces = PieceFactory.getPieces();
		for (int i = 0; i < boardSquares.length; i++) {
			int column = 0;
			SquareColor currentColor = (i % 2 == 0) ? SquareColor.LIGHT : SquareColor.DARK;
			for (File file : File.values()) {
				Square newSquare = new Square(currentColor, new Location(file, BOARD_LENGTH - i));
				if (pieces.containsKey(newSquare.getLocation())) {
					AbstractPiece piece = pieces.get(newSquare.getLocation());
					newSquare.setCurrentPiece(piece);
					newSquare.setOccupied(true);
					piece.setCurrentSquare(newSquare);
					if (piece.getPieceColor().equals(PieceColor.BLACK)) {
						darkPieces.add(piece);
					} else {
						lightPieces.add(piece);
					}
				}
				boardSquares[i][column] = newSquare;
				locationSquareMap.put(newSquare.getLocation(), newSquare);
				currentColor = (currentColor == SquareColor.DARK) ? SquareColor.LIGHT : SquareColor.DARK;
				column++;
			}
		}
	}

	public Map<Location, Square> getLocationSquareMap() {
		return locationSquareMap;
	}

	public List<AbstractPiece> getLightPieces() {
		return lightPieces;
	}

	public List<AbstractPiece> getDarkPieces() {
		return darkPieces;
	}

}
