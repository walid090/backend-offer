package com.sesame.backend_dashbord.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String phone_number;
    private String email;
    private String role;

    @OneToMany
    List<Reservation> reservations;

}

