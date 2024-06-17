package project.Managers;

import project.Collections.*;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

public class DataBaseManager {
    private static DataBaseManager instance;
    private static Connection connection;
    private String url;
    private String user;
    private String pass;

    public static synchronized DataBaseManager getInstance(){
        if (instance == null) {
            instance = new DataBaseManager();
            try {
                connection = connect();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    public static Connection connect() throws SQLException{
        // Open a connection
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab7", "postgres", "Homychok11");
    }

    public List<Movie> getCollection() throws SQLException {
        List<Movie> result = new LinkedList<>();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM public.\"Movie\"");
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Movie movie = new Movie(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            new Coordinates(
                    resultSet.getInt("cor_x"),
                    resultSet.getLong("cor_y")),
            ZonedDateTime.now(),
            resultSet.getInt("oscar_count"),
            resultSet.getLong("golden_palm"),
            resultSet.getLong("lenght"),
            MovieGenre.valueOf(resultSet.getString("movie_genre")),
            new Person(
                    resultSet.getString("person_name"),
                    resultSet.getDouble("height"),
                    Color.valueOf(resultSet.getString("eye_color")),
                    Color.valueOf(resultSet.getString("hair_color")),
                    Country.valueOf(resultSet.getString("nationality")),
                    new Location(
                            resultSet.getFloat("person_x"),
                            resultSet.getDouble("person_y"),
                            resultSet.getInt("person_z"),
                            resultSet.getString("city_name")
                    )
                    )
            );
            result.add(movie);
        }
        return result;
    }
}


