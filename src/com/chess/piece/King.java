package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.squares.Square;

public class King extends AbstractPiece {

    private AbstractPiece rook;
    private AbstractPiece bishop;

    public King(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "King";
        this.bishop = new Bishop(pieceColor);
        this.rook = new Rook(pieceColor);
    }

    public King(PieceColor pieceColor, AbstractPiece bishop, AbstractPiece rook) {
        super(pieceColor);
        this.name = "King";
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));

        Location current = this.getCurrentSquare().getLocation();
        return moveCandidates.stream()
                .filter(candidate -> Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) <= 1 &&
                        Math.abs(candidate.getRank() - current.getRank()) <= 1)
                .collect(Collectors.toList());
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

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(rook.getValidMoves(board, square));
        moveCandidates.addAll(bishop.getValidMoves(board, square));
        Location current = square.getLocation();
        return moveCandidates.stream()
                .filter(candidate -> Math.abs(candidate.getFile().ordinal() - current.getFile().ordinal()) <= 1 &&
                        Math.abs(candidate.getRank() - current.getRank()) <= 1)
                .collect(Collectors.toList());
    }
}
