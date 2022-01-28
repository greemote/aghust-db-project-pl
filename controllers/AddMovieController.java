package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class AddMovieController
{
    @FXML
    ChoiceBox<String> chooseDirector;
    @FXML
    ChoiceBox<String> chooseGenre;
    @FXML
    ChoiceBox<String> chooseAgeCategory;
    @FXML
    TextField title;
    int newId;
    int count;
    public static int idMovie;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.rezyser", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT imie, nazwisko FROM BD_projekt.rezyser", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseDirector.getItems().add("[" + i + "] " + rs.getString(1) + " " + rs.getString(2));
            }

            pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.gatunek", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT nazwa FROM BD_projekt.gatunek", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseGenre.getItems().add("[" + i + "] " + rs.getString(1));
            }

            pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM BD_projekt.kategoria_wiekowa", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT nazwa FROM BD_projekt.kategoria_wiekowa", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseAgeCategory.getItems().add("[" + i + "] " + rs.getString(1));
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
    void addMovieButton(ActionEvent event)
    {
        try
        {
            if(title.getText().equals("") || chooseDirector.getValue() == null || chooseGenre.getValue() == null || chooseAgeCategory.getValue() == null)
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            CallableStatement cst = ConnectionVariable.c.prepareCall("{call BD_projekt.getLastMovieId()}");
            ResultSet rs = cst.executeQuery();
            rs.next();
            newId = 1 + rs.getInt(1);
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("INSERT INTO BD_projekt.film VALUES (?, ?, ?, ?, ?)");
            pst.setInt(1, newId);
            pst.setInt(2, chooseDirector.getValue().charAt(1) - '0');
            pst.setInt(3, chooseGenre.getValue().charAt(1) - '0');
            pst.setInt(4, chooseAgeCategory.getValue().charAt(1) - '0');
            pst.setString(5, title.getText());
            pst.executeUpdate();

            rs.close();
            pst.close();

            Alert.alert1("pomyślnie dodano film do bazy");
            idMovie = newId;
            SwitchScene.switchScene(event, "addActorsToMovie.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
