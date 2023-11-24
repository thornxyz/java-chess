package chess.makeboard;

import java.io.IOException;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceCreate {
    public static ImageView createPiece(PieceType pieceType, double imageSize) {
        String imagePath = pieceType.getImagePath();

        try (InputStream inputStream = PieceCreate.class.getResourceAsStream(imagePath)) {
            if (inputStream != null) {
                Image image = new Image(inputStream);
                ImageView piece = new ImageView(image);

                piece.setFitWidth(imageSize);
                piece.setFitHeight(imageSize);

                return piece;
            } else {
                System.err.println("Error loading image: " + imagePath);
                return new ImageView();
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Exception loading image: " + imagePath);
            e.printStackTrace();
            return new ImageView();
        }
    }

}
