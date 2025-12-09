DROP TABLE IF EXISTS Review;
DROP TABLE IF EXISTS WatchlistEntry;
DROP TABLE IF EXISTS MediaItem;
DROP TABLE IF EXISTS MediaType;
DROP TABLE IF EXISTS Users;

CREATE DATABASE IF NOT EXISTS movie;
USE movie;

CREATE TABLE Users (
    userID INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL
);

CREATE TABLE MediaType (
    typeID INT PRIMARY KEY AUTO_INCREMENT,
    typeName VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE MediaItem (
    mediaID INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    releaseYear INT NOT NULL,
    duration INT,
    typeID INT NOT NULL,
    posterURL VARCHAR(300),
    description TEXT,
    tmdbRating FLOAT,
    FOREIGN KEY (typeID) REFERENCES MediaType(typeID)
);

CREATE TABLE WatchlistEntry (
    watchlistID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    mediaID INT NOT NULL,
    addedDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (mediaID) REFERENCES MediaItem(mediaID) ON DELETE CASCADE,
    INDEX idx_userID (userID),
    INDEX idx_mediaID (mediaID)
);

CREATE TABLE Review (
    reviewID INT PRIMARY KEY AUTO_INCREMENT,
    userID INT NOT NULL,
    mediaID INT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 10),
    comment TEXT,
    reviewDate DATETIME NOT NULL,
    FOREIGN KEY (userID) REFERENCES Users(userID) ON DELETE CASCADE,
    FOREIGN KEY (mediaID) REFERENCES MediaItem(mediaID) ON DELETE CASCADE,
    UNIQUE(userID, mediaID)
);

INSERT INTO MediaType (typeName) VALUES
('Movie'), ('Series'), ('Documentary'), ('Short Film');

INSERT INTO Users (username, password, email, age, gender) VALUES
('zahra', 'pass123', 'zahra@example.com', 22, 'Female'),
('abrar', 'pass123', 'abrar@example.com', 23, 'Female'),
('tayma', 'pass123', 'tayma@example.com', 22, 'Female'),
('nora', 'pass123', 'nora@example.com', 21, 'Female'),
('dema', 'pass123', 'dema@example.com', 22, 'Female'),
('wajdan', 'pass123', 'wajdan@example.com', 24, 'Female');

INSERT INTO MediaItem (title, releaseYear, duration, typeID, posterURL, description, tmdbRating) VALUES
('Oppenheimer', 2023, 180, 1, 'https://image.tmdb.org/t/p/w500/px.jpg', 'A biographical drama about J. Robert Oppenheimer.', 8.3),
('Fast X', 2023, 141, 1, 'https://image.tmdb.org/t/p/w500/fx.jpg', 'The tenth Fast & Furious film.', 7.0),
('Transformers: Rise of the Beasts', 2023, 127, 1, 'https://image.tmdb.org/t/p/w500/tf.jpg', 'Autobots join forces with the Maximals.', 7.1),
('John Wick: Chapter 4', 2023, 169, 1, 'https://image.tmdb.org/t/p/w500/jw.jpg', 'Legendary hitman John Wick faces new enemies.', 8.0),
('Avatar: The Way of Water', 2022, 192, 1, 'https://image.tmdb.org/t/p/w500/av.jpg', 'Jake Sully lives with his newfound family in Pandora.', 7.7);

INSERT INTO WatchlistEntry (userID, mediaID, addedDate) VALUES
(1, 1, '2025-01-10 12:00:00'),
(2, 2, '2025-01-11 12:00:00'),
(3, 4, '2025-01-12 12:00:00'),
(4, 5, '2025-01-13 12:00:00'),
(5, 3, '2025-01-14 12:00:00'),
(6, 1, '2025-01-15 12:00:00');

INSERT INTO Review (userID, mediaID, rating, comment, reviewDate) VALUES
(1, 1, 9, 'Amazing movie about science and history.', '2025-01-15 12:00:00'),
(2, 2, 7, 'Fast-paced action as expected.', '2025-01-16 12:00:00'),
(3, 4, 8, 'John Wick never disappoints!', '2025-01-17 12:00:00'),
(6, 1, 10, 'Absolutely loved it, a masterpiece!', '2025-01-18 12:00:00');

SHOW TABLES;
DESCRIBE Users;
DESCRIBE MediaType;
DESCRIBE MediaItem;
DESCRIBE WatchlistEntry;
DESCRIBE Review;
SELECT * FROM Users;
SELECT * FROM MediaType;
SELECT * FROM MediaItem;
SELECT * FROM WatchlistEntry;
SELECT * FROM Review;
