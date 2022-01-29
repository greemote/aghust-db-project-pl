package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class SelectCinemaController
{
    @FXML
    ChoiceBox<String> chooseCinema;

    int count;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.kino", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT miejscowosc FROM BD_projekt.kino", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseCinema.getItems().add("[" + i + "] " + rs.getString(1));
            }

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("add movie error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void selectCinema(ActionEvent event)
    {
        try
        {
            if(chooseCinema.getValue() == null)
            {
                Alert.alert1("proszę wybrać kino!");
                throw new SQLException();
            }

            AddShowController.idCinema = main.GetId.getId(chooseCinema.getValue());
            SwitchScene.switchScene(event, "addShow.fxml");
        }
        catch(SQLException e)
        {
            Alert.alert1("wystąpił błąd");
        }
    }
}
