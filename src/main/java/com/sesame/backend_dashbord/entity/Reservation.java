package com.sesame.backend_dashbord.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String statut;
    private Date date;

    @ManyToOne
    private User user;

    @ManyToOne
    private HotelOffer hotelOffer;
    public Reservation() {
        this.date = new Date();
    }

}
