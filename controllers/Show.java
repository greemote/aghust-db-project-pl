package controllers;

public class Show
{
    private Integer id;
    private String title;
    private String cinema;
    private String hall;
    private String technology;
    private String translation;
    private String date;
    private String time;

    public Show(Integer id, String title, String cinema, String hall, String technology, String translation, String date, String time)
    {
        this.id = id;
        this.title = title;
        this.cinema = cinema;
        this.hall = hall;
        this.technology = technology;
        this.translation = translation;
        this.date = date;
        this.time = time;
    }

    public Integer getId(){ return id; }
    public String getTitle(){ return title; }
    public String getCinema(){ return cinema; }
    public String getHall(){ return hall; }
    public String getTechnology(){ return technology; }
    public String getTranslation(){ return translation; }
    public String getDate(){ return date; }
    public String getTime(){ return time; }
}