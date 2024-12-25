package com.sesame.backend_dashbord.entity;




import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class HotelOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private Date startDate;
    private Date endDate;
    private String image; // Store file path or URL
    private boolean active;

    @OneToMany
    List<Reservation> reservations;




}
