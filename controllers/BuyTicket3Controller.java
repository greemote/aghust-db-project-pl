package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;

import java.math.BigDecimal;
import java.sql.*;

public class BuyTicket3Controller
{
    @FXML
    ComboBox<String> discount;
    @FXML
    ChoiceBox<String> typeBox;
    @FXML
    Label value;

    int count;
    int newId;

    @FXML
    public void initialize()
    {
        discount.getItems().addAll("[1] bilet ulgowy", "[2] bilet normalny");
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("select count(*) from bd_projekt.forma_platnosci", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("select nazwa from bd_projekt.forma_platnosci", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                typeBox.getItems().add("[" + i + "] " + rs.getString(1));
            }
            pst.close();
            rs.close();
        } catch(SQLException e){ System.out.println("buy ticket 3 error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        BuyTicket2Controller.newSeat = 0;
        BuyTicket2Controller.newRow = "\0";
        BuyTicket2Controller.newShow = 0;
        SwitchScene.switchScene(event, "buyTicket2.fxml");
    }

    @FXML
    void setValue()
    {
        if(discount.getValue() == "[1] bilet ulgowy")
            value.setText("cena: 15 PLN");
        else if(discount.getValue() == "[2] bilet normalny")
            value.setText("cena: 30 PLN");
    }

    @FXML
    void buy(ActionEvent event)
    {
        try
        {
            if(discount.getValue() == null || typeBox.getValue() == null)
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            CallableStatement cst = ConnectionVariable.c.prepareCall("{call bd_projekt.getLastPaymentId}");
            ResultSet rs = cst.executeQuery();
            rs.next();
            newId = 1 + rs.getInt(1);
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("insert into bd_projekt.platnosc values (?, ?, ?, ?)");
            pst.setInt(1, newId);
            pst.setInt(2, LoginController.clientId);
            pst.setInt(3, main.GetId.getId(typeBox.getValue()));
            if(discount.getValue() == "[1] bilet ulgowy")
                pst.setDouble(4, 15.0);
            else if(discount.getValue() == "[2] bilet normalny")
                pst.setDouble(4, 30.0);
            pst.executeUpdate();

            pst = ConnectionVariable.c.prepareStatement("insert into bd_projekt.bilet values (?, ?, ?, ?, ?)");
            pst.setInt(1, newId);
            pst.setInt(2, newId);
            pst.setInt(3, BuyTicket2Controller.newShow);
            pst.setString(4, BuyTicket2Controller.newRow);
            pst.setInt(5, BuyTicket2Controller.newSeat);
            pst.executeUpdate();

            Alert.alert1("bilet został zakupiony!");

            pst.close();
            rs.close();

            SwitchScene.switchScene(event, "clientPanel.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
