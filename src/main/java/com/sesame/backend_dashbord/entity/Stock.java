package com.sesame.backend_dashbord.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private Type_Mov movement_type ;
    private int quantity;
    private LocalDate date_movement;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product>products;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Customer>customers;


    @ManyToMany(cascade = CascadeType.ALL)
    private List<Supplier>suppliers;

    public Stock() {
        this.date_movement = LocalDate.now();
    }
}
