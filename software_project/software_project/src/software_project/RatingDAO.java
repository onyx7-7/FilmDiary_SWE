package software_project;

import java.sql.*;

public class RatingDAO {

    public static void saveRating(int userId, int movieId, int rating) {

        String sql = """
            INSERT INTO ratings (user_id, movie_id, rating)
            VALUES (?, ?, ?)
            ON DUPLICATE KEY UPDATE rating = VALUES(rating)
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.setInt(3, rating);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
