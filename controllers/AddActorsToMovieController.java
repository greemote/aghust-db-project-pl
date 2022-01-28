package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class AddActorsToMovieController
{
    @FXML
    ChoiceBox<String> chooseActor;
    int count;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.aktor", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT imie, nazwisko FROM BD_projekt.aktor", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseActor.getItems().add("[" + i + "] " + rs.getString(1) + " " + rs.getString(2));
            }

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("add movie error"); }
    }

    @FXML
    void finish(ActionEvent event)
    {
        Alert.alert1("proces zakończony pomyślnie");
        AddMovieController.idMovie = 0;
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void addActorToMovieButton(ActionEvent event)
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.aktor_film WHERE id_aktor = ? AND id_film = ?");
            pst.setInt(1, chooseActor.getValue().charAt(1) - '0');
            pst.setInt(2, AddMovieController.idMovie);
            ResultSet rs = pst.executeQuery();
            rs.next();
            if(rs.getInt(1) != 0)
            {
                Alert.alert1("dodano już tego aktora do tego filmu!");
                rs.close();
                pst.close();
                throw new SQLException();
            }
            pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.aktor_film VALUES (?, ?)");
            pst.setInt(1, AddMovieController.idMovie);
            pst.setInt(2, chooseActor.getValue().charAt(1) - '0');
            pst.executeUpdate();

            rs.close();
            pst.close();

            Alert.alert1("dodano aktora");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
