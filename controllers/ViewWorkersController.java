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

public class ViewWorkersController
{
    @FXML
    TableView<Worker> showTable;
    @FXML
    TableColumn<Worker, Integer> id;
    @FXML
    TableColumn<Worker, String> cinema;
    @FXML
    TableColumn<Worker, String> name;
    @FXML
    TableColumn<Worker, String> surname;
    @FXML
    TableColumn<Worker, String> email;
    @FXML
    TableColumn<Worker, String> phone;

    int count;

    @FXML
    public void initialize()
    {
        id.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("id"));
        cinema.setCellValueFactory(new PropertyValueFactory<Worker, String>("cinema"));
        name.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<Worker, String>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<Worker, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Worker, String>("phone"));
        ObservableList<Worker> list = FXCollections.observableArrayList();

        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM bd_projekt.pracownik", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT * from pracownicy", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                list.add(new Worker(Integer.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4) , rs.getString(5), rs.getString(6)));
            }

            showTable.setItems(list);

            pst.close();
            rs.close();
        } catch(SQLException e){ System.out.println("view workers error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }
}
