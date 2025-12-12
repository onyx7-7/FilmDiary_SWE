🎬 FilmDiary

Film Diary (Movie Tracker) is a desktop-based application that helps users organize and manage their personal movie lists and ratings. Users can track movies they plan to watch as well as movies they have already watched, making movie management simple and efficient without relying on memory.

The application is built using Java with a graphical user interface and uses MySQL for secure data storage. It integrates the TMDb (The Movie Database) API to fetch accurate and up-to-date movie information such as titles, descriptions, and images, providing a smooth and user-friendly experience.

🚀 Features

🔐 User login & session handling

🎞️ Browse popular movies (API-based)

➕ Add movies to personal watchlist

🗑️ Remove movies from watchlist

⭐ Rate movies (0–10, updateable)

📄 View detailed movie information

🖼️ Movie posters loaded dynamically from URLs

🛠️ Technologies Used

Java (Swing)

MySQL

JDBC

IntelliJ IDEA

Movie API (TMDB-style)

MVC-style structure (DAO + UI)

🗄️ Database Structure
movies
movie_id INT PRIMARY KEY AUTO_INCREMENT
title VARCHAR(255)
overview TEXT
poster_url VARCHAR(500)
release_year INT

users
userID INT PRIMARY KEY AUTO_INCREMENT
username VARCHAR(100)
password VARCHAR(100)

watchlist
watchlistID INT PRIMARY KEY AUTO_INCREMENT
userID INT
mediaID INT
addedDate DATETIME DEFAULT CURRENT_TIMESTAMP
UNIQUE (userID, mediaID)

ratings
ratingID INT PRIMARY KEY AUTO_INCREMENT
userID INT
mediaID INT
rating INT CHECK (rating BETWEEN 0 AND 10)
UNIQUE (userID, mediaID)

📂 Project Structure
software_project/
│
├── DBConnection.java
├── WatchlistDAO.java
├── Movie.java
├── Session.java
│
├── homePage.java
├── myListPage.java
├── detailsPage.java
├── aboutUsPage.java
│
├── MovieApiService.java
├── MovieCard.java

▶️ How to Run

Open the project in IntelliJ IDEA

Create a MySQL database named movie

Update credentials in DBConnection.java

Run homePage.java

Log in and start exploring 🎉
