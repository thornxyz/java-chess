package chess;

import chess.board.Board;
import chess.common.File;
import chess.common.Location;
import chess.makeboard.Chessboard;
import chess.piece.PieceFactory;
import chess.squares.Square;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private GridPane chessboard;
    
    @FXML
    private TextField moveInputField;
    
    private Chessboard gameChessboard;
    Board gameBoard=new Board();

    public void initialize() {
        gameChessboard = new Chessboard(this.chessboard,this.gameBoard);
        addPieces(gameChessboard);
        moveInputField.setStyle("-fx-font-size: 14; -fx-font-family: 'Arial';");
        moveInputField.setOnKeyPressed(this::handleKeyPress);
    }

    private void addPieces(Chessboard chessboard) {
    	PieceFactory.addPieces(gameBoard,chessboard);
    }
    
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String move = moveInputField.getText().toUpperCase(); 
            processMove(move);
            moveInputField.clear();
        }
    }
    
    private void processMove(String move) {
        try {
            String[] fromTo = move.split(",");
            if (fromTo.length != 2) {
                throw new IllegalArgumentException("Invalid move format. Please enter a move like 'E2,E4'.");
            }

            File fromFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[0].charAt(0))));
            int fromRank = Integer.parseInt(String.valueOf(fromTo[0].charAt(1)));
            File toFile = File.valueOf(String.valueOf(Character.toUpperCase(fromTo[1].charAt(0))));
            int toRank = Integer.parseInt(String.valueOf(fromTo[1].charAt(1)));

            Square fromSq = gameBoard.getLocationSquareMap().get(new Location(fromFile, fromRank));
            Square toSq = gameBoard.getLocationSquareMap().get(new Location(toFile, toRank));

            fromSq.getCurrentPiece().makeMove(gameChessboard, chessboard, gameBoard, toSq);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Current square is empty");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
