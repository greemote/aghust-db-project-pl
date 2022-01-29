package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.Alert;
import main.ConnectionVariable;
import main.GetId;
import main.SwitchScene;
import java.sql.*;

public class DeleteWorkerController
{
    @FXML
    ComboBox<String> chooseWorker;
    @FXML
    Label info;
    @FXML
    Label cinema;
    @FXML
    Label name;
    @FXML
    Label surname;
    @FXML
    Label email;
    @FXML
    Label phone;

    int count;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_pracownik from bd_projekt.pracownik", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                chooseWorker.getItems().add("[" + rs.getInt(1) + "]");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("delete worker error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void delete(ActionEvent event)
    {
        try
        {
            if(chooseWorker.getValue() == null)
            {
                Alert.alert1("proszę wybrać konto do usunięcia!");
                throw new SQLException();
            }

            PreparedStatement pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.logowanie_pracownik where id_pracownik = ?");
            pst.setInt(1, GetId.getId(chooseWorker.getValue()));
            pst.executeUpdate();
            pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.pracownik where id_pracownik = ?");
            pst.setInt(1, GetId.getId(chooseWorker.getValue()));
            pst.executeUpdate();

            Alert.alert1("pomyślnie usunięto konto pracownika");
            SwitchScene.switchScene(event, "adminPanel.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }

    @FXML
    void displayInfo()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT miejscowosc, imie, nazwisko, email, telefon FROM bd_projekt.pracownik p, bd_projekt.kino k where p.id_kino = k.id_kino and p.id_pracownik = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(chooseWorker.getValue()));
            ResultSet rs = pst.executeQuery();
            rs.next();
            info.setText("informacje o pracowniku:");
            cinema.setText(rs.getString(1));
            name.setText(rs.getString(2));
            surname.setText(rs.getString(3));
            email.setText(rs.getString(4));
            phone.setText(rs.getString(5));
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("display error"); }
    }
}
