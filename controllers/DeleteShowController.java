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

public class DeleteShowController
{
    @FXML
    ComboBox<String> chooseShowId;
    @FXML
    Label info;
    @FXML
    Label title;
    @FXML
    Label cinema;
    @FXML
    Label hall;
    @FXML
    Label technology;
    @FXML
    Label translation;
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
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM wszystkieSeanse", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT id_seans FROM wszystkieSeanse", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                chooseShowId.getItems().add("[" + rs.getInt(1) + "]");
            }
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("delete show error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }

    @FXML
    void selectShow(ActionEvent event)
    {
        try
        {
            if(chooseShowId.getValue() == null)
            {
                Alert.alert1("proszę wybrać seans do usunięcia!");
                throw new SQLException();
            }

            PreparedStatement pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.bilet where id_seans = ?");
            pst.setInt(1, GetId.getId(chooseShowId.getValue()));
            pst.executeUpdate();
            pst = ConnectionVariable.c.prepareStatement("delete from bd_projekt.seans where id_seans = ?");
            pst.setInt(1, GetId.getId(chooseShowId.getValue()));
            pst.executeUpdate();

            Alert.alert1("usunięto seans z bazy!");
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
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT * FROM wszystkieSeanse WHERE id_seans = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(chooseShowId.getValue()));
            ResultSet rs = pst.executeQuery();
            rs.next();
            info.setText("informacje o seansie:");
            title.setText(rs.getString(2));
            cinema.setText(rs.getString(3));
            hall.setText("[" + rs.getString(4) + "] " + rs.getString(5));
            technology.setText(rs.getString(6));
            translation.setText(rs.getString(7));
            date.setText(rs.getString(8));
            time.setText(rs.getString(9));
            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("display error"); }
    }
}
