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
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 75)
    private String titre;
    @Column(length = 75)
    private String realisateur;
    @Column(length = 75)
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateSortie;
    private double duree;
    private String photo;
    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
    @ManyToOne
    private Categorie categorie;
}
