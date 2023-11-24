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
        getRankCandidates(moveCandidates, squareMap, current, -1);
        getRankCandidates(moveCandidates, squareMap, current, 1);
        getFileCandidates(moveCandidates, squareMap, current, -1);
        getFileCandidates(moveCandidates, squareMap, current, 1);
        return moveCandidates;
    }

    private void getRankCandidates(
    		List<Location> moveCandidates, 
    		Map<Location, Square> squareMap, 
    		Location current,
            int offset) {
        try {
            Location next = LocationFactory.build(current, current.getFile().ordinal(), offset);
            while (squareMap.containsKey(next)) {
                if (squareMap.get(next).isOccupied()) {
                    if (squareMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
                        break;
                    }
                    moveCandidates.add(next);
                    break;
                }
                moveCandidates.add(next);
                next = LocationFactory.build(next, next.getFile().ordinal(), offset);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getFileCandidates(
    		List<Location> moveCandidates, 
    		Map<Location, Square> squareMap, 
    		Location current,
            int offset) {
        try {
            Location next = LocationFactory.build(current, offset, 0);
            while (squareMap.containsKey(next)) {
                if (squareMap.get(next).isOccupied()) {
                    if (squareMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
                        break;
                    }
                    moveCandidates.add(next);
                    break;
                }
                moveCandidates.add(next);
                next = LocationFactory.build(next, 0, offset);
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
        getRankCandidates(moveCandidates, squareMap, current, -1);
        getRankCandidates(moveCandidates, squareMap, current, 1);
        getFileCandidates(moveCandidates, squareMap, current, -1);
        getFileCandidates(moveCandidates, squareMap, current, 1);
        return moveCandidates;
    }
}

