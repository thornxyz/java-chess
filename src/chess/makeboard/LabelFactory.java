package chess.makeboard;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelFactory {
    public static Label[] createLabels(char startChar, int count, int fontSize) {
        Label[] labels = new Label[count];
        for (int i = 0; i < count; i++) {
            char currentChar = (char) (startChar + i);
            Label label = new Label(String.valueOf(currentChar));
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
            labels[i] = label;
        }
        return labels;
    }

    public static Label[] createLabels(int startNumber, int count, int fontSize) {
        Label[] labels = new Label[count];
        for (int i = 0; i < count; i++) {
            int currentNumber = startNumber + i;
            Label label = new Label(String.valueOf(currentNumber));
            label.setTextFill(Color.WHITE);
            label.setFont(Font.font("Arial", FontWeight.BOLD, fontSize));
            labels[i] = label;
        }
        return labels;
    }
}
