DROP DATABASE IF EXISTS movie;
CREATE DATABASE movie;
USE movie;

CREATE TABLE Users (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE MediaItem (
    mediaID INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    releaseYear INT NOT NULL,
    duration INT,
    mediaType VARCHAR(50) NOT NULL,
    posterURL VARCHAR(300),
    description TEXT,
    tmdbRating FLOAT
);

CREATE TABLE Watchlist (
    watchlistID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    mediaID INT NOT NULL,
    addedDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (mediaID) REFERENCES MediaItem(mediaID) ON DELETE CASCADE
);

CREATE TABLE Review (
    reviewID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    mediaID INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 10),
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (mediaID) REFERENCES MediaItem(mediaID) ON DELETE CASCADE,
    UNIQUE(userID, mediaID)
);

INSERT INTO Users (username, password, email, age, gender) VALUES
('zahra', 'pass123', 'zahra@example.com', 22, 'Female'),
('abrar', 'pass123', 'abrar@example.com', 23, 'Female'),
('tayma', 'pass123', 'tayma@example.com', 22, 'Female'),
('nora', 'pass123', 'nora@example.com', 21, 'Female'),
('dema', 'pass123', 'dema@example.com', 22, 'Female'),
('wajdan', 'pass123', 'wajdan@example.com', 24, 'Female');

INSERT INTO MediaItem (title, releaseYear, duration, mediaType, posterURL, description, tmdbRating) VALUES
('Oppenheimer', 2023, 180, 'Movie', 'https://image.tmdb.org/t/p/original/ptpr0k3pQQlfhYEBc1P0UFRdGtk.jpg', 'A biographical drama about Oppenheimer.', 8.3),
('Fast X', 2023, 141, 'Movie', 'https://image.tmdb.org/t/p/original/fiVW06jE7z9YnO4trhaMEdclSiC.jpg', 'The tenth Fast & Furious film.', 7.0),
('Transformers: Rise of the Beasts', 2023, 127, 'Movie', 'https://image.tmdb.org/t/p/original/gPbM0MK8CP8A174rmUwGsADNYKD.jpg', 'Autobots unite with the Maximals.', 7.1),
('John Wick: Chapter 4', 2023, 169, 'Movie', 'https://image.tmdb.org/t/p/original/vZloFAK7NmvMGKE7VkF5UHaz0I.jpg', 'John Wick faces new enemies.', 8.0),
('Avatar: The Way of Water', 2022, 192, 'Movie', 'https://image.tmdb.org/t/p/original/t6HIqrRAclMCA60NsSmeqe9RmNV.jpg', 'Jake Sully continues his journey in Pandora.', 7.7),
('Inception', 2010, 148, 'Movie', 'https://image.tmdb.org/t/p/original/edv5CZvWj09upOsy2Y6IwDhK8bt.jpg', 'A thief enters dreams to steal secrets.', 8.8),
('Interstellar', 2014, 169, 'Movie', 'https://image.tmdb.org/t/p/original/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg', 'A journey through space and time.', 8.6),
('The Dark Knight', 2008, 152, 'Movie', 'https://image.tmdb.org/t/p/original/qJ2tW6WMUDux911r6m7haRef0WH.jpg', 'Batman faces the Joker.', 9.0),
('Dune: Part One', 2021, 155, 'Movie', 'https://image.tmdb.org/t/p/original/d5NXSklXo0qyIYkgV94XAgMIckC.jpg', 'Paul Atreides begins his destiny.', 8.1),
('The Matrix', 1999, 136, 'Movie', 'https://image.tmdb.org/t/p/original/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg', 'A hacker discovers reality is not real.', 8.7);

INSERT INTO Watchlist (userID, mediaID, addedDate) VALUES
(1, 1, '2025-01-10 12:00:00'),
(1, 3, '2025-01-11 12:00:00'),
(2, 2, '2025-01-12 12:00:00'),
(2, 5, '2025-01-13 12:00:00'),
(3, 4, '2025-01-14 12:00:00'),
(3, 7, '2025-01-15 12:00:00'),
(4, 6, '2025-01-16 12:00:00'),
(5, 8, '2025-01-17 12:00:00'),
(6, 9, '2025-01-18 12:00:00'),
(6, 10, '2025-01-19 12:00:00');

INSERT INTO Review (userID, mediaID, rating) VALUES
(1, 1, 9),
(1, 2, 7),
(2, 3, 8),
(2, 4, 9),
(3, 5, 8),
(3, 6, 10),
(4, 7, 9),
(5, 8, 10),
(5, 9, 8),
(6, 10, 9);

SELECT * FROM Users;
SELECT * FROM MediaItem;
SELECT * FROM Watchlist;
SELECT * FROM Review;
