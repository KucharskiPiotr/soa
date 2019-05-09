package ws.resources;

import ejb.interfaces.MovieService;
import ws.dto.WSMovie;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

@Path("/movies")
@Consumes({"application/json"})
@Produces({"application/json"})
public class MovieResource {

    @EJB(lookup = "java:global/EjbImpl-1.0/MovieServiceBean!ejb.interfaces.MovieService")
    MovieService movieService;

    @GET
    public List<WSMovie> getMovies() {
        return new ArrayList<WSMovie>();
    }
}
