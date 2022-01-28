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

public class ViewMoviesController
{
    @FXML
    TableView<Movie> movieTable;
    @FXML
    TableColumn<Movie, String> title;
    @FXML
    TableColumn<Movie, String> director;
    @FXML
    TableColumn<Movie, String> genre;
    @FXML
    TableColumn<Movie, String> ageCategory;

    int count;

    @FXML
    public void initialize()
    {
        title.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        director.setCellValueFactory(new PropertyValueFactory<Movie, String>("director"));
        genre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        ageCategory.setCellValueFactory(new PropertyValueFactory<Movie, String>("ageCategory"));
        ObservableList<Movie> list = FXCollections.observableArrayList();

        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("SELECT COUNT(*) FROM wszystkieFilmy", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("SELECT * FROM wszystkieFilmy", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                list.add(new Movie(rs.getString(1), rs.getString(2) + " " + rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            movieTable.setItems(list);

        } catch(SQLException e){ System.out.println("view movies error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        SwitchScene.switchScene(event, "adminPanel.fxml");
    }
}
