package ejb.dao;

import ejb.dto.MovieData;

public class MovieDAO extends AbstractDAO<MovieData> {
    private MovieDAO() {
        super(MovieData.class);
    }

    private static MovieDAO instance;

    public static MovieDAO getInstance() {
        if (instance == null) {
            synchronized (MovieDAO.class) {
                if (instance == null) {
                    instance = new MovieDAO();
                }
            }
        }
        return instance;
    }
}
