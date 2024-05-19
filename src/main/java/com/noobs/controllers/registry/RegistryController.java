package com.noobs.controllers.registry;


import com.noobs.model.Course;
import com.noobs.model.Registry;
import com.noobs.model.User;
import com.noobs.respository.CourseRepository;
import com.noobs.respository.RegistryRepository;
import com.noobs.respository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/register")
public class RegistryController {

    @Inject
    RegistryRepository registryRepository;

    @Inject
    CourseRepository courseRepository;

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/{user_id}/{course_id}")
    @Transactional
    public Response createRegistry(@PathParam("user_id") Long userId,
                                   @PathParam("course_id") Long courseId) {

        final Optional<Registry> byIdOptional = registryRepository
                .findByIdOptional(Registry.buildKey(userId, courseId));

        if (byIdOptional.isPresent()) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        final Optional<Course> courseOpt = courseRepository.findByIdOptional(courseId);

        if (courseOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Course %d not exist", courseId))
                    .build();
        }

        final Optional<User> userOpt = userRepository.findByIdOptional(userId);

        if (userOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("User %d not exist", userId))
                    .build();
        }


        final var registry = new Registry(userOpt.get(), courseOpt.get());

        registryRepository.persist(registry);

        return Response.ok(registry).build();
    }

    @DELETE
    @Path("/{user_id}/{course_id}")
    @Transactional
    public Response deleteRegistry(@PathParam("user_id") Long userId,
                                   @PathParam("course_id") Long courseId) {
        final String key = Registry.buildKey(userId, courseId);
        final Optional<Registry> registryOpt = registryRepository.findByIdOptional(key);

        if (registryOpt.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(String.format("Registry %s not exist", key))
                    .build();
        }

        registryRepository.delete(registryOpt.get());

        return Response.ok().build();
    }
}
