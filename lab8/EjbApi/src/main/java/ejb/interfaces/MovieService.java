package ejb.interfaces;

import ejb.dto.MovieData;
import ejb.dto.UserData;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface MovieService {
    MovieData getMovie(Integer movieId);
    List<MovieData> getAllMovies();
    List<MovieData> getUserMovies(UserData user);
    void addMovie(MovieData movieData);
    void updateMovie(MovieData movieData);
}
