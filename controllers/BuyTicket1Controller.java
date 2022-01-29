package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class BuyTicket1Controller
{
    @FXML
    ChoiceBox<String> chooseCinema;
    @FXML
    ChoiceBox<String> chooseMovie;
    int count;
    static public int idCinema;
    static public int idMovie;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.kino", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT id_kino, miejscowosc FROM BD_projekt.kino", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseCinema.getItems().add("[" + rs.getInt(1) + "] " + rs.getString(2));
            }

            pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.film", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT id_film, tytul FROM BD_projekt.film", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseMovie.getItems().add("[" + rs.getInt(1) + "] " + rs.getString(2));
            }

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("buy ticket 1 error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "clientPanel.fxml");
    }

    @FXML
    void searchButton(ActionEvent event)
    {
        try
        {
            if(chooseCinema.getValue() == null || chooseMovie.getValue() == null)
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            idCinema = main.GetId.getId(chooseCinema.getValue());
            idMovie = main.GetId.getId(chooseMovie.getValue());
            SwitchScene.switchScene(event, "buyTicket2.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}