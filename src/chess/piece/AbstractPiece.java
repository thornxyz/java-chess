package chess.piece;

import java.util.List;

import chess.board.Board;
import chess.common.File;
import chess.common.Location;
import chess.makeboard.Chessboard;
import chess.makeboard.PieceType;
import chess.squares.Square;
import javafx.scene.layout.GridPane;

public abstract class AbstractPiece {
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;

	public abstract List<Location> getValidMoves(Board board);

	public abstract List<Location> getValidMoves(Board board, Square square);

	public void makeMove(Chessboard chessboard, GridPane gridpane, Board board, Square square) {
	    Square currentSquare = this.getCurrentSquare();

	    boolean wasOccupied = currentSquare.isOccupied();
	    AbstractPiece previousPiece = currentSquare.getCurrentPiece();

	    List<Location> validMoves = getValidMoves(board, currentSquare);
	    if (validMoves.contains(square.getLocation())) {
	        Location curr = currentSquare.getLocation();
			currentSquare.setOccupied(false);
			currentSquare.setCurrentPiece(null);
	        int currRow = 9-curr.getRank();
	        File currRank = curr.getFile();
	        int currCol = currRank.ordinal() + 1;
	        Chessboard.removePiece(gridpane, currCol, currRow);

	        this.setCurrentSquare(square);
	        square.setCurrentPiece(this);
	        square.setOccupied(true);

	        Location next = square.getLocation();
	        int nextRow = 9-next.getRank();
	        File nextRank = next.getFile();
	        int nextCol = nextRank.ordinal() + 1;

	        PieceType pieceType = PieceFactory.getPieceType(this);
	        chessboard.addPiece(pieceType, nextCol, nextRow);

	    } else {
	        currentSquare.setOccupied(wasOccupied);
	        currentSquare.setCurrentPiece(previousPiece);

	        System.out.println("Invalid move. The board remains unchanged.");
	    }
	}


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

}
