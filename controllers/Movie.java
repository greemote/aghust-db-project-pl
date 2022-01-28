package controllers;

public class Movie
{
    private String title;
    private String director;
    private String genre;
    private String ageCategory;

    public Movie(String title, String director, String genre, String ageCategory)
    {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.ageCategory = ageCategory;
    }

    public String getTitle(){ return title; }
    public String getDirector(){ return director; }
    public String getGenre(){ return genre; }
    public String getAgeCategory(){ return ageCategory; }
}