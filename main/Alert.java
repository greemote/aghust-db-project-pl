package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alert
{
    public static void alert1(String message)
    {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("uwaga");
        window.setResizable(false);
        window.setMinWidth(360);

        Label label = new Label(message);
        label.setFont(Font.font("Lato", FontWeight.BOLD, FontPosture.ITALIC, 14));
        Button ok = new Button("rozumiem");
        ok.setFont(Font.font("Lato", FontWeight.BOLD, FontPosture.ITALIC, 14));
        ok.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, ok);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
