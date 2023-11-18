package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.common.LocationFactory;
import com.chess.squares.Square;

public class Knight extends AbstractPiece {

    public Knight(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Knight";
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = this.getCurrentSquare().getLocation();
        getMoves(moveCandidates, squareMap, current, 2, 1);
        getMoves(moveCandidates, squareMap, current, 2, -1);
        getMoves(moveCandidates, squareMap, current, -2, 1);
        getMoves(moveCandidates, squareMap, current, -2, -1);
        getMoves(moveCandidates, squareMap, current, 1, 2);
        getMoves(moveCandidates, squareMap, current, -1, -2);
        getMoves(moveCandidates, squareMap, current, 1, -2);
        getMoves(moveCandidates, squareMap, current, -1, 2);
        return moveCandidates;
    }

    private void getMoves(List<Location> candidates, Map<Location, Square> squareMap, Location current, int rankOffset, int fileOffset) {
        try {
            Location next = LocationFactory.build(current, fileOffset, rankOffset);
            if (squareMap.containsKey(next)) {
                if (!squareMap.get(next).isOccupied() || !squareMap.get(next).getCurrentPiece().getPieceColor().equals(this.pieceColor)) {
                    candidates.add(next);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }

    @Override
    public void makeMove(Board board, Square square) {
        List<Location> validMoves = getValidMoves(board);

        if (validMoves.contains(square.getLocation())) {
            this.currentSquare.setOccupied(false);
            this.setCurrentSquare(square);
            square.setCurrentPiece(this);
            square.setOccupied(true);
        } else {
            System.out.println("Invalid move. Please try again.");
        }
    }

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        List<Location> moveCandidates = new ArrayList<>();
        Map<Location, Square> squareMap = board.getLocationSquareMap();
        Location current = square.getLocation();
        getMoves(moveCandidates, squareMap, current, 2, 1);
        getMoves(moveCandidates, squareMap, current, 2, -1);
        getMoves(moveCandidates, squareMap, current, -2, 1);
        getMoves(moveCandidates, squareMap, current, -2, -1);
        getMoves(moveCandidates, squareMap, current, 1, 2);
        getMoves(moveCandidates, squareMap, current, -1, -2);
        getMoves(moveCandidates, squareMap, current, 1, -2);
        getMoves(moveCandidates, squareMap, current, -1, 2);
        return moveCandidates;
    }
}
