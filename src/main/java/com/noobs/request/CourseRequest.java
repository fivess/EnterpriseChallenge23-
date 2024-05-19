package com.noobs.request;

import com.noobs.model.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseRequest {
    private String name;

    private String status;

    private String institution;

    private String description;

    private Date dateEnd;

    private Date dateStart;

    private String category;

    private String workload;


    public Course toModel() {
        return Course.builder()
                .name(this.name)
                .status(this.status)
                .institution(this.institution)
                .description(this.description)
                .dateEnd(this.dateEnd)
                .dateStart(this.dateStart)
                .category(this.category)
                .workload(this.workload)
                .build();
    }
}
