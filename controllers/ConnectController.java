package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class ConnectController
{
    @FXML
    void tryToConnect(ActionEvent event)
    {
        try
        {
            ConnectionVariable.c = DriverManager.getConnection("jdbc:postgresql://ella.db.elephantsql.com:5432/pkeroafy", "pkeroafy", "Xc6lWk_QQ7y3SHs93kCmNNJkvzRyIDYZ");
            SwitchScene.switchScene(event, "login.fxml");
        }
        catch(SQLException e) { Alert.alert1("wystąpił problem z połączeniem"); }
    }
}