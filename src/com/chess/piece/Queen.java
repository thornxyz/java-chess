package com.chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.chess.board.Board;
import com.chess.common.Location;
import com.chess.squares.Square;

public class Queen extends AbstractPiece {

    private AbstractPiece bishop;
    private AbstractPiece rook;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Queen";
        this.bishop= new Bishop(pieceColor);
        this.rook=new Rook(pieceColor);
    }

    public Queen(PieceColor pieceColor, AbstractPiece bishop, AbstractPiece rook) {
        super(pieceColor);
        this.bishop = bishop;
        this.rook = rook;
    }

    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
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

    @Override
    public List<Location> getValidMoves(Board board, Square square) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, square));
        moveCandidates.addAll(rook.getValidMoves(board, square));
        return moveCandidates.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
