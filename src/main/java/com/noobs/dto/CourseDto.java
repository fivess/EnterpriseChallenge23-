package com.noobs.dto;

import com.noobs.model.Course;
import com.noobs.model.Registry;
import com.noobs.model.User;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@RegisterForReflection
public class CourseDto {

    private Long id;
    private String name;

    private String status;

    private String institution;

    private String description;

    private Date dateEnd;

    private Date dateStart;

    private String category;

    private String workload;

    private List<User> users;

    public static CourseDto fromModel(Course course) {
        return CourseDto.builder()
                .id(course.id)
                .name(course.getName())
                .status(course.getStatus())
                .institution(course.getInstitution())
                .description(course.getDescription())
                .dateEnd(course.getDateEnd())
                .dateStart(course.getDateStart())
                .category(course.getCategory())
                .workload(course.getWorkload())
                .users(course.getRegistryList().stream().map(Registry::getUser).toList())
                .build();

    }
}
