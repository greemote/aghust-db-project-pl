package controllers;

public class Worker
{
    private Integer id;
    private String cinema;
    private String name;
    private String surname;
    private String email;
    private String phone;

    public Worker(Integer id, String cinema, String name, String surname, String email, String phone)
    {
        this.id = id;
        this.cinema = cinema;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public Integer getId(){ return id; }
    public String getCinema(){ return cinema; }
    public String getName(){ return name; }
    public String getSurname(){ return surname; }
    public String getEmail(){ return email; }
    public String getPhone(){ return phone; }
}
