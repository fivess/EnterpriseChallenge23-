package com.noobs.request;


import com.noobs.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String name;
    private String email;
    private String cpf;
    private String address;
    private Date birth;
    private String schooling;
    private String phone;
    private String genre;
    private String maritalStatus;
    private String city;
    private String state;
    private String profession;

    public User toModel() {
       return User.builder()
               .name(this.name)
                .email(this.email)
                .cpf(this.cpf)
                .address(this.address)
                .birth(this.birth)
                .schooling(this.schooling)
                .phone(this.phone)
                .genre(this.genre)
                .maritalStatus(this.maritalStatus)
                .city(this.city)
                .state(this.state)
                .profession(this.profession)
               .build();
    }
}
