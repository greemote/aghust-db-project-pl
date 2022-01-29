package controllers;

public class Ticket
{
    private Double value;
    private String title;
    private String cinema;
    private String hall;
    private String row;
    private String seat;
    private String date;
    private String time;

    public Ticket(Double value, String title, String cinema, String hall, String row, String seat, String date, String time)
    {
        this.value = value;
        this.title = title;
        this.cinema = cinema;
        this.hall = hall;
        this.row = row;
        this.seat = seat;
        this.date = date;
        this.time = time;
    }

    public Double getValue(){ return value; }
    public String getTitle(){ return title; }
    public String getCinema(){ return cinema; }
    public String getHall(){ return hall; }
    public String getRow(){ return row; }
    public String getSeat(){ return seat; }
    public String getDate(){ return date; }
    public String getTime(){ return time; }
}