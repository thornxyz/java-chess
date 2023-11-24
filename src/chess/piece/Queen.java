package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import chess.board.Board;
import chess.common.Location;
import chess.squares.Square;

public class Queen extends AbstractPiece {

    private AbstractPiece bishop;
    private AbstractPiece rook;

    public Queen(PieceColor pieceColor) {
        super(pieceColor);
        this.name = "Queen";
        this.bishop= new Bishop(pieceColor);
        this.rook=new Rook(pieceColor);
    }


    @Override
    public List<Location> getValidMoves(Board board) {
        List<Location> moveCandidates = new ArrayList<>();
        moveCandidates.addAll(bishop.getValidMoves(board, this.getCurrentSquare()));
        moveCandidates.addAll(rook.getValidMoves(board, this.getCurrentSquare()));
        return moveCandidates;
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

