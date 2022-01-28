package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class AddDirectorController
{
    @FXML
    TextField nameText;
    @FXML
    TextField surnameText;
    int newId;

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void addDirectorButton(ActionEvent event)
    {
            try
            {
                if(nameText.getText().equals("") || surnameText.getText().equals(""))
                {
                    Alert.alert1("proszę wypełnić wymagane pola!");
                    throw new SQLException();
                }

                CallableStatement cst = ConnectionVariable.c.prepareCall("{call BD_projekt.getLastDirectorId()}");
                ResultSet rs = cst.executeQuery();
                rs.next();
                newId = 1 + rs.getInt(1);
                PreparedStatement pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.rezyser VALUES (?, ?, ?)");
                pst.setInt(1, newId);
                pst.setString(2, nameText.getText());
                pst.setString(3, surnameText.getText());
                pst.executeUpdate();

                rs.close();
                pst.close();

                Alert.alert1("pomyślnie dodano reżysera");
                SwitchScene.switchScene(event, "adminPanel.fxml");
            }
            catch(SQLException e)
            {
                System.out.println("ERROR");
            }
    }
}
