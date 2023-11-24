package chess.piece;

import java.util.HashMap;
import java.util.Map;

import chess.board.Board;
import chess.common.File;
import chess.common.Location;
import chess.makeboard.Chessboard;
import chess.makeboard.PieceType;
import chess.squares.Square;

public final class PieceFactory {
    private PieceFactory() {}

    public static Map<Location, AbstractPiece> getPieces() {
        Map<Location, AbstractPiece> pieces = new HashMap<>();

        pieces.put(new Location(File.A, 1), new Rook(PieceColor.LIGHT));
        pieces.put(new Location(File.H, 1), new Rook(PieceColor.LIGHT));
        pieces.put(new Location(File.A, 8), new Rook(PieceColor.DARK));
        pieces.put(new Location(File.H, 8), new Rook(PieceColor.DARK));

        pieces.put(new Location(File.B, 1), new Knight(PieceColor.LIGHT));
        pieces.put(new Location(File.G, 1), new Knight(PieceColor.LIGHT));
        pieces.put(new Location(File.B, 8), new Knight(PieceColor.DARK));
        pieces.put(new Location(File.G, 8), new Knight(PieceColor.DARK));

        pieces.put(new Location(File.C, 1), new Bishop(PieceColor.LIGHT));
        pieces.put(new Location(File.F, 1), new Bishop(PieceColor.LIGHT));
        pieces.put(new Location(File.C, 8), new Bishop(PieceColor.DARK));
        pieces.put(new Location(File.F, 8), new Bishop(PieceColor.DARK));

        pieces.put(new Location(File.D, 1), new Queen(PieceColor.LIGHT));
        pieces.put(new Location(File.D, 8), new Queen(PieceColor.DARK));

        pieces.put(new Location(File.E, 1), new King(PieceColor.LIGHT));
        pieces.put(new Location(File.E, 8), new King(PieceColor.DARK));

        for (File file : File.values()) {
            pieces.put(new Location(file, 2), new Pawn(PieceColor.LIGHT));
            pieces.put(new Location(file, 7), new Pawn(PieceColor.DARK));
        }

        return pieces;
    }
    
    public static void addPieces(Board board, Chessboard chessboard) {
        Map<Location, AbstractPiece> pieces = getPieces();

        for (Map.Entry<Location, AbstractPiece> entry : pieces.entrySet()) {
            Location location = entry.getKey();
            AbstractPiece piece = entry.getValue();

            Square square = board.getLocationSquareMap().get(location);
            chessboard.addPiece(getPieceType(piece), square.getLocation().getFile().ordinal() + 1, 9-square.getLocation().getRank());
            square.setOccupied(true);
        }
    }
    
    public static PieceType getPieceType(AbstractPiece piece) {
        if (piece instanceof Rook) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_ROOK : PieceType.BLACK_ROOK;
        } else if (piece instanceof Knight) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_KNIGHT : PieceType.BLACK_KNIGHT;
        } else if (piece instanceof Bishop) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_BISHOP : PieceType.BLACK_BISHOP;
        } else if (piece instanceof Queen) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_QUEEN : PieceType.BLACK_QUEEN;
        } else if (piece instanceof King) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_KING : PieceType.BLACK_KING;
        } else if (piece instanceof Pawn) {
            return piece.getPieceColor() == PieceColor.LIGHT ? PieceType.WHITE_PAWN : PieceType.BLACK_PAWN;
        } else {
            throw new IllegalArgumentException("Unknown piece type: " + piece.getClass().getSimpleName());
        }
    }
    
    
}

