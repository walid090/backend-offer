package com.sesame.backend_dashbord.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name	;
    private String description	;
    private float price	;
    private String category	;
    private int quantity;
    private boolean available;
    private LocalDate dateAdded;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<Stock> stocks ;


    public Product() {
        this.dateAdded = LocalDate.now();
    }
}
