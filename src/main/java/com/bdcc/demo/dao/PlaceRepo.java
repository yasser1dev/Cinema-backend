package com.bdcc.demo.dao;

import com.bdcc.demo.entites.Place;
import com.bdcc.demo.entites.Salle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")

public interface PlaceRepo extends JpaRepository<Place,Long> {


    List<Place> findBySalle_Id(Long id);
}
