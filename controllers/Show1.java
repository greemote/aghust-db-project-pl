package controllers;

public class Show1
{
    private Integer id;
    private String technology;
    private String translation;
    private String date;
    private String time;

    public Show1(Integer id, String technology, String translation, String date, String time)
    {
        this.id = id;
        this.technology = technology;
        this.translation = translation;
        this.date = date;
        this.time = time;
    }

    public Integer getId(){ return id; }
    public String getTechnology(){ return technology; }
    public String getTranslation(){ return translation; }
    public String getDate(){ return date; }
    public String getTime(){ return time; }
}