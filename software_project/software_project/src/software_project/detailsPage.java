package software_project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 *
 * @author wjd12
 */
public class detailsPage extends javax.swing.JFrame {

    private Movie movie;

    // ================== CONSTRUCTORS ==================

    public detailsPage() {
        initComponents();
        setupMenuListeners();
    }

    public detailsPage(Movie movie) {
        initComponents();
        setupMenuListeners();
        this.movie = movie;
        loadMovieData();
    }

    // ================== LOAD MOVIE DATA ==================

    private void loadMovieData() {
        if (movie == null) return;

        jLabelTitle.setText(movie.getTitle());
        jTextAreaOverview.setText(movie.getOverview());

        try {
            if (movie.getPosterUrl() != null && !movie.getPosterUrl().isEmpty()) {
                ImageIcon icon = new ImageIcon(new URL(movie.getPosterUrl()));
                Image scaled = icon.getImage().getScaledInstance(
                        200,   // same width as label
                        280,   // same height as label
                        Image.SCALE_SMOOTH
                );
                jLabelPoster.setIcon(new ImageIcon(scaled));
                jLabelPoster.setText(""); // remove "No Image"

            }
        } catch (Exception e) {
            jLabelPoster.setText("No Image");
        }
    }

    // ================== MENU ==================

    private void setupMenuListeners() {

        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new homePage().setVisible(true);
                dispose();
            }
        });

        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new editProfilePage().setVisible(true);
                dispose();
            }
        });

        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new myListPage().setVisible(true);
                dispose();
            }
        });

        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new aboutUsPage().setVisible(true);
                dispose();
            }
        });
    }

    // ================== GUI ==================

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jLabelTitle = new javax.swing.JLabel();
        jLabelPoster = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaOverview = new javax.swing.JTextArea();
        jButtonAdd = new javax.swing.JButton();
        jButtonRate = new javax.swing.JButton();
        jButtonBack = new javax.swing.JButton();

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 24));
        add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 600, 40));

        jLabelPoster.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(jLabelPoster, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 200, 280));

        jTextAreaOverview.setLineWrap(true);
        jTextAreaOverview.setWrapStyleWord(true);
        jTextAreaOverview.setEditable(false);
        jScrollPane1.setViewportView(jTextAreaOverview);
        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 400, 200));

        jButtonAdd.setText("Add to List");
        jButtonAdd.addActionListener(evt ->
                JOptionPane.showMessageDialog(this, "Added to watchlist!")
        );
        add(jButtonAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 120, 30));

        jButtonRate.setText("Rate");
        jButtonRate.addActionListener(evt -> rateMovie());
        add(jButtonRate, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 120, 30));

        jButtonBack.setText("Back");
        jButtonBack.addActionListener(evt -> {
            new homePage().setVisible(true);
            dispose();
        });
        add(jButtonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 120, 30));

        jMenu1.setText("Home");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Profile");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("My List");
        jMenuBar1.add(jMenu4);

        jMenu3.setText("About Us");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);
        setSize(750, 450);
        setLocationRelativeTo(null);
    }

    // ================== RATE ==================

    private void rateMovie() {

            User user = Session.getCurrentUser();
            if (user == null) {
                JOptionPane.showMessageDialog(this, "Please log in first.");
                return;
            }

            if (!WatchlistDAO.isMovieInWatchlist(user.getUserID(), movie.getMovieId())) {
                JOptionPane.showMessageDialog(this,
                        "You must add this movie to your list before rating it.");
                return;
            }

            String input = JOptionPane.showInputDialog(
                    this,
                    "Enter rating (0–10):",
                    "Rate Movie",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input != null) {
                try {
                    int rating = Integer.parseInt(input);
                    if (rating < 0 || rating > 10) {
                        JOptionPane.showMessageDialog(this, "Rating must be 0–10");
                        return;
                    }

                    RatingDAO.saveRating(
                            user.getUserID(),
                            movie.getMovieId(),
                            rating
                    );

                    JOptionPane.showMessageDialog(this, "Rating saved!");

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Invalid number");
                }
            }
        }




    // ================== VARIABLES ==================

    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonRate;
    private javax.swing.JButton jButtonBack;
    private javax.swing.JLabel jLabelPoster;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaOverview;
}
