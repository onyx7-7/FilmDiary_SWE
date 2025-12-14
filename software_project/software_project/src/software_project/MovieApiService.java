package software_project;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieApiService {

    private static final String API_KEY = "c0433f7232269051ce244873b313aee6";

    public static List<Movie> fetchPopularMovies() {
        List<Movie> movies = new ArrayList<>();
        String endpoint = "https://api.themoviedb.org/3/movie/popular?language=en-US&page=1&api_key=" + API_KEY;

        try {
            URL url = new URL(endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            JSONObject response = new JSONObject(json.toString());
            JSONArray results = response.getJSONArray("results");

            for (int i = 0; i < results.length(); i++) {
                JSONObject m = results.getJSONObject(i);

                String title = m.optString("title", "");
                String overview = m.optString("overview", "");
                String posterPath = m.optString("poster_path", null);
                String releaseDate = m.optString("release_date", "0000-00-00");

                int year = 0;
                if (releaseDate.length() >= 4) year = Integer.parseInt(releaseDate.substring(0, 4));

                String posterUrl = (posterPath != null && !posterPath.equals("null"))
                        ? "https://image.tmdb.org/t/p/w500" + posterPath
                        : "";

                movies.add(new Movie(title, overview, posterUrl, year));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
    public static List<Movie> searchMovies(String query) {
        List<Movie> results = new ArrayList<>();

        for (Movie movie : fetchPopularMovies()) {
            if (movie.getTitle().toLowerCase().contains(query.toLowerCase())) {
                results.add(movie);
            }
        }
        return results;
    }

}
