package ch.bbw.cardgame;

import java.util.List;

public class Studio {
    private int id;
    private String name;
    private int yearFounded;
    private List<Integer> movies;
    private Headquarters headquarters;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getYearFounded() { return yearFounded; }
    public void setYearFounded(int yearFounded) { this.yearFounded = yearFounded; }
    public List<Integer> getMovies() { return movies; }
    public void setMovies(List<Integer> movies) { this.movies = movies; }
    public Headquarters getHeadquarters() { return headquarters; }
    public void setHeadquarters(Headquarters headquarters) { this.headquarters = headquarters; }

    @Override
    public String toString() {
        return "Studio{id=" + id + ", name='" + name + "', yearFounded=" + yearFounded + "}";
    }

    public static class Headquarters {
        private String address;
        private String city;
        private String state;
        private String country;

        public String getAddress() { return address; }
        public void setAddress(String address) { this.address = address; }
        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        public String getCountry() { return country; }
        public void setCountry(String country) { this.country = country; }
    }
}
