package com.noobs.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Date;

@Entity(name = "registry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registry extends PanacheEntityBase {

    @Id
    private String id;

    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference
    private Course course;

    private Date createdDate;

    public Registry(User user, Course course) {
        this.id = buildKey(user, course);
        this.user = user;
        this.course = course;
        this.createdDate = new Date();
    }

    public static String buildKey(User user, Course course) {
        return buildKey(user.id, course.id);
    }

    public static String buildKey(Long userId, Long courseId) {
        return String.join("-", userId.toString(), courseId.toString());
    }

}
