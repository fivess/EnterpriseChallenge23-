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
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String  address;
    private Date creation;
    private Date birth;
    private String schooling;
    private String phone;
    private String genre;
    private String maritalStatus;
    private String city;
    private String state;
    private String profession;
    private List<Course> courses;


    public static UserDto fromModel(User user) {
        return UserDto.builder()
                .id(user.id)
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .address(user.getAddress())
                .creation(user.getCreation())
                .birth(user.getBirth())
                .schooling(user.getSchooling())
                .phone(user.getPhone())
                .genre(user.getGenre())
                .maritalStatus(user.getMaritalStatus())
                .city(user.getCity())
                .state(user.getState())
                .profession(user.getProfession())
                .courses(user.getRegistryList().stream().map(Registry::getCourse).toList())
                .build();

    }
}
