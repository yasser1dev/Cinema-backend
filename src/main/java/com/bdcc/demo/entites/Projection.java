package com.bdcc.demo.entites;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Projection implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateProjection;
    private double prix;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Salle salle;
    @ManyToOne
    @ToString.Exclude
    private Film film;
    @OneToMany(mappedBy = "place")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;
}
