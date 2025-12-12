
package software_project;

import javax.swing.*;
import java.awt.*;

public class myListPage extends javax.swing.JFrame {

    public myListPage() {
        User currentUser = Session.getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "Please log in first.");
            new loginPage().setVisible(true);
            dispose();
            return;
        }
        initComponents();
        setupMenuListeners();
        setupDynamicMovieList();
    }

    private void setupMenuListeners() {

        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new homePage().setVisible(true);
                dispose();
            }
        });

        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (Session.getCurrentUser() == null) {
                    JOptionPane.showMessageDialog(null, "Please log in first.");
                    new loginPage().setVisible(true);
                    dispose();
                    return;
                }
                new editProfilePage().setVisible(true);
                dispose();
            }
        });

        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new myListPage().setVisible(true);
                dispose();
            }
        });

        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new aboutUsPage().setVisible(true);
                dispose();
            }
        });
    }


    /** Inserts your teammate’s dynamic panel into the scrollpane */
    private void setupDynamicMovieList() {
//
//        JPanel moviePanel = new JPanel();
//        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));
//
//        moviePanel.add(new JLabel(new ImageIcon(getClass().getResource("/software_project/movie (1).png"))));
//        moviePanel.add(Box.createVerticalStrut(10));
//
//        moviePanel.add(new JLabel(new ImageIcon(getClass().getResource("/software_project/movie (3).png"))));
//        moviePanel.add(Box.createVerticalStrut(10));
//
//        moviePanel.add(new JLabel(new ImageIcon(getClass().getResource("/software_project/movie (4).png"))));
//
//        jScrollPane2.setViewportView(moviePanel);
        User user = Session.getCurrentUser();
        if (user == null) return;

        JPanel moviePanel = new JPanel();
        moviePanel.setLayout(new BoxLayout(moviePanel, BoxLayout.Y_AXIS));

        java.util.List<Movie> movies =
                WatchlistDAO.getUserWatchlist(user.getUserID());

        if (movies.isEmpty()) {
            moviePanel.add(new JLabel("Your list is empty."));
        }

        for (Movie movie : movies) {

            JPanel row = new JPanel(new BorderLayout());
            row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel title = new JLabel(movie.getTitle());
            title.setFont(new Font("Segoe UI", Font.BOLD, 16));

            JButton viewBtn = new JButton("View");
            viewBtn.addActionListener(e -> {
                new detailsPage(movie).setVisible(true);
                dispose();
            });

            row.add(title, BorderLayout.CENTER);
            row.add(viewBtn, BorderLayout.EAST);

            moviePanel.add(row);
        }

        jScrollPane2.setViewportView(moviePanel);
        jScrollPane2.revalidate();
        jScrollPane2.repaint();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 300, 260));

        jButton1.setText("view");
        jButton1.addActionListener(evt -> {
            new detailsPage().setVisible(true);
            dispose();
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, 120, 30));

        jButton2.setText("delete");
        jButton2.addActionListener(evt -> {
            if (Session.getCurrentUser() == null) {
                JOptionPane.showMessageDialog(this, "Please log in first.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete this item?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Item removed from your list.");
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 120, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/software_project/software project (4).png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jMenu1.setText("home");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("profile");
        jMenuBar1.add(jMenu2);

        jMenu4.setText("my list");
        jMenuBar1.add(jMenu4);

        jMenu3.setText("about us");
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new myListPage().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
}
