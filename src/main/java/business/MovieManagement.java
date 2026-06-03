package business;

import model.Movie;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieManagement {
    public void addMovie(String title, String director, int year) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL add_movie(?, ?, ?)}")) {
            statement.setString(1, title);
            statement.setString(2, director);
            statement.setInt(3, year);
            statement.executeUpdate();
        }
    }

    public List<Movie> listMovies() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL list_movies()}");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                movies.add(new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("director"),
                        resultSet.getInt("year")
                ));
            }
        }
        return movies;
    }

    public void updateMovie(int id, String title, String director, int year) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL update_movie(?, ?, ?, ?)}")) {
            statement.setInt(1, id);
            statement.setString(2, title);
            statement.setString(3, director);
            statement.setInt(4, year);
            statement.executeUpdate();
        }
    }

    public void deleteMovie(int id) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{CALL delete_movie(?)}")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
