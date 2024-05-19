package com.noobs.respository;

import com.noobs.model.Course;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CourseRepository implements PanacheRepository<Course> {

    public Course update(Long id, Course course) {
        final Optional<Course> find = findByIdOptional(id);

        final var item = find.orElseThrow(NotFoundException::new);

        item.setName(course.getName());
        item.setStatus(course.getStatus());
        item.setInstitution(course.getInstitution());
        item.setDescription(course.getDescription());
        item.setDateStart(course.getDateStart());
        item.setDateEnd(course.getDateEnd());
        item.setCategory(course.getCategory());
        item.setWorkload(course.getWorkload());
        persist(item);
        return item;
    }

    public List<Course> findByName(String name) {
        return list("lower(name) LIKE ?1", name.toLowerCase() + "%");
    }
}
