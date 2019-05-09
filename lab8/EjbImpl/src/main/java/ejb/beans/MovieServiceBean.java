package ejb.beans;

import ejb.dto.MovieData;
import ejb.dto.UserData;
import ejb.interfaces.MovieService;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
@Remote(MovieService.class)
public class MovieServiceBean implements MovieService {
    @Override
    public MovieData getMovie(Integer movieId) {
        return null;
    }

    @Override
    public List<MovieData> getAllMovies() {
        return null;
    }

    @Override
    public List<MovieData> getUserMovies(UserData user) {
        return null;
    }

    @Override
    public void addMovie(MovieData movieData) {

    }

    @Override
    public void updateMovie(MovieData movieData) {

    }
}
