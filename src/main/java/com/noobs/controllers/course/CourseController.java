package com.noobs.controllers.course;

import com.noobs.dto.CourseDto;
import com.noobs.model.Course;
import com.noobs.request.CourseRequest;
import com.noobs.respository.CourseRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/course")
public class CourseController {

    @Inject
    CourseRepository courseRepository;

    @GET
    @Path("/{id}")
    @Transactional
    public Response getCourse(@PathParam("id") Long id) {
        final Course course = courseRepository.findById(id);
        if (course == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(CourseDto.fromModel(course)).build();
    }

    @POST
    @Transactional
    public Response saveCourse(CourseRequest course) {
        final Course entity = course.toModel();
        courseRepository.persist(entity);
        return Response.created(null).entity(entity).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Course updateCourse(CourseRequest course, @PathParam("id") Long id) {
        return courseRepository.update(id, course.toModel());
    }

    @GET
    @Transactional
    public Response getAllCourses() {
        return Response.ok()
                .entity(courseRepository.streamAll().map(CourseDto::fromModel).toList())
                .build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteCourse(@PathParam("id") Long id) {
       courseRepository.deleteById(id);
        return Response.ok().build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response.ok()
                .entity(courseRepository.findByName(name)
                        .stream()
                        .map(CourseDto::fromModel).toList())
                .build();
    }

}
