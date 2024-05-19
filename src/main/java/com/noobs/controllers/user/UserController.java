package com.noobs.controllers.user;

import com.noobs.dto.UserDto;
import com.noobs.model.User;
import com.noobs.request.UserRequest;
import com.noobs.respository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
public class UserController {

    @Inject
    UserRepository userRepository;


    @GET
    @Path("/{id}")
    @Transactional
    public Response getUser(@PathParam("id") Long id) {
        final User user = userRepository.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(UserDto.fromModel(user)).build();
    }

    @POST
    @Transactional
    public Response saveUser(UserRequest user) {
        final var entity = user.toModel();
        userRepository.save(entity);
        return Response.created(null).entity(entity).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public User updateUser(UserRequest user, @PathParam("id") Long id) {
        return userRepository.update(id, user);
    }

    @GET
    @Transactional
    public Response getAllUsers() {
        return Response.ok()
                .entity(userRepository.streamAll().map(UserDto::fromModel).toList())
                .build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userRepository.deleteById(id);
        return Response.ok().build();
    }

    @GET()
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response.ok()
                .entity(userRepository.findByName(name).stream().map(UserDto::fromModel).toList())
                .build();
    }

}
