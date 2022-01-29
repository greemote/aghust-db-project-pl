package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class WorkerChangePasswordController
{
    @FXML
    PasswordField password;
    @FXML
    PasswordField password1;

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void changePassword(ActionEvent event)
    {
        try
        {
            if(!password.getText().equals(password1.getText()))
            {
                Alert.alert1("błąd przy ustawianiu nowego hasła!");
                password.clear();
                password1.clear();
                throw new SQLException();
            }

            PreparedStatement pst = ConnectionVariable.c.prepareStatement("UPDATE BD_projekt.logowanie_pracownik SET haslo = ? WHERE id_pracownik = ?");
            pst.setString(1, password.getText());
            pst.setInt(2, LoginController.workerId);
            pst.executeUpdate();

            pst.close();

            Alert.alert1("pomyślnie zmieniono hasło");
            SwitchScene.switchScene(event, "workerPanel.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
