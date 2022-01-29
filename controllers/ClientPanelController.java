package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import main.ConnectionVariable;
import main.SwitchScene;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientPanelController
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
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT imie, nazwisko, email, telefon FROM BD_projekt.klient WHERE id_klient = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.clientId);
            ResultSet rs;
            rs = pst.executeQuery();

            rs.next();
            names.setText(rs.getString("imie") + " " + rs.getString("nazwisko"));
            email.setText(rs.getString("email"));
            if(rs.getString("telefon").equals(""))
                phoneNumber.setText("");
            else
                phoneNumber.setText("nr tel. " + rs.getString("telefon"));

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("client panel error"); }
    }

    @FXML
    public void logout(ActionEvent event)
    {
        LoginController.clientId = 0;
        SwitchScene.switchScene(event, "login.fxml");
    }
    @FXML
    void changePassword(ActionEvent event)
    {
        SwitchScene.switchScene(event, "clientChangePassword.fxml");
    }

    @FXML
    void buyTicket(ActionEvent event)
    {
        SwitchScene.switchScene(event, "buyTicket1.fxml");
    }
    @FXML
    void refundTicket(ActionEvent event)
    {
        SwitchScene.switchScene(event, "refund.fxml");
    }
    @FXML
    void myTickets(ActionEvent event)
    {
        SwitchScene.switchScene(event, "viewTickets.fxml");
    }
}
