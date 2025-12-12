package software_project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MovieCard extends JPanel {

    private Movie movie;

    public MovieCard(Movie movie) {
        this.movie = movie;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 260));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Poster
        JLabel posterLabel = new JLabel();
        posterLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            if (!movie.getPosterUrl().isEmpty()) {
                ImageIcon icon = new ImageIcon(new URL(movie.getPosterUrl()));
                Image scaled = icon.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaled));
            }
        } catch (Exception e) {
            posterLabel.setText("No Image");
        }

        // Title
        JLabel titleLabel = new JLabel(
                "<html><center>" + movie.getTitle() + "</center></html>",
                JLabel.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12));

        add(posterLabel, BorderLayout.CENTER);
        add(titleLabel, BorderLayout.SOUTH);
    }

    public Movie getMovie() {
        return movie;
    }
}
