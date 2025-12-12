package software_project;

import java.util.List;

public class TestTMDB {
    public static void main(String[] args) {
        List<Movie> movies = MovieApiService.fetchPopularMovies();
        System.out.println("Fetched: " + movies.size());
        for (int i = 0; i < Math.min(5, movies.size()); i++) {
            System.out.println(movies.get(i).getTitle() + " (" + movies.get(i).getReleaseYear() + ")");
        }
    }
}
