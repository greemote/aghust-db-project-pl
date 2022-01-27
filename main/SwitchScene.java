package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class SwitchScene
{
    static Parent newParent;
    static Scene newScene;
    static Stage window;

    static public void switchScene(ActionEvent event, String fxmlFile)
    {
        try
        {
            newParent = FXMLLoader.load(Objects.requireNonNull(SwitchScene.class.getResource(fxmlFile)));
            newScene = new Scene(newParent);
            window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
        }
        catch(IOException ie){ System.out.println("switch scene error"); }

    }
}
