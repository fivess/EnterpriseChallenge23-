package com.noobs.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends PanacheEntity {

    private String name;
    private String email;
    @Column(unique = true)
    private String cpf;
    private String address;
    private Date creation;
    private Date birth;
    private String schooling;
    private String phone;
    private String genre;
    private String maritalStatus;
    private String city;
    private String state;
    private String profession;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Registry> registryList;
}
