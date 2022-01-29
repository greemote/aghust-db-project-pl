package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class WorkerAddShowController
{
    @FXML
    ChoiceBox<String> chooseMovie;
    @FXML
    ChoiceBox<String> chooseHall;
    @FXML
    ChoiceBox<String> chooseTechnology;
    @FXML
    ChoiceBox<String> chooseTranslation;
    @FXML
    TextField date;
    @FXML
    TextField time;

    int newId;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT id_film, tytul FROM BD_projekt.film", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                chooseMovie.getItems().add("[" + rs.getString(1) + "] " + rs.getString(2));
            }
            pst = ConnectionVariable.c.prepareStatement("SELECT id_sala, ilosc_miejsc FROM BD_projekt.sala WHERE id_kino = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, WorkerPanelController.cinemaId);
            rs = pst.executeQuery();
            while(rs.next())
            {
                chooseHall.getItems().add("[" + rs.getString(1) + "] miejsca: " + rs.getString(2));
            }
            pst = ConnectionVariable.c.prepareStatement("SELECT id_technologia, nazwa FROM BD_projekt.technologia", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while(rs.next())
            {
                chooseTechnology.getItems().add("[" + rs.getString(1) + "] " + rs.getString(2));
            }
            pst = ConnectionVariable.c.prepareStatement("SELECT id_tlumaczenie, nazwa FROM BD_projekt.tlumaczenie", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            while(rs.next())
            {
                chooseTranslation.getItems().add("[" + rs.getString(1) + "] " + rs.getString(2));
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("add show error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "workerPanel.fxml");
    }

    @FXML
    void addShowButton(ActionEvent event)
    {
        try
        {
            if(chooseMovie.getValue() == null || chooseHall.getValue() == null || chooseTechnology.getValue() == null || date.getText().equals("") || time.getText().equals(""))
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            CallableStatement cst = ConnectionVariable.c.prepareCall("{call BD_projekt.getLastShowId()}");
            ResultSet rs = cst.executeQuery();
            rs.next();
            newId = 1 + rs.getInt(1);
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.seans VALUES (?, ?, ?, ?, ?, ?, ?)");
            pst.setInt(1, newId);
            pst.setInt(2, main.GetId.getId(chooseMovie.getValue()));
            pst.setInt(3, main.GetId.getId(chooseHall.getValue()));
            pst.setInt(4, main.GetId.getId(chooseTechnology.getValue()));
            if(chooseTranslation.getValue() != null)
                pst.setInt(5, main.GetId.getId(chooseTranslation.getValue()));
            else
                pst.setNull(5, Types.NULL);
            pst.setString(6, date.getText());
            pst.setString(7, time.getText());
            pst.executeUpdate();

            rs.close();
            pst.close();

            Alert.alert1("pomyślnie dodano seans");
            SwitchScene.switchScene(event, "workerPanel.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
