package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.ConnectionVariable;
import main.SwitchScene;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanelController
{
    @FXML
    Label names;
    @FXML
    Label email;
    @FXML
    Label phoneNumber;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT imie, nazwisko, email, telefon FROM BD_projekt.administrator WHERE id_administrator = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.adminId);
            ResultSet rs;
            rs = pst.executeQuery();

            rs.next();
            names.setText(rs.getString("imie") + " " + rs.getString("nazwisko"));
            email.setText(rs.getString("email"));
            phoneNumber.setText("nr tel. " + rs.getString("telefon"));

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("admin panel error"); }
    }

    @FXML
    public void logout(ActionEvent event)
    {
        LoginController.adminId = 0;
        SwitchScene.switchScene(event, "login.fxml");
    }

    @FXML
    public void addDirector(ActionEvent event)
    {
        SwitchScene.switchScene(event, "addDirector.fxml");
    }
    @FXML
    public void addActor(ActionEvent event)
    {
        SwitchScene.switchScene(event, "addActor.fxml");
    }
    @FXML
    public void addMovie(ActionEvent event)
    {
        SwitchScene.switchScene(event, "addMovie.fxml");
    }
    @FXML
    public void displayMovies(ActionEvent event)
    {
        SwitchScene.switchScene(event, "viewMovies.fxml");
    }
}