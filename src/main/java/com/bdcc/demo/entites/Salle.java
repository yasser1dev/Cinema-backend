package com.bdcc.demo.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Salle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String name;
    private int nbrPlace;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Collection<Place> places;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Collection<Projection> projections;

}
