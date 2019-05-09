package ws.resources;

import ejb.dto.UserData;
import ejb.interfaces.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
@Consumes({"application/json"})
@Produces({"application/json"})
public class UserResource {
    @EJB(lookup = "java:global/EjbImpl-1.0/UserServiceBean!ejb.interfaces.UserService")
    UserService userService;

    @GET
    public List<UserData> getUsers() {
        return userService.getUsers();
    }

    @POST
    public Response addUser(UserData user) {
        user.setId(0);
        userService.addUser(user);
        return Response.ok().build();
    }
}
