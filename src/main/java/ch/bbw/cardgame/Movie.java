package ch.bbw.cardgame;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String plot;
    private int directorId;
    private int releaseYear;
    private Imdb imdb;
    private List<Actor> actors;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getPlot() { return plot; }
    public void setPlot(String plot) { this.plot = plot; }
    public int getDirectorId() { return directorId; }
    public void setDirectorId(int directorId) { this.directorId = directorId; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }
    public Imdb getImdb() { return imdb; }
    public void setImdb(Imdb imdb) { this.imdb = imdb; }
    public List<Actor> getActors() { return actors; }
    public void setActors(List<Actor> actors) { this.actors = actors; }

    @Override
    public String toString() {
        return "Movie{id=" + id + ", title='" + title + "', releaseYear=" + releaseYear + "}";
    }

    public static class Imdb {
        private double rating;
        private int votes;
        private String url;

        public double getRating() { return rating; }
        public void setRating(double rating) { this.rating = rating; }
        public int getVotes() { return votes; }
        public void setVotes(int votes) { this.votes = votes; }
        public String getUrl() { return url; }
        public void setUrl(String url) { this.url = url; }
    }

    public static class Actor {
        private int personId;
        private String as;
        private Integer salary;

        public int getPersonId() { return personId; }
        public void setPersonId(int personId) { this.personId = personId; }
        public String getAs() { return as; }
        public void setAs(String as) { this.as = as; }
        public Integer getSalary() { return salary; }
        public void setSalary(Integer salary) { this.salary = salary; }
    }
}
