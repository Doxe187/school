package ch.bbw.cardgame;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Aufgabe 3 – CRUD Operationen für die moviesdb
 * Alle Operationen werden direkt aus dem main ausgeführt.
 */
public class MoviesCRUDMain {

    private static final String CONNECTION_STRING = "mongodb://root:root@localhost:27017/";
    private static final String DB_NAME = "moviesdb";

    public static void main(String[] args) {
        try (MongoClient client = MongoClients.create(
                MongoClientSettings.builder()
                        .applyConnectionString(new ConnectionString(CONNECTION_STRING))
                        .build())) {

            MongoDatabase db = client.getDatabase(DB_NAME);

            crudMovies(db);
            crudStudios(db);
            crudPeople(db);
        }
    }

    // ===================== MOVIES =====================
    private static void crudMovies(MongoDatabase db) {
        MongoCollection<Document> movies = db.getCollection("movies");

        System.out.println("=== MOVIES ===");

        // CREATE
        Document newMovie = new Document("_id", 999)
                .append("title", "Inception")
                .append("plot", "A thief who steals corporate secrets through dream-sharing technology.")
                .append("director_id", 218)
                .append("release_year", 2010)
                .append("imdb", new Document("rating", 8.8).append("votes", 2300000))
                .append("actors", Arrays.asList(
                        new Document("person_id", 181).append("as", "Dom Cobb").append("salary", 20000000)
                ));
        movies.insertOne(newMovie);
        System.out.println("CREATE: Inserted Inception (id=999)");

        // READ – alle Filme
        System.out.println("READ – alle Filme:");
        for (Document doc : movies.find()) {
            System.out.println("  " + doc.get("_id") + " | " + doc.getString("title")
                    + " (" + doc.getInteger("release_year") + ")");
        }

        // READ – gefiltert (nur Filme nach 2000)
        System.out.println("READ – Filme nach 2000:");
        for (Document doc : movies.find(Filters.gt("release_year", 2000))) {
            System.out.println("  " + doc.getString("title") + " (" + doc.getInteger("release_year") + ")");
        }

        // UPDATE – Plot von Inception ändern
        movies.updateOne(
                Filters.eq("_id", 999),
                Updates.set("plot", "A thief enters dreams to plant an idea in a target's mind.")
        );
        System.out.println("UPDATE: Plot von Inception aktualisiert");

        // READ – nach Update prüfen
        Document updated = movies.find(Filters.eq("_id", 999)).first();
        if (updated != null) {
            System.out.println("  Neuer Plot: " + updated.getString("plot"));
        }

        // DELETE
        movies.deleteOne(Filters.eq("_id", 999));
        System.out.println("DELETE: Inception (id=999) gelöscht");
        System.out.println("  Anzahl Filme nach Delete: " + movies.countDocuments());
    }

    // ===================== STUDIOS =====================
    private static void crudStudios(MongoDatabase db) {
        MongoCollection<Document> studios = db.getCollection("studios");

        System.out.println("\n=== STUDIOS ===");

        // CREATE
        Document newStudio = new Document("_id", 99)
                .append("name", "Universal Pictures")
                .append("year_founded", 1912)
                .append("movies", Arrays.asList(999))
                .append("headquarters", new Document("address", "100 Universal City Plaza")
                        .append("city", "Universal City")
                        .append("state", "California")
                        .append("country", "United States"));
        studios.insertOne(newStudio);
        System.out.println("CREATE: Inserted Universal Pictures (id=99)");

        // READ – alle Studios
        System.out.println("READ – alle Studios:");
        for (Document doc : studios.find()) {
            System.out.println("  " + doc.get("_id") + " | " + doc.getString("name")
                    + " (gegründet " + doc.getInteger("year_founded") + ")");
        }

        // UPDATE – Name korrigieren
        studios.updateOne(
                Filters.eq("_id", 99),
                Updates.set("name", "Universal Pictures (NBCUniversal)")
        );
        System.out.println("UPDATE: Name von Studio 99 aktualisiert");

        // DELETE
        studios.deleteOne(Filters.eq("_id", 99));
        System.out.println("DELETE: Studio 99 gelöscht");
    }

    // ===================== PEOPLE =====================
    private static void crudPeople(MongoDatabase db) {
        MongoCollection<Document> people = db.getCollection("people");

        System.out.println("\n=== PEOPLE ===");

        // CREATE
        Document newPerson = new Document("_id", 9999)
                .append("first_name", "Tom")
                .append("last_name", "Hanks")
                .append("birth_date", "1956-07-09");
        people.insertOne(newPerson);
        System.out.println("CREATE: Inserted Tom Hanks (id=9999)");

        // READ – alle Personen
        System.out.println("READ – alle Personen:");
        for (Document doc : people.find()) {
            System.out.println("  " + doc.get("_id") + " | "
                    + doc.getString("first_name") + " " + doc.getString("last_name")
                    + " (*" + doc.getString("birth_date") + ")");
        }

        // READ – gefiltert nach Nachname
        System.out.println("READ – Person mit Nachname 'Cameron':");
        Document found = people.find(Filters.eq("last_name", "Cameron")).first();
        if (found != null) {
            System.out.println("  Gefunden: " + found.getString("first_name") + " " + found.getString("last_name"));
        }

        // UPDATE – Geburtsdatum korrigieren
        people.updateOne(
                Filters.eq("_id", 9999),
                Updates.set("birth_date", "1956-07-09")
        );
        System.out.println("UPDATE: Geburtsdatum von Tom Hanks bestätigt");

        // DELETE
        people.deleteOne(Filters.eq("_id", 9999));
        System.out.println("DELETE: Tom Hanks (id=9999) gelöscht");
        System.out.println("  Anzahl Personen nach Delete: " + people.countDocuments());
    }
}
