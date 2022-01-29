package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.GetId;
import main.SwitchScene;
import java.sql.*;

public class AddWorkerController
{
    @FXML
    TextField name;
    @FXML
    TextField surname;
    @FXML
    TextField email;
    @FXML
    TextField phone;
    @FXML
    TextField login;
    @FXML
    ChoiceBox<String> cinema;
    int newId;

    @FXML
    void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_kino, miejscowosc from bd_projekt.kino");
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                cinema.getItems().add("[" + rs.getString(1) + "] " + rs.getString(2));
            }
        } catch(SQLException e){ System.out.println("add worker error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void add(ActionEvent event)
    {
        try
        {
            if(name.getText().equals("") || surname.getText().equals("") || email.getText().equals("") || phone.getText().equals("") || login.getText().equals("") || cinema.getValue() == null)
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            CallableStatement cst = ConnectionVariable.c.prepareCall("{call BD_projekt.getLastWorkerId()}");
            ResultSet rs = cst.executeQuery();
            rs.next();
            newId = 1 + rs.getInt(1);
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.pracownik VALUES (?, ?, ?, ?, ?, ?)");
            pst.setInt(1, newId);
            pst.setInt(2, GetId.getId(cinema.getValue()));
            pst.setString(3, name.getText());
            pst.setString(4, surname.getText());
            pst.setString(5, email.getText());
            pst.setString(6, phone.getText());
            pst.executeUpdate();

            pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.logowanie_pracownik VALUES (?, ?, ?)");
            pst.setInt(1, newId);
            pst.setString(2, login.getText());
            pst.setString(3, login.getText());
            pst.executeUpdate();

            rs.close();
            pst.close();

            Alert.alert1("pomyślnie dodano pracownika");
            SwitchScene.switchScene(event, "adminPanel.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
