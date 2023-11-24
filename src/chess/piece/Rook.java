package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.board.Board;
import chess.common.Location;
import chess.common.LocationFactory;
import chess.squares.Square;

public class Rook extends AbstractPiece {

    public Rook(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Rook";
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = this.getCurrentSquare().getLocation();
        getMoves(moveCandidates, squareMap, current, 1, 0);
        getMoves(moveCandidates, squareMap, current, -1, 0);
        getMoves(moveCandidates, squareMap, current, 0, -1);
        getMoves(moveCandidates, squareMap, current, 0, 1);
        return moveCandidates;
    }

    private static final int MAX_MOVES = 7;

    private void getMoves(
            List<Location> candidates,
            Map<Location, Square> squareMap,
            Location current,
            int fileOffset,
            int rankOffset) {
        try {
            Location next = LocationFactory.build(current, fileOffset, rankOffset);
            int moves = 0;
            while (squareMap.containsKey(next) && moves < MAX_MOVES) {
                if (squareMap.get(next).isOccupied()) {
                    if (!squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.pieceColor)) {
                        candidates.add(next);
                    }
                    break;
                }
                candidates.add(next);
                next = LocationFactory.build(next, fileOffset, rankOffset);
                moves++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = square.getLocation();
        getMoves(moveCandidates, squareMap, current, 1, 0);
        getMoves(moveCandidates, squareMap, current, -1, 0);
        getMoves(moveCandidates, squareMap, current, 0, -1);
        getMoves(moveCandidates, squareMap, current, 0, 1);
        return moveCandidates;
    }
}
