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

public class ViewShowsController
{
    @FXML
    TableView<Show> showTable;
    @FXML
    TableColumn<Show, Integer> id;
    @FXML
    TableColumn<Show, String> title;
    @FXML
    TableColumn<Show, String> cinema;
    @FXML
    TableColumn<Show, String> hall;
    @FXML
    TableColumn<Show, String> technology;
    @FXML
    TableColumn<Show, String> translation;
    @FXML
    TableColumn<Show, String> date;
    @FXML
    TableColumn<Show, String> time;

    int count;

    @FXML
    public void initialize()
    {
        id.setCellValueFactory(new PropertyValueFactory<Show, Integer>("id"));
        title.setCellValueFactory(new PropertyValueFactory<Show, String>("title"));
        cinema.setCellValueFactory(new PropertyValueFactory<Show, String>("cinema"));
        hall.setCellValueFactory(new PropertyValueFactory<Show, String>("hall"));
        technology.setCellValueFactory(new PropertyValueFactory<Show, String>("technology"));
        translation.setCellValueFactory(new PropertyValueFactory<Show, String>("translation"));
        date.setCellValueFactory(new PropertyValueFactory<Show, String>("date"));
        time.setCellValueFactory(new PropertyValueFactory<Show, String>("time"));
        ObservableList<Show> list = FXCollections.observableArrayList();

        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM wszystkieSeanse", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT * FROM wszystkieSeanse", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                list.add(new Show(Integer.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), "[" + rs.getString(4) + "] " + rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }

            showTable.setItems(list);

            pst.close();
            rs.close();
        } catch(SQLException e){ System.out.println("view shows error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }
}
