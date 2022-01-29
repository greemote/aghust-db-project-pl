package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class RefundController
{
    @FXML
    ComboBox<String> chooseTicket;
    @FXML
    Label info;
    @FXML
    Label value;
    @FXML
    Label title;
    @FXML
    Label cinema;
    @FXML
    Label hall;
    @FXML
    Label row;
    @FXML
    Label seat;
    @FXML
    Label date;
    @FXML
    Label time;

    int count;

    @FXML
    public void initialize()
    {
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM mojebilety where id_klient = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.clientId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT id_bilet FROM mojebilety WHERE id_klient = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.clientId);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseTicket.getItems().add("[" + rs.getInt(1) + "]");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("refund error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "clientPanel.fxml");
    }

    @FXML
    void refund(ActionEvent event)
    {
        try
        {
            if(chooseTicket.getValue() == null)
            {
                Alert.alert1("proszę wybrać bilet do zwrotu!");
                throw new SQLException();
            }

            PreparedStatement pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.bilet where id_bilet = ?");
            pst.setInt(1, LoginController.clientId);
            pst.executeUpdate();
            pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.platnosc where id_platnosc = ?");
            pst.setInt(1, LoginController.clientId);
            pst.executeUpdate();

            Alert.alert1("dokonano zwrotu!");
            SwitchScene.switchScene(event, "clientPanel.fxml");
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
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT * FROM mojebilety WHERE id_bilet = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(chooseTicket.getValue()));
            ResultSet rs = pst.executeQuery();
            rs.next();
            info.setText("informacje:");
            value.setText(rs.getString(3));
            title.setText(rs.getString(4));
            cinema.setText(rs.getString(5));
            hall.setText("[" + rs.getString(6) + "]");
            row.setText(rs.getString(7));
            seat.setText(rs.getString(8));
            date.setText(rs.getString(9));
            time.setText(rs.getString(10));
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("display error"); }
    }
}
