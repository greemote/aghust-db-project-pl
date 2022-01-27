package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.ConnectionVariable;
import main.SwitchScene;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerPanelController
{
    @FXML
    Label names;
    @FXML
    Label email;
    @FXML
    Label phoneNumber;
    @FXML
    Label unit;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT imie, nazwisko, email, telefon, miejscowosc" +
                    " FROM BD_projekt.pracownik p, BD_projekt.kino k " +
                    " WHERE id_pracownik = ?" +
                    " AND p.id_kino = k.id_kino",
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.workerId);
            ResultSet rs;
            rs = pst.executeQuery();

            rs.next();
            names.setText(rs.getString("imie") + " " + rs.getString("nazwisko"));
            email.setText(rs.getString("email"));
            phoneNumber.setText("nr tel. " + rs.getString("telefon"));
            unit.setText(rs.getString("miejscowosc"));

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("client panel error"); }
    }

    @FXML
    public void logout(ActionEvent event)
    {
        LoginController.workerId = 0;
        SwitchScene.switchScene(event, "login.fxml");
    }
}
