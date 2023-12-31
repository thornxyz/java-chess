package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.board.Board;
import chess.common.Location;
import chess.common.LocationFactory;
import chess.squares.Square;

public class Pawn extends AbstractPiece {

	private boolean isFirstMove = true;

	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Pawn";
	}

	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Location current = this.getCurrentSquare().getLocation();
		int sign = (pieceColor.equals(PieceColor.BLACK)) ? -1 : 1;

		moveCandidates.add(LocationFactory.build(current, 0, sign));

		if (isFirstMove) {
			moveCandidates.add(LocationFactory.build(current, 0, 2 * sign));
			isFirstMove = false;
		}

		moveCandidates.add(LocationFactory.build(current, 1, sign));
		moveCandidates.add(LocationFactory.build(current, -1, sign));

		Map<Location, Square> squareMap = board.getLocationSquareMap();

		return moveCandidates.stream()
				.filter(squareMap::containsKey)
				.filter(candidate -> validateMove(candidate, squareMap))
				.collect(Collectors.toList());
	}

	private boolean validateMove(Location candidate, Map<Location, Square> squareMap) {
		Square destinationSquare = squareMap.get(candidate);
		if (!squareMap.containsKey(candidate)) {
			return false;
		}
		if (destinationSquare.isOccupied()) {
			if (candidate.getFile().equals(this.getCurrentSquare().getLocation().getFile())) {
				return false;
			} else {
				return true;
			}
		} else {
			if (!candidate.getFile().equals(this.getCurrentSquare().getLocation().getFile())) {
				return false;
			}
		}

		return true;
	}

	@Override
	public List<Location> getValidMoves(Board board, Square square) {
		List<Location> moveCandidates = new ArrayList<>();
		Location current = square.getLocation();
		int sign = (pieceColor.equals(PieceColor.BLACK)) ? -1 : 1;

		moveCandidates.add(LocationFactory.build(current, 0, sign));

		if (isFirstMove) {
			moveCandidates.add(LocationFactory.build(current, 0, 2 * sign));
			isFirstMove = false;
		}

		moveCandidates.add(LocationFactory.build(current, 1, sign));
		moveCandidates.add(LocationFactory.build(current, -1, sign));

		Map<Location, Square> squareMap = board.getLocationSquareMap();

		return moveCandidates.stream()
				.filter(squareMap::containsKey)
				.filter(candidate -> validateMove(candidate, squareMap))
				.collect(Collectors.toList());
	}

}
