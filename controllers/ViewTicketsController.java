package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class ViewTicketsController
{
    @FXML
    TableView<Ticket> showTable;
    @FXML
    TableColumn<Ticket, Double> value;
    @FXML
    TableColumn<Ticket, String> title;
    @FXML
    TableColumn<Ticket, String> cinema;
    @FXML
    TableColumn<Ticket, String> hall;
    @FXML
    TableColumn<Ticket, String> row;
    @FXML
    TableColumn<Ticket, String> seat;
    @FXML
    TableColumn<Ticket, String> date;
    @FXML
    TableColumn<Ticket, String> time;

    int count;

    @FXML
    public void initialize()
    {
        value.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("value"));
        title.setCellValueFactory(new PropertyValueFactory<Ticket, String>("title"));
        cinema.setCellValueFactory(new PropertyValueFactory<Ticket, String>("cinema"));
        hall.setCellValueFactory(new PropertyValueFactory<Ticket, String>("hall"));
        row.setCellValueFactory(new PropertyValueFactory<Ticket, String>("row"));
        seat.setCellValueFactory(new PropertyValueFactory<Ticket, String>("seat"));
        date.setCellValueFactory(new PropertyValueFactory<Ticket, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Ticket, String>("time"));
        ObservableList<Ticket> list = FXCollections.observableArrayList();

        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM mojebilety where id_klient = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.clientId);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT wartosc, tytul, miejscowosc, id_sala, rzad, numer_miejsca, data, godzina_rozpoczecia FROM mojebilety WHERE id_klient = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, LoginController.clientId);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                list.add(new Ticket(Double.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), "[" + rs.getString(4) + "]", rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            showTable.setItems(list);

            pst.close();
            rs.close();
        } catch(SQLException e){ System.out.println("view movies error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "clientPanel.fxml");
    }
}
