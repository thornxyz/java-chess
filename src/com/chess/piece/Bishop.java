package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationFactory;
import com.chess.squares.Square;

public class Bishop extends AbstractPiece {

    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Bishop";
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = this.getCurrentSquare().getLocation();
        getDiagonalMoves(moveCandidates, squareMap, current, 1, 1);
        getDiagonalMoves(moveCandidates, squareMap, current, 1, -1);
        getDiagonalMoves(moveCandidates, squareMap, current, -1, -1);
        getDiagonalMoves(moveCandidates, squareMap, current, -1, 1);
        return moveCandidates;
    }

    private void getDiagonalMoves(List<Location> candidates, Map<Location, Square> squareMap, Location current,
            int fileOffset, int rankOffset) {
        try {
            Location next = LocationFactory.build(current, fileOffset, rankOffset);
            while (squareMap.containsKey(next)) {
                if (squareMap.get(next).isOccupied()) {
                    if (!squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.pieceColor)) {
                        candidates.add(next);
                    }
                    break;
                }
                candidates.add(next);
                next = LocationFactory.build(next, fileOffset, rankOffset);
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
        getDiagonalMoves(moveCandidates, squareMap, current, 1, 1);
        getDiagonalMoves(moveCandidates, squareMap, current, 1, -1);
        getDiagonalMoves(moveCandidates, squareMap, current, -1, -1);
        getDiagonalMoves(moveCandidates, squareMap, current, -1, 1);
        return moveCandidates;
    }

    @Override
    public void makeMove(Board board, Square square) {
        List<Location> validMoves = getValidMoves(board, this.getCurrentSquare());

        if (validMoves.contains(square.getLocation())) {
            this.getCurrentSquare().setOccupied(false);
            this.setCurrentSquare(square);
            square.setCurrentPiece(this);
            square.setOccupied(true);
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }
}
