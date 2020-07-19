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
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(length = 75)
    private String name;
    private double longitude,latitude,altitude;
    @OneToMany(mappedBy = "ville")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    private Collection<Cinema> cinemas;
}
