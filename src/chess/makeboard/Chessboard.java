package chess.makeboard;

import chess.board.Board;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class Chessboard {
    private GridPane gridPane;
    private Board board;

    public Chessboard(GridPane gridPane, Board board) {
        this.gridPane = gridPane;
        this.board = board;
        createChessboard();
    }

    private void createChessboard() {
        int size = 10;
        double squareSize = 500.0 / size;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                ImageView piece = new ImageView();
                piece.setFitWidth(squareSize);
                piece.setFitHeight(squareSize);

                StackPane squarePane = new StackPane(piece);

                squarePane.setPrefSize(squareSize, squareSize);

                Label[] letters = LabelFactory.createLabels('A', 8, 20);
                Label[] numbers = LabelFactory.createLabels(1, 8, 20);
                for (int i = 0; i < 8; i++) {
                    if (row == 0 && col == (i + 1)) {
                        squarePane.getChildren().add(letters[i]);
                    }
                    if (row == 9 && col == (i + 1)) {
                        squarePane.getChildren().add(letters[i]);
                    }
                }

                for (int i = 7; i >= 0; i--) {
                    if (row == (i + 1) && col == 0) {
                        squarePane.getChildren().add(numbers[7 - i]);
                    }
                    if (row == (i + 1) && col == 9) {
                        squarePane.getChildren().add(numbers[7 - i]);
                    }
                }

                if (row == 0 || col == 0 || row == 9 || col == 9) {
                    squarePane.setStyle("-fx-background-color: black");
                } else {
                    squarePane.setStyle(
                            (row + col) % 2 == 0 ? "-fx-background-color: white;" : "-fx-background-color: gray;");
                }

                gridPane.add(squarePane, col, row);

            }
        }
    }

    public Board getboard() {
        return board;
    }

    public static void removePiece(GridPane gridPane, int col, int row) {
        ObservableList<Node> children = gridPane.getChildren();

        for (Node node : children) {
            if (node instanceof ImageView && GridPane.getColumnIndex(node) == col
                    && GridPane.getRowIndex(node) == row) {
                children.remove(node);
                break;
            }
        }
    }

    public void addPiece(PieceType pieceType, int col, int row) {
        ImageView piece = PieceCreate.createPiece(pieceType, 45.0);
        gridPane.add(piece, col, row);
    }

}
