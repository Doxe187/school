package ch.bbw.cardgame;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MoviesNoSQLConnector {

    private static final String CONNECTION_STRING = "mongodb://root:root@localhost:27017/";
    private static final String DB_NAME = "moviesdb";

    public List<Movie> getAllMovies() {
        List<Movie> result = new ArrayList<>();
        try (MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                        .build())) {
            MongoCollection<Document> col = client.getDatabase(DB_NAME).getCollection("movies");
            for (Document doc : col.find()) {
                Movie m = new Movie();
                m.setId(doc.getInteger("_id"));
                m.setTitle(doc.getString("title"));
                m.setPlot(doc.getString("plot"));
                Object dirId = doc.get("director_id");
                if (dirId instanceof Integer) m.setDirectorId((Integer) dirId);
                Object year = doc.get("release_year");
                if (year instanceof Integer) m.setReleaseYear((Integer) year);

                Document imdbDoc = (Document) doc.get("imdb");
                if (imdbDoc != null) {
                    Movie.Imdb imdb = new Movie.Imdb();
                    Object rating = imdbDoc.get("rating");
                    if (rating instanceof Double) imdb.setRating((Double) rating);
                    else if (rating instanceof Integer) imdb.setRating((Integer) rating);
                    Object votes = imdbDoc.get("votes");
                    if (votes instanceof Integer) imdb.setVotes((Integer) votes);
                    imdb.setUrl(imdbDoc.getString("url"));
                    m.setImdb(imdb);
                }

                List<?> actorDocs = (List<?>) doc.get("actors");
                if (actorDocs != null) {
                    List<Movie.Actor> actors = new ArrayList<>();
                    for (Object a : actorDocs) {
                        Document ad = (Document) a;
                        Movie.Actor actor = new Movie.Actor();
                        Object pid = ad.get("person_id");
                        if (pid instanceof Integer) actor.setPersonId((Integer) pid);
                        actor.setAs(ad.getString("as"));
                        Object sal = ad.get("salary");
                        if (sal instanceof Integer) actor.setSalary((Integer) sal);
                        actors.add(actor);
                    }
                    m.setActors(actors);
                }
                result.add(m);
            }
        }
        return result;
    }

    public List<Studio> getAllStudios() {
        List<Studio> result = new ArrayList<>();
        try (MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                        .build())) {
            MongoCollection<Document> col = client.getDatabase(DB_NAME).getCollection("studios");
            for (Document doc : col.find()) {
                Studio s = new Studio();
                s.setId(doc.getInteger("_id"));
                s.setName(doc.getString("name"));
                Object year = doc.get("year_founded");
                if (year instanceof Integer) s.setYearFounded((Integer) year);

                List<?> movieIds = (List<?>) doc.get("movies");
                if (movieIds != null) {
                    List<Integer> ids = new ArrayList<>();
                    for (Object id : movieIds) {
                        if (id instanceof Integer) ids.add((Integer) id);
                    }
                    s.setMovies(ids);
                }

                Document hqDoc = (Document) doc.get("headquarters");
                if (hqDoc != null) {
                    Studio.Headquarters hq = new Studio.Headquarters();
                    hq.setAddress(hqDoc.getString("address"));
                    hq.setCity(hqDoc.getString("city"));
                    hq.setState(hqDoc.getString("state"));
                    hq.setCountry(hqDoc.getString("country"));
                    s.setHeadquarters(hq);
                }
                result.add(s);
            }
        }
        return result;
    }

    public List<Person> getAllPeople() {
        List<Person> result = new ArrayList<>();
        try (MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                        .build())) {
            MongoCollection<Document> col = client.getDatabase(DB_NAME).getCollection("people");
            for (Document doc : col.find()) {
                Person p = new Person();
                Object id = doc.get("_id");
                if (id instanceof Integer) p.setId((Integer) id);
                p.setFirstName(doc.getString("first_name"));
                p.setLastName(doc.getString("last_name"));
                p.setBirthDate(doc.getString("birth_date"));
                result.add(p);
            }
        }
        return result;
    }
}
