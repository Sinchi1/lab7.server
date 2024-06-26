package project.Managers;

import project.Collections.*;
import project.Common.Account;
import project.ProgrammEnums.OperationCode;

import java.sql.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseManager {
    private static DataBaseManager instance;
    private static Connection connection;
    public static DataBaseManager getInstance(){
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
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/studs", "num", "bruh");
    }

    public List<Movie> getCollection() throws SQLException {
        List<Movie> result = new LinkedList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Movie ORDER BY id ASC");
        ResultSet resultSet = preparedStatement.executeQuery();
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
                    ),
            resultSet.getString("user_name")
            );
            result.add(movie);

        }
        return result;
    }

    public void addMovie(Movie movie){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Movie " +
                    "(name," +
                    "cor_x," +
                    "cor_y," +
                    "oscar_count," +
                    "golden_palm," +
                    "lenght," +
                    "movie_genre," +
                    "person_name," +
                    "height," +
                    "eye_color," +
                    "hair_color," +
                    "nationality," +
                    "person_x," +
                    "person_y," +
                    "person_z," +
                    "city_name," +
                    "user_name)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            connection.setAutoCommit(false);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setDouble(2, movie.getCoordinates().getX());
            preparedStatement.setLong(3, movie.getCoordinates().getY());
            preparedStatement.setLong(4, movie.getOscarsCount());
            preparedStatement.setLong(5, movie.getGoldenPalmCount());
            preparedStatement.setLong(6, movie.getLength());
            preparedStatement.setString(7, movie.getGenre().name());
            preparedStatement.setString(8, movie.getOperator().getName());
            preparedStatement.setDouble(9, movie.getOperator().getHeight());
            preparedStatement.setString(10, movie.getOperator().getEyeColor().name());
            preparedStatement.setString(11, movie.getOperator().getHairColor().name());
            preparedStatement.setString(12, movie.getOperator().getNationality().name());
            preparedStatement.setFloat(13, movie.getOperator().getLocation().getX());
            preparedStatement.setDouble(14, movie.getOperator().getLocation().getY());
            preparedStatement.setInt(15, movie.getOperator().getLocation().getZ());
            preparedStatement.setString(16, movie.getOperator().getLocation().getName());
            preparedStatement.setString(17, Account.getInstance().getUserName());

            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement.close();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }
    }

    public void updateMovie(int id,Movie movie){
        try {

            String query = "UPDATE \"Movie\" SET " +
                    "name = ?, " + //1
                    "cor_x = ?, " + //2
                    "cor_y = ?, " + //3
                    "oscar_count = ?, " + //4
                    "golden_palm = ?, " + //5
                    "lenght = ?, " + //6
                    "movie_genre = ?, " + //7
                    "person_name = ?, " + //8
                    "height = ?, " + //9
                    "eye_color = ?, " + //10
                    "hair_color = ?, " + //11
                    "nationality = ?, " + //12
                    "person_x = ?, " + //13
                    "person_y = ?, " + //14
                    "person_z = ?, " + //15
                    "city_name = ? " +  // 16
                    "user_name = ? " +  // 17
                    "WHERE id = ?"; //18

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, movie.getName());
            preparedStatement.setDouble(2, movie.getCoordinates().getX());
            preparedStatement.setLong(3, movie.getCoordinates().getY());
            preparedStatement.setLong(4, movie.getOscarsCount());
            preparedStatement.setLong(5, movie.getGoldenPalmCount());
            preparedStatement.setLong(6, movie.getLength());
            preparedStatement.setString(7, movie.getGenre().name());
            preparedStatement.setString(8, movie.getOperator().getName());
            preparedStatement.setDouble(9, movie.getOperator().getHeight());
            preparedStatement.setString(10, movie.getOperator().getEyeColor().name());
            preparedStatement.setString(11, movie.getOperator().getHairColor().name());
            preparedStatement.setString(12, movie.getOperator().getNationality().name());
            preparedStatement.setFloat(13, movie.getOperator().getLocation().getX());
            preparedStatement.setDouble(14, movie.getOperator().getLocation().getY());
            preparedStatement.setInt(15, movie.getOperator().getLocation().getZ());
            preparedStatement.setString(16, movie.getOperator().getLocation().getName());
            preparedStatement.setString(17, Account.getInstance().getUserName());
            preparedStatement.setInt(18, id);

            preparedStatement.executeUpdate();

            connection.commit();

            preparedStatement.close();

        } catch (SQLException e) {
            try {
                connection.rollback();
                OperationCodeManager.getInstance().setProgrammState(OperationCode.error);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            return;
        }
    }

    public Movie getMovieById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from Movie where id = ? ");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Movie movie = null;
            while (resultSet.next()) {
                movie = new Movie(
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
                        ),
                        resultSet.getString("user_name")
                );
            }

            resultSet.close();
            preparedStatement.close();

            return  movie;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String filterName(String args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select Count(id) from Movie where name = ? ");
            preparedStatement.setString(1, args);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String result = String.valueOf(resultSet.getInt("count"));
            resultSet.close();
            preparedStatement.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> removeFirst() throws SQLException {
        List<String> result = new ArrayList<>();
        if (getCollection().isEmpty()){
            result.add("Коллекция пуста, первого элемента не существует");
            return result;

        }
        else {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Movie " +
                        "WHERE id = (SELECT id FROM \"Movie\" ORDER BY id LIMIT 1); ");

                preparedStatement.executeUpdate();

                preparedStatement.close();

                result.add("Первый элемент найден и удалён");

                return result;

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public List<String> removeOscar(int count) {
        List<String> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatementInfo = connection.prepareStatement("SELECT * FROM Movie WHERE oscar_count = ? LIMIT 1 ");
            preparedStatementInfo.setInt(1, count);
            ResultSet resultSet = preparedStatementInfo.executeQuery();

            while (resultSet.next()){
                result.add(String.valueOf(resultSet.getInt("oscar_count")));
            }

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"Movie\" " +
                    "WHERE ctid IN " +
                    "(SELECT ctid FROM \"Movie\" WHERE oscar_count = ? LIMIT 1)");
            preparedStatement.setInt(1, count);
            preparedStatement.executeUpdate();

            resultSet.close();
            preparedStatement.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getGenres() {
        List<String> result = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select movie_genre from Movie");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                result.add(resultSet.getString("movie_genre"));
            }
            resultSet.close();
            preparedStatement.close();

            return result;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie getHead() {
        try {
            PreparedStatement preparedStatement = connect().prepareStatement("SELECT id from Movie ORDER BY id ASC LIMIT 1");
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Movie movie = getMovieById(resultSet.getInt("id"));
            return movie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

        public void removeId(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from Movie where id = ? ");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearCollection() throws SQLException {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MOVIE WHERE user_name = ? ");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, Account.getInstance().getUserName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
    }

    public boolean registration(String name, String pass) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    return false;
                }
            } catch (Exception ignored){}
            preparedStatement = connection.prepareStatement("INSERT INTO Users(name, pass) VALUES(?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, pass);
            preparedStatement.execute();
            connection.commit();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return false;
    }

    public int getPersonId(String name, String pass) {
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT id FROM  Users WHERE name = ? AND pass = ?");

            ps.setString(1, name);
            ps.setString(2, pass);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                return resultSet.getInt("id");
            }

            return -1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public boolean log(String name, String pass) {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement("SELECT * FROM Users WHERE name = ? AND pass = ?");
            connection.setAutoCommit(false);
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet resultSet = ps.executeQuery();

            try {
                if (resultSet.next()) {
                    return true;
                }
                return false;
            } catch (SQLException exception){
                return false;
            }


        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return false;
    }

}


