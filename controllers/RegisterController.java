package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class RegisterController
{
    @FXML
    TextField nameText;
    @FXML
    TextField surnameText;
    @FXML
    TextField emailText;
    @FXML
    TextField phoneText;
    @FXML
    TextField loginText;
    @FXML
    PasswordField passwordText1;
    @FXML
    PasswordField passwordText2;

    int newId;

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "login.fxml");
    }

    @FXML
    void tryToRegister(ActionEvent event)
    {
        if(!passwordText1.getText().equals(passwordText2.getText()))
        {
            Alert.alert1("błąd przy ustawianiu hasła");
            passwordText1.clear();
            passwordText2.clear();
        }
        else
        {
            try
            {
                CallableStatement cst = ConnectionVariable.c.prepareCall("{call BD_projekt.checkClientEmail(?)}");
                cst.setString(1, emailText.getText());
                ResultSet rs;
                rs = cst.executeQuery();
                rs.next();
                if(rs.getInt(1) == 0)
                {
                    Alert.alert1("ten e-mail posiada już konto w bazie");
                    emailText.clear();
                    throw new SQLException();
                }

                cst = ConnectionVariable.c.prepareCall("{call BD_projekt.checkClientLogin(?)}");
                cst.setString(1, loginText.getText());
                rs = cst.executeQuery();
                rs.next();
                if(rs.getInt(1) == 0)
                {
                    Alert.alert1(("login jest już wykorzystany"));
                    loginText.clear();
                    throw new SQLException();
                }

                if(nameText.getText().equals("") || surnameText.getText().equals("") || emailText.getText().equals("") || loginText.getText().equals("") || passwordText1.getText().equals(""))
                {
                    Alert.alert1("proszę wypełnić wymagane pola!");
                    throw new SQLException();
                }

                cst = ConnectionVariable.c.prepareCall("{call BD_projekt.getLastClientId()}");
                rs = cst.executeQuery();
                rs.next();
                newId = 1 + rs.getInt(1);
                PreparedStatement pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.klient VALUES (?, ?, ?, ?, ?)");
                pst.setInt(1, newId);
                pst.setString(2, nameText.getText());
                pst.setString(3, surnameText.getText());
                pst.setString(4, emailText.getText());
                pst.setString(5, phoneText.getText());
                pst.executeUpdate();
                pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.logowanie_klient VALUES (?, ?, ?)");
                pst.setInt(1, newId);
                pst.setString(2, loginText.getText());
                pst.setString(3, passwordText1.getText());
                pst.executeUpdate();

                rs.close();
                pst.close();

                Alert.alert1("konto zostalo założone pomyślnie");
                SwitchScene.switchScene(event, "login.fxml");
            }
            catch(SQLException e)
            {
                System.out.println("ERROR");
            }
        }
    }
}
