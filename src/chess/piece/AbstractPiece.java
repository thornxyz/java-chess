package chess.piece;

import java.util.List;

import chess.board.Board;
import chess.common.File;
import chess.common.Location;
import chess.makeboard.Chessboard;
import chess.makeboard.PieceType;
import chess.squares.Square;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public abstract class AbstractPiece {
    protected String name;
    protected PieceColor pieceColor;
    protected Square currentSquare;

    public abstract List<Location> getValidMoves(Board board);

    public abstract List<Location> getValidMoves(Board board, Square square);

    public AbstractPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public String getName() {
        return name;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    private static PieceColor currentTurn = PieceColor.WHITE;

    public static PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public static void switchTurn() {
        currentTurn = (currentTurn == PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }

    public void handleRegularMove(Chessboard chessboard, GridPane gridpane, Board board, Square targetSquare,
            AbstractPiece capturedPiece, Label outputLabel) {

        Location curr = currentSquare.getLocation();
        int currRow = 9 - curr.getRank();
        int currCol = curr.getFile().ordinal() + 1;
        Chessboard.removePiece(gridpane, currCol, currRow);

        currentSquare.setOccupied(false);
        currentSquare.setCurrentPiece(null);

        this.setCurrentSquare(targetSquare);
        targetSquare.setCurrentPiece(this);
        targetSquare.setOccupied(true);

        if (capturedPiece != null) {
            Location capturedPieceLocation = targetSquare.getLocation();
            int capturedPieceRow = 9 - capturedPieceLocation.getRank();
            int capturedPieceCol = capturedPieceLocation.getFile().ordinal() + 1;
            Chessboard.removePiece(gridpane, capturedPieceCol, capturedPieceRow);

            String cap = "Piece captured: " + capturedPiece.getName() + " at " + capturedPieceLocation.toString();
            System.out.println(cap);
            outputLabel.setText(cap);
        }

        Location next = targetSquare.getLocation();
        int nextRow = 9 - next.getRank();
        int nextCol = next.getFile().ordinal() + 1;

        PieceType pieceType = PieceFactory.getPieceType(this);
        chessboard.addPiece(pieceType, nextCol, nextRow);

        switchTurn();
    }

    public void makeMove(Chessboard chessboard, GridPane gridpane, Board board, Square square, Label outputLabel) {

        if (this.getPieceColor() != currentTurn) {
            System.out.println("It's not your turn!");
            outputLabel.setText("It's not your turn!");
            return;
        }
        Square currentSquare = this.getCurrentSquare();
        boolean wasOccupied = currentSquare.isOccupied();
        AbstractPiece previousPiece = currentSquare.getCurrentPiece();
        List<Location> validMoves = getValidMoves(board, currentSquare);

        if (validMoves.contains(square.getLocation())) {
            AbstractPiece capturedPiece = square.isOccupied() ? square.getCurrentPiece() : null;
            if (capturedPiece == null || capturedPiece.getPieceColor() != this.getPieceColor()) {

                handleRegularMove(chessboard, gridpane, board, square, capturedPiece, outputLabel);

            } else {
                System.out.println("Invalid move. You cannot capture your own piece.");
                outputLabel.setText("Invalid move. You cannot capture your own piece.");
            }
        } else {
            currentSquare.setOccupied(wasOccupied);
            currentSquare.setCurrentPiece(previousPiece);
            System.out.println("Invalid move. The board remains unchanged.");
            outputLabel.setText("Invalid move. The board remains unchanged.");
        }
    }

}
