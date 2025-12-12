package software_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchlistDAO {

    // 🔹 Add movie to user's watchlist
    public static boolean addToWatchlist(int userId, int movieId) {

        String sql = "INSERT INTO watchlist (user_id, movie_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error adding to watchlist: " + e.getMessage());
            return false;
        }
    }

    // 🔹 Get all movies in user's watchlist
    public static List<Movie> getUserWatchlist(int userId) {

        List<Movie> movies = new ArrayList<>();

        String sql = """
            SELECT m.movie_id, m.title, m.overview, m.poster_url, m.release_year
            FROM watchlist w
            JOIN movies m ON w.mediaID = m.movie_id
            WHERE w.userID = ?
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movie movie = new Movie(
                        rs.getInt("movie_id"),
                        rs.getString("title"),
                        rs.getString("overview"),
                        rs.getString("poster_url"),
                        rs.getInt("release_year")
                );

                movies.add(movie);
            }

        } catch (SQLException e) {
            System.out.println("Error loading watchlist: " + e.getMessage());
        }

        return movies;
    }
}
