package com.bdcc.demo.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String nomClient;
    private double prix;
    @Column(unique = false)
    private Integer CodePayement;
    private boolean reserved;

    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;

}
