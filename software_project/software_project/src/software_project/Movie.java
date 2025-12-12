package software_project;

public class Movie {
    private int movieId;          // DB id (optional if not saved yet)
    private String title;
    private String overview;
    private String posterUrl;
    private int releaseYear;

    public Movie(String title, String overview, String posterUrl, int releaseYear) {
        this.title = title;
        this.overview = overview;
        this.posterUrl = posterUrl;
        this.releaseYear = releaseYear;
    }

    public Movie(int movieId, String title, String overview, String posterUrl, int releaseYear) {
        this.movieId = movieId;
        this.title = title;
        this.overview = overview;
        this.posterUrl = posterUrl;
        this.releaseYear = releaseYear;
    }
    public int getId() {
        return movieId;
    }


    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public String getOverview() { return overview; }
    public String getPosterUrl() { return posterUrl; }
    public int getReleaseYear() { return releaseYear; }
}
