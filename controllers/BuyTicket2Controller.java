package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Alert;
import main.ConnectionVariable;
import main.SwitchScene;
import java.sql.*;

public class BuyTicket2Controller
{
    @FXML
    TableView<Show1> showTable;
    @FXML
    TableColumn<Show1, Integer> id;
    @FXML
    TableColumn<Show1, String> technology;
    @FXML
    TableColumn<Show1, String> translation;
    @FXML
    TableColumn<Show1, String> date;
    @FXML
    TableColumn<Show1, String> time;
    @FXML
    ChoiceBox<String> idBox;
    @FXML
    ChoiceBox<Integer> seat;
    @FXML
    ChoiceBox<String> row;
    @FXML
    Label cinemaAndMovie;

    int count;
    String cinemaLabel;
    String titleLabel;
    int seats;

    public static int newShow;
    public static String newRow;
    public static int newSeat;

    @FXML
    public void initialize()
    {
        seat.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        row.getItems().addAll("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T");
        try
        {
            PreparedStatement pst = ConnectionVariable.c.prepareStatement("select miejscowosc from bd_projekt.kino k where k.id_kino = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, BuyTicket1Controller.idCinema);
            ResultSet rs = pst.executeQuery();
            rs.next();
            cinemaLabel = rs.getString(1);
            pst = ConnectionVariable.c.prepareStatement("select tytul from bd_projekt.film f where f.id_film = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, BuyTicket1Controller.idMovie);
            rs = pst.executeQuery();
            rs.next();
            titleLabel = rs.getString(1);

            cinemaAndMovie.setText("wybrane pokazy filmu \"" + titleLabel + "\" w miejscowości " + cinemaLabel + ":");

            id.setCellValueFactory(new PropertyValueFactory<Show1, Integer>("id"));
            technology.setCellValueFactory(new PropertyValueFactory<Show1, String>("technology"));
            translation.setCellValueFactory(new PropertyValueFactory<Show1, String>("translation"));
            date.setCellValueFactory(new PropertyValueFactory<Show1, String>("date"));
            time.setCellValueFactory(new PropertyValueFactory<Show1, String>("time"));
            ObservableList<Show1> list = FXCollections.observableArrayList();

            pst = ConnectionVariable.c.prepareStatement("select count(*) from bd_projekt.seans s, bd_projekt.technologia tch, bd_projekt.tlumaczenie tl, bd_projekt.kino k, bd_projekt.sala sal where s.id_technologia = tch.id_technologia and s.id_tlumaczenie = tl.id_tlumaczenie and s.id_sala = sal.id_sala and k.id_kino = sal.id_kino and k.id_kino = ? and s.id_film = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, BuyTicket1Controller.idCinema);
            pst.setInt(2, BuyTicket1Controller.idMovie);
            rs = pst.executeQuery();
            rs.next();
            count = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("select id_seans, tch.nazwa as technologia, tl.nazwa as tlumaczenie, s.data, s.godzina_rozpoczecia from bd_projekt.seans s, bd_projekt.technologia tch, bd_projekt.tlumaczenie tl, bd_projekt.kino k, bd_projekt.sala sal where s.id_technologia = tch.id_technologia and s.id_tlumaczenie = tl.id_tlumaczenie and s.id_sala = sal.id_sala and k.id_kino = sal.id_kino and k.id_kino = ? and s.id_film = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, BuyTicket1Controller.idCinema);
            pst.setInt(2, BuyTicket1Controller.idMovie);
            rs = pst.executeQuery();
            for(int i = 1; i <= count; ++i)
            {
                rs.next();
                list.add(new Show1(Integer.valueOf(rs.getInt(1)), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
                idBox.getItems().add("[" + rs.getInt(1) + "]");
            }
            showTable.setItems(list);

            rs.close();
            pst.close();
        } catch(SQLException e){ System.out.println("buy ticket 2 error"); }
    }

    @FXML
    void back(ActionEvent event)
    {
        BuyTicket1Controller.idMovie = 0;
        BuyTicket1Controller.idCinema = 0;
        SwitchScene.switchScene(event, "buyTicket1.fxml");
    }

    @FXML
    void select(ActionEvent event)
    {
        try
        {
            if(idBox.getValue() == null || seat.getValue() == null || row.getValue() == null)
            {
                Alert.alert1("proszę wypełnić wymagane pola!");
                throw new SQLException();
            }

            PreparedStatement pst = ConnectionVariable.c.prepareStatement("select count(*) from bd_projekt.bilet b where id_seans = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(idBox.getValue()));
            ResultSet rs = pst.executeQuery();
            rs.next();
            seats = rs.getInt(1);
            pst = ConnectionVariable.c.prepareStatement("select ilosc_miejsc from bd_projekt.sala sl, bd_projekt.seans s where s.id_seans = ? and s.id_sala = sl.id_sala", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(idBox.getValue()));
            rs = pst.executeQuery();
            rs.next();
            if(seats == rs.getInt(1))
            {
                Alert.alert1("sala jest pełna!");
                throw new SQLException();
            }
            pst = ConnectionVariable.c.prepareStatement("select count(*) from bd_projekt.bilet b where id_seans = ? and b.rzad = ? and b.numer_miejsca = ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pst.setInt(1, main.GetId.getId(idBox.getValue()));
            pst.setString(2, row.getValue());
            pst.setInt(3, seat.getValue());
            rs = pst.executeQuery();
            rs.next();
            if(rs.getInt(1) == 1)
            {
                Alert.alert1("to miejsce jest już zajęte!");
                throw new SQLException();
            }

            newShow = main.GetId.getId(idBox.getValue());
            newRow = row.getValue();
            newSeat = seat.getValue();

            pst.close();
            rs.close();

            SwitchScene.switchScene(event, "buyTicket3.fxml");
        }
        catch(SQLException e)
        {
            System.out.println("ERROR");
        }
    }
}
