package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController
{
    public static int adminId = 0;
    public static int clientId = 0;
    public static int workerId = 0;

    @FXML
    TextField clientLoginText;
    @FXML
    TextField clientPasswordText;
    @FXML
    TextField workerLoginText;
    @FXML
    TextField workerPasswordText;
    @FXML
    TextField adminLoginText;
    @FXML
    TextField adminPasswordText;

    @FXML
    void register(ActionEvent event)
    {
        SwitchScene.switchScene(event, "register.fxml");
    }

    @FXML
    void clientLogin(ActionEvent event)
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_klient FROM BD_projekt.logowanie_klient la WHERE login = ? AND haslo = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, clientLoginText.getText());
            pst.setString(2, clientPasswordText.getText());
            ResultSet rs;
            rs = pst.executeQuery();
            if(rs.next())
            {
                clientId = rs.getInt("id_klient");
                SwitchScene.switchScene(event, "clientPanel.fxml");
            }
            else
            {
                Alert.alert1("nieprawidłowa kombinacja loginu i hasła!");
                clientLoginText.setText("");
                clientPasswordText.setText("");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("ERROR"); }
    }

    @FXML
    void workerLogin(ActionEvent event)
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_pracownik FROM BD_projekt.logowanie_pracownik la WHERE login = ? AND haslo = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, workerLoginText.getText());
            pst.setString(2, workerPasswordText.getText());
            ResultSet rs;
            rs = pst.executeQuery();
            if(rs.next())
            {
                workerId = rs.getInt("id_pracownik");
                SwitchScene.switchScene(event, "workerPanel.fxml");
            }
            else
            {
                Alert.alert1("nieprawidłowa kombinacja loginu i hasła!");
                workerLoginText.setText("");
                workerPasswordText.setText("");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("ERROR"); }
    }

    @FXML
    void adminLogin(ActionEvent event)
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_administrator FROM BD_projekt.logowanie_admin la WHERE login = ? AND haslo = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setString(1, adminLoginText.getText());
            pst.setString(2, adminPasswordText.getText());
            ResultSet rs;
            rs = pst.executeQuery();
            if(rs.next())
            {
                adminId = rs.getInt("id_administrator");
                SwitchScene.switchScene(event, "adminPanel.fxml");
            }
            else
            {
                Alert.alert1("nieprawidłowa kombinacja loginu i hasła!");
                adminLoginText.setText("");
                adminPasswordText.setText("");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){  Alert.alert1("wystąpił błąd"); }
    }
}