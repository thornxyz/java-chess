package chess.makeboard;

public enum PieceType {
    BLACK_ROOK("chess-pieces/black-rook.png"),
    BLACK_KNIGHT("chess-pieces/black-knight.png"),
    BLACK_BISHOP("chess-pieces/black-bishop.png"),
    BLACK_QUEEN("chess-pieces/black-queen.png"),
    BLACK_KING("chess-pieces/black-king.png"),
    BLACK_PAWN("chess-pieces/black-pawn.png"),
    WHITE_ROOK("chess-pieces/white-rook.png"),
    WHITE_KNIGHT("chess-pieces/white-knight.png"),
    WHITE_BISHOP("chess-pieces/white-bishop.png"),
    WHITE_QUEEN("chess-pieces/white-queen.png"),
    WHITE_KING("chess-pieces/white-king.png"),
    WHITE_PAWN("chess-pieces/white-pawn.png");

    private final String imagePath;

    PieceType(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

}
